package utils.files;

import graphics.views.SpriteSheet;
import graphics.views.SpriteSheetParser;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Will contain references to all resources stored. Id's will be granted to all resources, grouped based on directory
 * type and named based on the file name
 */
public class Resources {

    private final String PATH_META_INF = "META_INF.xml";

    private final String path_images = "/images/";
    private final String path_audio = "/audio/";
    private static final String path_textFile = "files/";
    private final String path_font = "fonts/";

    private static final Map<String, BufferedImage> imagesFiles = new HashMap<>();
    private static final Map<String, String> audioFiles = new HashMap<>();
    private static final Map<String, File> textFiles = new HashMap<>();
    private static final Map<String, Font> fontFiles = new HashMap<>();

    /**
     * Instantiates a new Resources.
     */
    public Resources() {
    }

    /**
     * Init.
     */
    public void init() {

        loadMetaInf();

        loadImageFiles();
        loadAudioFiles();
        loadTextFiles();
        loadFontFiles();

        System.out.println(this);

    }

    /**
     * Loads a list of Image Resources listed from File
     */
    private void loadImageFiles() {

        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "avatar.png",
                "avatar2.png",
                "key.png",
                "button_hrect.png",
                "button_square.png",
                "mockPlatformV2.png",
                "dirt.png",
                "spikes.png",
                "menubackground.png",
                "button_spritesheet.png",
                "avatarrun_spritesheet.png",
                "avatarrun_spritesheet2.png"
        };

        for(String fileName : fileNames) {
            imagesFiles.put(fileName.split("\\.")[0], loadImageFile(fileName));
        }

    }

    /**
     * Load image file buffered image.
     *
     * @param fileName the file name
     * @return the buffered image
     */
    public BufferedImage loadImageFile(String fileName) {
        InputStream resourceBuff = Resources.class.getResourceAsStream(path_images + fileName);
        try {
            if(resourceBuff != null) {
                return ImageIO.read(resourceBuff);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Loads a list of Audio Resources listed from File
     */
    private void loadAudioFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "buttonclick.mp3",
                "mainmenu.mp3",
                "game.mp3"
        };

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            audioFiles.put(rawName, loadAudioFile(fileName));
        }

    }

    /**
     * Load audio file clip.
     *
     * @param fileName the file name
     * @return the clip
     */
    public String loadAudioFile(String fileName) {

        //Clip clip = null;
        String fullPath = path_audio + fileName;

        InputStream resourceBuff = Resources.class.getResourceAsStream(fullPath);

        if(resourceBuff == null) {
            return null;
        }

        return fullPath;

    }

    private void loadMetaInf() {
        loadTextFile(PATH_META_INF);
    }

    /**
     * Loads a list of TextFile Resources listed from File
     */
    private void loadTextFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "avatarrun_spritesheet.json",
                "avatarrun_spritesheet2.json",
                "button_spritesheet.json",
                "colors.xml",
                "Preferences.xml",
                "SaveData.xml"
        };

        for(String fileName: fileNames) {
            textFiles.put(fileName, loadTextFile(fileName));
        }

    }

    /**
     * Load text file file.
     *
     * @param fileName the file name
     * @return the file
     */
    public File loadTextFile(String fileName) {
        String  name = fileName.split("\\.")[0],
                type = fileName.split("\\.")[1];

        File file = null;
        try {
            file = File.createTempFile(name, type);

            InputStream is =
                    AFileReader.class.getClassLoader().getResourceAsStream(path_textFile + fileName);
            if(is == null) {
                return null;
            }
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader r = new BufferedReader(isr);
            FileWriter writer = new FileWriter(file);

            String line;
            while (((line = r.readLine())) != null) {
                writer.write(line + "\n");
            }

            writer.close();
            r.close();
            isr.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private void loadFontFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "bahnschrift.ttf"
        };

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            fontFiles.put(rawName, loadFontFile(fileName));
        }
    }

    /**
     * Load text file file.
     *
     * @param fileName the file name
     * @return the file
     */
    public Font loadFontFile(String fileName) {
        InputStream is = AFileReader.class.getClassLoader().getResourceAsStream(path_font + fileName);
        if(is == null) {
            return null;
        }

        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    public static SpriteSheet loadSpriteSheet(String name) {
        String fileName = name + ".json";
        if(textFiles.get(fileName) == null) {
            System.out.println("No spritesheet data file of that name!");
        }
        if(imagesFiles.get(name) == null) {
            System.out.println("No spritesheet image file of that name!");
        }

        SpriteSheetParser parser = new SpriteSheetParser(path_textFile + fileName);
        SpriteSheet spriteSheet = parser.getSpriteSheet();
        spriteSheet.addReferenceImage(getImage(name));

        return spriteSheet;
    }

    public static BufferedImage getImage(String imageKey) {
        return imagesFiles.get(imageKey);
    }

    public static File getTextFile(String fileKey) {
        return textFiles.get(fileKey);
    }

    public static Font getFont(String fontKey) {
        return fontFiles.get(fontKey);
    }

    public static synchronized Player playAudio_Player(String audioKey) {

        // create AudioInputStream object
        InputStream resourceBuff = Resources.class.getResourceAsStream(audioFiles.get(audioKey));

        if(resourceBuff == null) {
            System.out.println("Buffer is null");
            return null;
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(resourceBuff);

        // Create a Player object that realizes the audio
        Player p = null;
        try {
            p = new Player(bufferedInputStream);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

        Player finalP = p;
        Thread t = new Thread(() -> {
            try {
                if (finalP != null) {
                    finalP.play(); // Start the music
                }
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        });
        t.start();

        return p;

    }

    /**
     * Displays the items read from storage
     * @return
     */
    public String toString() {
        String s = "Image Files:\n";
        for(String key: imagesFiles.keySet()) {
            s +=    "\t" + key + " " + (imagesFiles.get(key) != null) + "\n";
        }

        s += "Audio Files:\n";
        for(String key: audioFiles.keySet()) {
            s +=    "\t" + key + " " + (audioFiles.get(key) != null) + "\n";
        }
        s += "Text Files:\n";
        for(String key: textFiles.keySet()) {
            s +=    "\t" + key + " " +
                    (textFiles.get(key) != null) + " " + "\n";
        }

        s += "Text Files:\n";
        for(String key: textFiles.keySet()) {
            s +=    "\t" + key + " " +
                    (textFiles.get(key) != null) + " " + "\n";
        }

        s += "Font Files:\n";
        for(String key: fontFiles.keySet()) {
            s +=    "\t" + key + " " +
                    (fontFiles.get(key) != null) + " " + "\n";
        }

        return s;
    }

}
