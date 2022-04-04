package graphics.views;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import utils.files.AFileReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * TODO: Add description
 */
public class SpriteSheetParser {

    @SerializedName(value = "frames")
    private SpriteSheet spriteSheet;

    public SpriteSheetParser(String jsonFile) {

        AFileReader reader = new AFileReader(jsonFile) {
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

                    // Create Write stream and parse through
                    /*
                    FileWriter writer = new FileWriter(file);
                    String line;
                    while (((line = r.readLine())) != null) {
                        System.out.println(line);
                        writer.write(line + "\n");
                    }

                    writer.close();
                    */

                    ArrayList<Sprite> sprites = new ArrayList<>();

                    JsonElement element = JsonParser.parseReader(r);
                    JsonObject frames = element.getAsJsonObject();
                    JsonElement frameObj = frames.get("frames");

                    //System.out.println(frameObj);
                    if(frameObj.isJsonArray()) {
                        JsonArray frameArray = frameObj.getAsJsonArray();
                        for(int i = 0; i < frameArray.size(); i++) {
                            JsonObject attrObj = frameArray.get(i).getAsJsonObject();

                            String name = attrObj.get("filename").toString();
                            boolean rotated = attrObj.get("rotated").getAsBoolean();
                            boolean trimmed = attrObj.get("trimmed").getAsBoolean();
                            int duration = attrObj.get("duration").getAsInt();

                            JsonObject frame = attrObj.get("frame").getAsJsonObject();
                            int fx = frame.get("x").getAsInt();
                            int fy = frame.get("y").getAsInt();
                            int fw = frame.get("w").getAsInt();
                            int fh = frame.get("h").getAsInt();
                            int[] frameDim = new int[]{fx, fy, fw, fh};

                            JsonObject spriteSourceSize = attrObj.get("spriteSourceSize").getAsJsonObject();
                            int ssx = spriteSourceSize.get("x").getAsInt();
                            int ssy = spriteSourceSize.get("y").getAsInt();
                            int ssw = spriteSourceSize.get("w").getAsInt();
                            int ssh = spriteSourceSize.get("h").getAsInt();
                            int[] spriteDim = new int[]{ssx, ssy, ssw, ssh};

                            JsonObject sourceSize = attrObj.get("sourceSize").getAsJsonObject();
                            int sw = sourceSize.get("w").getAsInt();
                            int sh = sourceSize.get("h").getAsInt();
                            int[] srcDim = new int[]{sw, sh};

                            sprites.add(new Sprite(
                                    name,
                                    frameDim,
                                    rotated,
                                    trimmed,
                                    spriteDim,
                                    srcDim,
                                    duration
                            ));
                        }
                        //System.out.println(frameObj);
                    }

                    spriteSheet = new SpriteSheet(sprites);
                    System.out.println(spriteSheet);

                    r.close();
                    isr.close();
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };
        reader.read();
    }
}
