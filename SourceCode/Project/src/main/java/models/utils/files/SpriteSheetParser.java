package models.utils.files;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.sprites.Sprite;
import models.sprites.SpriteSheet;

import java.io.*;
import java.util.ArrayList;

/**
 * <p></p>
 */
public class SpriteSheetParser {

    private SpriteSheet spriteSheet;

    private final String jsonFile;

    /**
     * <p></p>
     * @param jsonFile -
     */
    public SpriteSheetParser(String jsonFile) {
        this.jsonFile = jsonFile;

        spriteSheet = processSpriteSheet();
    }

    /**
     * <p></p>
     * @return
     */
    private SpriteSheet processSpriteSheet() {

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
                    if(is == null) {
                        return false;
                    }
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader r = new BufferedReader(isr);

                    ArrayList<Sprite> sprites = new ArrayList<>();

                    JsonElement element = JsonParser.parseReader(r);
                    JsonObject frames = element.getAsJsonObject();
                    JsonElement frameObj = frames.get("frames");

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
                    }

                    spriteSheet = new SpriteSheet(sprites);

                    r.close();
                    isr.close();
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };

        return spriteSheet;
    }

    /**
     * <p></p>
     * @return
     */
    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
}
