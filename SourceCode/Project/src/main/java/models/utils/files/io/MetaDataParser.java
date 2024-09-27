package models.utils.files.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>MetaDataParser class accepts the metadata.json file. This file is used to list all local files within the
 * classpath, register them to a hashmap, and allow this data to be transferred into the Resources for further
 * processing and resource allocation.</p>
 * @author Andrew Stephens
 */
public class MetaDataParser {

    /**<p>The path of the Json file</p>*/
    private final String jsonFile;

    /**<p>The resource paths of all data types.</p>*/
    private HashMap<String, String> paths = new HashMap<>();
    /**<p>The arraylist of file names based on the file type.</p>*/
    private HashMap<String, ArrayList<String>> fileNames = new HashMap<>();

    /**
     * <p>Creates a new MetaDataParser and stores the path of the metadata.json file.</p>
     * @param jsonFile The metadata.json file path
     */
    public MetaDataParser(String jsonFile) {
        this.jsonFile = jsonFile;
    }

    /**
     * <p>Reads the metadata.json file and uses Gson to parse through the content.</p>
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
     * <p>Gets the path of the specific Resource type.</p>
     * @param type The type of resource requested
     * @return The path of the resource type
     */
    public String getPath(String type) {
        return paths.get(type);
    }

    /**
     * <p>Gets the file names of the type of resource that is requested.</p>
     * @param type The type of resource.
     * @return A list of the names of the files of the specified resource type.
     */
    public ArrayList<String> getFileNames(String type) {
        return fileNames.get(type);
    }

    /**
     * <p>Registers the Element of the specified attributes passed.</p>
     * @param parent The parent Json Object
     * @param memberName The Json Object's attribute's member name
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
