package models.utils.resources;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import models.sprites.SpriteSheet;
import models.utils.files.AFileReader;
import models.utils.files.MetaDataParser;
import models.utils.files.SpriteSheetParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Resources class
 *
 * First, this class checks for different types of resources along their respective paths. Paths vary between IDE and
 * Jar environments, so those specified within this class respect the paths of both environments.
 *
 * If found, resources are either stored into HashMap lists as objects or as referential paths.
 *
 * Resources are called on by reference, ensuring for a singleton instance of the resource existing in memory. This
 * allows for the system to use that resource multiple times without requiring resource overhead.
 */
public class Resources {

    private final String PATH_META = "metadata/metadata.json";

    private static String path_textFile;
    private String path_images, path_audio, path_fonts;

    private static final Map<String, BufferedImage> imagesFiles = new HashMap<>();
    private static final Map<String, String> audioFiles = new HashMap<>();
    private static final Map<String, File> textFiles = new HashMap<>();
    private static final Map<String, Font> fontFiles = new HashMap<>();

    public void init() {

        // Initialize MetaData
        MetaDataParser metaData = new MetaDataParser(PATH_META);
        metaData.init();

        // Register paths
        path_textFile = metaData.getPath("text");
        path_images = metaData.getPath("images");
        path_audio = metaData.getPath("audio");
        path_fonts = metaData.getPath("fonts");
        
        // Register Texts
        loadTextFiles(registerFiles(metaData, "text"));
        // Register Images
        loadImageFiles(registerFiles(metaData, "images"));
        // Register Audio
        loadAudioFiles(registerFiles(metaData, "audio"));
        // Register Fonts
        loadFontFiles(registerFiles(metaData, "fonts"));

        System.out.println(this);

    }

    private String[] registerFiles(MetaDataParser metaData, String tag) {
        ArrayList<String> tempList = metaData.getFileNames(tag);
        String[] tempArr = new String[tempList.size()];
        tempList.toArray(tempArr);

        return tempArr;
    }

    private void loadImageFiles(String[] fileNames) {

        for(String fileName : fileNames) {
            imagesFiles.put(fileName.split("\\.")[0], loadImageFile(fileName));
        }

    }

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


    private void loadAudioFiles(String[] fileNames) {

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            audioFiles.put(rawName, loadAudioFile(fileName));
        }

    }

    public String loadAudioFile(String fileName) {

        //Clip clip = null;
        String fullPath = path_audio + fileName;

        InputStream resourceBuff = Resources.class.getResourceAsStream(fullPath);

        if(resourceBuff == null) {
            return null;
        }

        return fullPath;

    }

    private void loadTextFiles(String[] fileNames) {

        for(String fileName: fileNames) {
            textFiles.put(fileName, loadTextFile(fileName));
        }

    }

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

    private void loadFontFiles(String[] fileNames) {

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            fontFiles.put(rawName, loadFontFile(fileName));
        }
    }

    public Font loadFontFile(String fileName) {
        InputStream is = AFileReader.class.getClassLoader().getResourceAsStream(path_fonts + fileName);
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

    public static SpriteSheet getSpriteSheet(String name) {
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

    public static synchronized Player playAudio(String audioKey) {

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

        s += "Font Files:\n";
        for(String key: fontFiles.keySet()) {
            s +=    "\t" + key + " " +
                    (fontFiles.get(key) != null) + " " + "\n";
        }

        return s;
    }

}
