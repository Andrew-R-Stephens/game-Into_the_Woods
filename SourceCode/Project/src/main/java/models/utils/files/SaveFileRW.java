package models.utils.files;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.utils.config.Config;
import models.utils.config.SaveData;

import java.io.*;

/**
 * <p>SaveFileRW is granted access to the SaveData that's held in memory and updated internally, and processes that data
 * to Json format and writes it to a persistent external file. If the file is not found, the file is created.</p>
 * <p>The external save file is always created at the path of the JAR.</p>
 */
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

    /**
     * <p>Creates a new Save File if the save file does not exist.</p>
     * <p>The save file is always created at the same system path of the JAR.</p>
     */
    public void createNewSaveFile() {
        if(saveFile != null) {
            return;
        }

        File file = new File(Config.jarPath);
        File folder = file.getParentFile();
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

    /**
     * Serializes the save data to the save file.
     * @return True if the serialization was successful. False if failure.
     */
    public boolean savetoFile() {
        if(!serialize()) {
            System.out.println("Failed save.");
            return false;
        }

        return true;
    }

    /**
     * Write SaveData object to File. Validate File by deserializing the file and checking the data.
     * @return True is the file was written properly and is checked via deserialization.
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
     * Read from File and records to SaveData object.
     * @return False if the data cannot be found, if the file is not found, or if there is malformed data.
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
