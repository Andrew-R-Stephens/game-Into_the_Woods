package models.prototypes.level;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import models.prototypes.actor.AActor;
import models.utils.config.Config;
import models.utils.files.AFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.prototypes.level.LevelData.LevelModel.Prop;
import models.prototypes.level.LevelData.LevelModel.Prop.Dims;
import models.prototypes.level.LevelData.LevelModel.Prop.Coords;
import models.prototypes.level.LevelData.LevelModel.Prop.Motion;
import models.prototypes.level.LevelData.LevelModel.StartOrigin;
import models.utils.physics.APhysics;

public class LevelData {

    public LevelModels levelModels = new LevelModels();

    public LevelData(String fileName) {
        parseLevels(fileName);

        String outName = "levels_out.json";
        System.out.println(outName);
        serialize(outName);
    }

    /**
     * <p>Processes the Json file, stores the data into individual Sprites, and stores the Sprites into a SpriteSheet.
     * </p>
     * @return The completed SpriteSheet.
     */
    private void parseLevels(String jsonFile) {

        new AFileReader() {
            @Override
            public boolean read() {
                try {
                    // Create Input stream
                    InputStream is =
                            AFileReader.class.getClassLoader().getResourceAsStream(jsonFile);
                    if(is == null) {
                        System.out.println("Failed to read levels file");
                        return false;
                    }
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader r = new BufferedReader(isr);

                    JsonElement element = JsonParser.parseReader(r);

                    JsonObject root = element.getAsJsonObject();
                    JsonElement levels = root.get("levels");

                    if(levels.isJsonArray()) {
                        JsonArray frameArray = levels.getAsJsonArray();
                        for(int i = 0; i < frameArray.size(); i++) {
                            JsonObject attrObj = frameArray.get(i).getAsJsonObject();

                            int id = attrObj.get("id").getAsInt();
                            String name = attrObj.get("name").toString();

                            ArrayList<String> backgrounds = new ArrayList<>();
                            JsonArray backgroundImages = attrObj.get("backgroundImages").getAsJsonArray();
                            for(int j = 0; j < backgroundImages.size(); j++) {
                                backgrounds.add(backgroundImages.get(j).getAsString());
                            }

                            JsonObject typeImages = attrObj.get("typeImages").getAsJsonObject();
                            HashMap<String, ArrayList<String>> typeImagesMap = new Gson().fromJson(typeImages, HashMap.class);

                            JsonObject startOrigin = attrObj.get("startOrigin").getAsJsonObject();
                            int x = startOrigin.get("x").getAsInt();
                            int y = startOrigin.get("y").getAsInt();

                            //props
                            JsonArray propsIn = attrObj.get("props").getAsJsonArray();
                            ArrayList<LevelModel.Prop> propsOut = new ArrayList<>(propsIn.size());
                            for(int k = 0; k < propsIn.size(); k++) {
                                JsonObject propIn = propsIn.get(k).getAsJsonObject();

                                boolean hasGravity = false;
                                if(propIn.get("hasGravity") != null) {
                                 hasGravity = propIn.get("hasGravity").getAsBoolean();
                                }

                                int maxCycles = 0;
                                if(propIn.get("maxCycles") != null) {
                                    maxCycles = propIn.get("maxCycles").getAsInt();
                                }

                                JsonObject coordsIn = propIn.get("coords").getAsJsonObject();
                                int pX = coordsIn.get("x").getAsInt();
                                int pY = coordsIn.get("y").getAsInt();
                                Coords coords = new Coords(pX, pY);

                                JsonObject dimsIn = propIn.get("dims").getAsJsonObject();
                                int pW = dimsIn.get("w").getAsInt();
                                int pH = dimsIn.get("h").getAsInt();
                                Dims dims = new Dims(pW, pH);

                                int vX = 0;
                                int vY = 0;
                                if(propIn.get("motion") != null) {
                                    JsonObject motionIn = propIn.get("motion").getAsJsonObject();
                                    vX = motionIn.get("vX").getAsInt();
                                    vY = motionIn.get("vY").getAsInt();
                                }
                                Motion motion = new Motion(vX, vY);

                                String type = propIn.get("type").getAsString();

                                Prop prop = new Prop(type, coords, dims, motion, hasGravity, maxCycles);

                                propsOut.add(prop);
                            }

                            LevelModel levelModel = new LevelModel(
                                    id, name, backgrounds, typeImagesMap, propsOut);
                            levelModel.startOrigin = new StartOrigin(x, y);
                            levelModels.levels.add(levelModel);

                        }
                    }
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

    private void serialize(String saveFile) {
        createNewSaveFile(saveFile);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile, false));

            JsonObject root = new JsonObject();
            Map<String, JsonElement> rootMap = root.asMap();

            JsonArray levels = new JsonArray();
            for(int i = 0; i < levelModels.levels.size(); i++) {
                JsonObject level = new JsonObject();

                // Add props to level
                JsonArray props = new JsonArray();
                for(Prop p: levelModels.levels.get(i).props) {
                    JsonObject prop = new JsonObject();
                    prop.addProperty("type", p.type);

                    JsonObject coords = new JsonObject();
                    coords.addProperty("x", (int)AActor.roundCoordinate(p.coords.x));
                    coords.addProperty("y",  (int)AActor.roundCoordinate(p.coords.y));
                    prop.add("coords", coords);

                    JsonObject dims = new JsonObject();
                    dims.addProperty("w", (int)AActor.roundCoordinate(p.dims.w));
                    dims.addProperty("h", (int)AActor.roundCoordinate(p.dims.h));
                    prop.add("dims", dims);

                    if(p.motion.vX != 0 && p.motion.vY != 0) {
                        JsonObject motion = new JsonObject();
                        motion.addProperty("vX", p.motion.vX);
                        motion.addProperty("vY", p.motion.vY);
                        prop.add("motion", motion);
                    }

                    if(p.maxCycles != 1) {
                        prop.addProperty("maxCycles", p.maxCycles);
                    }

                    prop.addProperty("hasGravity", p.hasGravity);

                    props.add(prop);
                }
                level.add("props", props);

                levels.add(level);
            }

            rootMap.put("levels", levels);

            bw.write(root.toString());
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createNewSaveFile(String saveFileName) {

        File file = new File(Config.jarPath);
        File folder = file.getParentFile();
        File saveFile = new File(folder + "/" + saveFileName);

        boolean isNew = false;
        try {
            isNew = saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isNew) {
            serialize(saveFileName);
        }
    }


    public class LevelModels {

        @SerializedName("levels")
        public List<LevelModel> levels = new ArrayList<>(0);

    }

    public class LevelModel {

        public int id;
        public String name;
        public List<String> backgroundImages;
        public HashMap<String, ArrayList<String>> typeImages;
        public LevelModel.StartOrigin startOrigin;

        public List<LevelModel.Prop> props;

        public LevelModel(int id, String name,
                          List<String> backgroundImages,
                          HashMap<String, ArrayList<String>> typeImagesMap, List<LevelModel.Prop> props) {
            this.id = id;
            this.name = name;
            this.backgroundImages = backgroundImages;
            this.typeImages = typeImagesMap;
            this.props = props;
        }

        public static class StartOrigin {
            public int x;
            public int y;

            public StartOrigin(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public static class Prop {
            public boolean hasGravity;
            public String type;
            public Coords coords;
            public Dims dims;
            public Motion motion;
            public int maxCycles;

            public Prop(String type, Coords coords, Dims dims, Motion motion, boolean hasGravity, int maxCycles) {
                this.type = type;
                this.hasGravity = hasGravity;
                this.coords = coords;
                this.dims = dims;
                this.motion = motion;
                this.maxCycles = 1;
            }

            public static class Coords {
                public int x;
                public int y;

                public Coords(int x, int y) {
                    this.x = x;
                    this.y = y;
                }
            }

            public static class Dims {
                public int w;
                public int h;

                public Dims(int w, int h) {
                    this.w = w;
                    this.h = h;
                }
            }

            public static class Motion {
                public int vX;
                public int vY;

                public Motion(int vX, int vY) {
                    this.vX = vX;
                    this.vY = vY;
                }
            }
        }
    }
}
