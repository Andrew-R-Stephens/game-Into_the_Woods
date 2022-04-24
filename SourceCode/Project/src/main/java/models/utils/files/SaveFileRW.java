package models.utils.files;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.utils.config.Config;
import models.utils.config.SaveData;
import models.utils.resources.Resources;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class SaveFileRW {

    private final SaveData saveData;
    private final File saveFile;

    /**
     * Construct the SaveFileRW with persistent Files.
     * @param saveData - where we pull SaveData from or write SaveData to
     * @param saveFile - the File on disk
     */
    public SaveFileRW(SaveData saveData, File saveFile) {
        this.saveData = saveData;
        this.saveFile = saveFile;
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
        File file = new File(Config.jarPath);
        System.out.println(file.getName());

        /*
        URL url =
                SaveFileRW.class.getClassLoader().getResource(Resources.path_textFile + "savedata.json");
        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));

            JsonObject obj = new JsonObject();
            obj.addProperty("levelProgress", 124);

            bw.write(obj.toString());
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
*/

        return true;//deserialize();
    }

    /**
     * Read from File. Validate data. Record to SaveData object.
     */
    public boolean deserialize() {
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

    public boolean validate() {
        return deserialize();
    }

}
