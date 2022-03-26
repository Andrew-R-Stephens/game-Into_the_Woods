package utils.files;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * Will contain references to all resources stored. Id's will be granted to all resources, grouped based on directory
 * type and named based on the file name
 */
public class Resources {

    /**
     * The constant imagesFiles.
     */
    private static final Map<String, BufferedImage> imagesFiles = new HashMap<>();
    /**
     * The constant audioFiles.
     */
    private static final Map<String, Clip> audioFiles = new HashMap<>();
    /**
     * The constant textFiles.
     */
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

        loadImageFiles();
        loadAudioFiles();
        loadTextFiles();

        System.out.println(this);

    }

    /**
     * Loads a list of Image Resources listed from File
     */
    public void loadImageFiles() {

        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "testbutton.png",
                "avatar.png",
                "backgroundImage.png"};

        for(String fileName : fileNames) {
            imagesFiles.put(fileName.split("\\.")[0], loadImageFile(fileName));
        }

        for(String fileName: fileNames) {
            if(imagesFiles.get(fileName) != null) {
                System.out.println(imagesFiles.get(fileName.split("\\.")[0]).getWidth());
            }
        }
    }

    /**
     * Load image file buffered image.
     *
     * @param fileName the file name
     * @return the buffered image
     */
    public BufferedImage loadImageFile(String fileName) {
        InputStream resourceBuff = Resources.class.getResourceAsStream("/images/" + fileName);
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
    public void loadAudioFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "buttonclick.wav"
        };

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            audioFiles.put(rawName, loadAudioFile(fileName));
        }

        for(String fileName: fileNames) {
            if(audioFiles.get(fileName) != null) {
                System.out.println(audioFiles.keySet());
            }
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

        InputStream resourceBuff = Resources.class.getResourceAsStream("/audio/" + fileName);

        if (resourceBuff != null) {
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        return clip;
    }

    /**
     * Loads a list of TextFile Resources listed from File
     */
    public void loadTextFiles() {
        //TODO : Create list of files with image resource names instead of this hardcoding
        String[] fileNames = {
                "Preferences.xml",
                "SaveData.xml",
                "movingButtonSheet.json"
        };

        for(String fileName: fileNames) {
            textFiles.put(fileName.split("\\.")[0], loadTextFile(fileName));
        }

    }

    /**
     * Load text file file.
     *
     * @param fileName the file name
     * @return the file
     */
    public File loadTextFile(String fileName) {

        if (fileName == null) {
            throw new NullPointerException("File name cannot be null");
        }

        return new File(fileName);
    }


    public static BufferedImage getImage(String imageKey) {
        return imagesFiles.get(imageKey);
    }

    public static File getFile(String fileKey) {
        System.out.println(textFiles.get(fileKey));
        return textFiles.get(fileKey);
    }


    /**
     * Displays the items read from storage
     * @return
     */
    public String toString() {
        String s = "Image Files:\n";
        for(String key: imagesFiles.keySet()) {
            s += "\t" + key + " " + (imagesFiles.get(key) != null) + "\n";
        }

        s += "Audio Files:\n";
        for(String key: audioFiles.keySet()) {
            s += "\t" + key + " " + (audioFiles.get(key) != null) + "\n";
        }
        s += "Text Files:\n";
        for(String key: textFiles.keySet()) {
            s += "\t" + key + " " + (textFiles.get(key) != null) + "\n";
        }

        return s;
    }

}
