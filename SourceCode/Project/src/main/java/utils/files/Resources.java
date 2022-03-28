package utils.files;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
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
    private final String path_textFile = "files/";

    private static final Map<String, BufferedImage> imagesFiles = new HashMap<>();
    private static final Map<String, Clip> audioFiles = new HashMap<>();
    private static final Map<String, File> textFiles = new HashMap<>();

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

        System.out.println(this);

    }

    /**
     * Loads a list of Image Resources listed from File
     */
    private void loadImageFiles() {

        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "dirt.png",
                "testbutton.png",
                "testbutton2.png",
                "avatar.png",
                "backgroundImage.png"};

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
                "buttonclick.wav"
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
    public Clip loadAudioFile(String fileName) {

        Clip clip = null;

        InputStream resourceBuff = Resources.class.getResourceAsStream(path_audio + fileName);

        if (resourceBuff != null) {
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        return clip;
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
                "Preferences.xml",
                "SaveData.xml",
                "movingButtonSheet.json",
                "hfafsadi.xml"
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


    public static BufferedImage getImage(String imageKey) {
        return imagesFiles.get(imageKey);
    }

    public static File getTextFile(String fileKey) {
        return textFiles.get(fileKey);
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

        return s;
    }

}
