package models.utils.files;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.utils.config.Config;
import models.utils.config.SaveData;

import java.io.*;

public class SaveFileRW {

    private SaveData saveData;
    private String saveFileName;
    private File saveFile;

    /**
     * Construct the SaveFileRW with persistent Files.
     * @param saveData - where we pull SaveData from or write SaveData to
     * @param saveFileName - the File on disk
     */
    public void init(SaveData saveData, String saveFileName) {
        this.saveData = saveData;
        this.saveFileName = saveFileName;
    }

    public void createNewSaveFile() {
        if(saveFile != null) {
            return;
        }

        File file = new File(Config.jarPath);
        File folder = file.getParentFile();
        System.out.println(file + "'s parent is -> " + folder + " which is " + (folder.isDirectory()? "a folder" :
                "not a folder"));
        saveFile = new File(folder + "/" + saveFileName);

        boolean isNew = false;
        try {
            isNew = saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(isNew) {
            serialize();
        }
    }

    public boolean savetoFile() {
        if(!serialize()) {
            System.out.println("Failed save.");
            return false;
        }

        return true;
    }

    /**
     * Write SaveData object to File. Validate File.
     */
    private boolean serialize() {
        createNewSaveFile();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile, false));

            JsonObject obj = new JsonObject();
            obj.addProperty("levelProgress", saveData.getLevelProgress());

            bw.write(obj.toString());
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return deserialize();
    }

    /**
     * Read from File. Validate data. Record to SaveData object.
     */
    public boolean deserialize() {
        createNewSaveFile();

        // Initialize temp file
        new AFileReader() {
            @Override
            public boolean read() {
                try {
                    // Write to temporary file
                    // Create Input stream
                    InputStream is = new FileInputStream(saveFile);
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader r = new BufferedReader(isr);

                    JsonElement element = JsonParser.parseReader(r);

                    if(element.isJsonNull()) {
                        return false;
                    }

                    JsonObject frames = element.getAsJsonObject();
                    JsonElement frameObj = frames.get("levelProgress");

                    saveData.setLevelProgress(frameObj.getAsInt());

                    r.close();
                    isr.close();
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                return true;
            }
        };

        return true;
    }

}
