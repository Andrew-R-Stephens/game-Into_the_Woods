package models.utils.files;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p></p>
 */
public class MetaDataParser {

    private final String jsonFile;

    private HashMap<String, String> paths = new HashMap<>();
    private HashMap<String, ArrayList<String>> fileNames = new HashMap<>();

    /**
     * <p></p>
     * @param jsonFile -
     */
    public MetaDataParser(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    /**
     * <p></p>
     */
    public void init() {

        new AFileReader() {
            @Override
            public boolean read() {
                try {
                    // Initialize temp file
                    file = File.createTempFile(jsonFile.split("\\.")[0], ".json");

                    // Write to temporary file
                    // Create Input stream
                    InputStream is =
                            AFileReader.class.getClassLoader().getResourceAsStream(jsonFile);
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader r = new BufferedReader(isr);

                    JsonElement element = JsonParser.parseReader(r);
                    JsonObject parent = element.getAsJsonObject();
                    registerElement(parent, "text");
                    registerElement(parent, "images");
                    registerElement(parent, "audio");
                    registerElement(parent, "fonts");

                    r.close();
                    isr.close();
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };
    }

    /**
     * <p></p>
     * @param type -
     * @return
     */
    public String getPath(String type) {
        return paths.get(type);
    }

    /**
     * <p></p>
     * @param type -
     * @return
     */
    public ArrayList<String> getFileNames(String type) {
        return fileNames.get(type);
    }

    /**
     * <p></p>
     * @param parent -
     * @param memberName -
     */
    private void registerElement(JsonObject parent, String memberName) {
        JsonElement typeElement = parent.get(memberName);
        JsonObject child = typeElement.getAsJsonObject();
        paths.put(memberName, child.get("path").getAsString());
        ArrayList<String> names = new ArrayList<>();
        for(JsonElement e: child.get("names").getAsJsonArray()) {
            names.add(e.getAsString());
        }
        fileNames.put(memberName, names);
    }

}
