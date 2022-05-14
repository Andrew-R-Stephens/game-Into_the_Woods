package models.utils.resources;

import models.sprites.SpriteSheet;
import models.utils.audio.SuperPlayer;
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
 * <p>First, this class checks for different types of resources along their respective paths. Paths vary between IDE and
 * Jar environments, so those specified within this class respect the paths of both environments.</p>
 * <p>If found, resources are either stored into HashMap lists as objects or as referential paths.</p>
 * <p>Resources are called on by reference, ensuring for a singleton instance of the resource existing in memory. This
 * allows for the system to use that resource multiple times without requiring resource overhead.</p>
 * @author Andrew Stephens
 */
public class Resources {

    /**<p>The metadata.json file classpath.</p>*/
    private String PATH_META = "metadata/metadata.json";

    /**<p>The classpath to all text files.</p>*/
    public static String path_textFile;
    /**<p>The classpath for the resource.</p>*/
    private String path_images, path_audio, path_fonts;

    /**<p>The Hashmap of String keys obtaining specific BufferedImage references.</p>*/
    private final Map<String, BufferedImage> imagesFiles = new HashMap<>();
    /**<p>The Hashmap of String keys obtaining specific Audio File classpaths.</p>*/
    private final Map<String, String> audioFiles = new HashMap<>();
    /**<p>The Hashmap of String keys obtaining specific File references.</p>*/
    private final Map<String, File> textFiles = new HashMap<>();
    /**<p>The Hashmap of String keys obtaining specific Font references.</p>*/
    private final Map<String, Font> fontFiles = new HashMap<>();

    /**
     * <p>Parses the metadata.json file and registers specified resources locally.</p>
     * <p>The metadata file referenecs all items in the Resources directory, including path names.</p>
     * <p>Those files that are specified within the metadata file are stored referentially or by path depending on the resource type.</p>
     */
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

    /**
     * <p>Retrieves file path data held within the MetaDataParser.</p>
     * @param metaData The MetaDataParser object which has processed the metadata already.
     * @param tag The key which holds data for a specified file type.
     * @return An arraylist of file paths.
     */
    private String[] registerFiles(MetaDataParser metaData, String tag) {
        ArrayList<String> tempList = metaData.getFileNames(tag);
        String[] tempArr = new String[tempList.size()];
        tempList.toArray(tempArr);

        return tempArr;
    }

    /**
     * <p>Registers all image paths, loads image and stores a reference to the image into a Map.</p>
     * @param fileNames The file names of images requested to be stored.
     */
    private void loadImageFiles(String[] fileNames) {
        for(String fileName : fileNames) {
            imagesFiles.put(fileName.split("\\.")[0], loadImageFile(fileName));
        }
    }

    /**
     * <p>Loads the specified image file to see if it exists in the Resource directory. If it exists, the file path is not null.</p>
     * @param fileName The specific file name.
     * @return BufferedImage file located from the Resources directory. Or null if the file could not be found.
     */
    public BufferedImage loadImageFile(String fileName) {
        InputStream resourceBuff = Resources.class.getResourceAsStream(path_images + fileName);

        if(resourceBuff == null) {
            return null;
        }

        try {
            BufferedImage image = ImageIO.read(resourceBuff);
            resourceBuff.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * <p>Registers all audio paths into a Map.</p>
     * @param fileNames The file names of audio requested to be stored.
     */
    private void loadAudioFiles(String[] fileNames) {

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            audioFiles.put(rawName, loadAudioFile(fileName));
        }

    }

    /**
     * <p>Loads the specified audio file to see if it exists in the Resource directory. If it exists, the file path is not null.</p>
     * @param fileName The specific file name.
     * @return the path to the audio file.
     */
    public String loadAudioFile(String fileName) {

        String fullPath = path_audio + fileName;

        InputStream resourceBuff = Resources.class.getResourceAsStream(fullPath);

        if(resourceBuff == null) {
            return null;
        }

        try {
            resourceBuff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullPath;
    }

    /**
     * <p>Registers all text files into a Map.</p>
     * @param fileNames The file names of text files requested to be stored.
     */
    private void loadTextFiles(String[] fileNames) {
        for(String fileName: fileNames) {
            textFiles.put(fileName, loadTextFile(fileName));
        }
    }

    /**
     * <p>Loads the specified text file to see if it exists in the Resource directory. If it exists, the file is not null.</p>
     * <p>Reads in the text file and stores the data into a temporary file which gets returned.</p>
     * @param fileName The specific text file name.
     * @return the text file written to memory.
     */
    public File loadTextFile(String fileName) {
        String  name = fileName.split("\\.")[0],
                type = fileName.split("\\.")[1];

        File file = null;
        try {
            file = File.createTempFile(name, "." + type);

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

    /**
     * <p>Registers all Font files into a Map.</p>
     * @param fileNames The file names of Font files requested to be stored.
     */
    private void loadFontFiles(String[] fileNames) {

        for(String fileName: fileNames) {
            String rawName = fileName.split("\\.")[0];
            fontFiles.put(rawName, loadFontFile(fileName));
        }
    }

    /**
     * <p>Loads the specified Font file to see if it exists in the Resource directory. If it exists, the Font is not null.</p>
     * @param fileName The specific Font file name.
     * @return the Font file written to memory.
     */
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

    /**
     * <p>Retrieves a SpriteSheet which is stored in the Map. Uses the references from Text Files and BufferedImage Maps.</p>
     * @param spriteSheetName the non-extension name of the SpriteSheet requested.
     * @return A reference to the SpriteSheet requested.
     */
    public SpriteSheet getSpriteSheet(String spriteSheetName) {
        String fileName = spriteSheetName + ".json";
        if(textFiles.get(fileName) == null) {
            System.out.println("No spritesheet data file of that name!");
        }
        if(imagesFiles.get(spriteSheetName) == null) {
            System.out.println("No spritesheet image file of that name!");
        }

        SpriteSheetParser parser = new SpriteSheetParser(path_textFile + fileName);
        SpriteSheet spriteSheet = parser.getSpriteSheet();
        spriteSheet.setReferenceImage(getImage(spriteSheetName));

        return spriteSheet;
    }

    /**
     * <p>Retrieves a BufferedImage which is stored in the Map</p>
     * @param imageKey the non-extension file name of the image requested.
     * @return A reference to the BufferedImage requested.
     */
    public BufferedImage getImage(String imageKey) {
        return imagesFiles.get(imageKey);
    }

    /**
     * <p>Retrieves a File which is stored in the Map</p>
     * @param fileKey the non-extension file name of the text file requested.
     * @return A reference to the File requested.
     */
    public File getTextFile(String fileKey) {
        return textFiles.get(fileKey);
    }

    /**
     * <p>Retrieves a Font which is stored in the Map</p>
     * @param fontKey the non-extension file name of the font requested.
     * @return A reference to the Font requested.
     */
    public Font getFont(String fontKey) {
        return fontFiles.get(fontKey);
    }

    /**
     * <p>Called externally to start a new concurrent audio thread.</p>
     * <br>
     * <p>Uses stored audio file paths to in-stream the data as an audio file. Sets the file into an SuperPlayer object.</p>
     * <p>The SuperPlayer object is returned for control over object permanence.</p>
     * @param audioKey The audio file name without extension.
     * @return The SuperPlayer that retains data for a specific audio file.
     */
    public synchronized SuperPlayer getAudioPlayer(String audioKey) {
        // Create a Player object that realizes the audio
        return new SuperPlayer(audioFiles.get(audioKey));
    }

    /**
     * <p>Called externally to start a new concurrent audio thread.</p>
     * <br>
     * <p>Uses stored audio file paths to in-stream the data as an audio file. Sets the file into an SuperPlayer object.</p>
     * <p>The SuperPlayer object is returned for control over object permanence.</p>
     * <p>Starts the SuperPlayer automatically.</p>
     * @param audioKey The audio file name without extension.
     * @return The SuperPlayer that retains data for a specific audio file.
     */
    public synchronized SuperPlayer playAudio(String audioKey) {
        // Create a Player object that realizes the audio
        SuperPlayer p = new SuperPlayer(audioFiles.get(audioKey));

        // Start the audio
        p.play();

        return p;

    }

    /**
     * <p>Checks if all files exist within the Map objects. Files do not exist or could not be found if they are null
     * .</p>
     * @return Brief data about the resource file references.
     */
    public String toString() {
        StringBuilder s = new StringBuilder("Image Files:\n");
        for(String key: imagesFiles.keySet()) {
            s.append("\t").append(key).append(" ").append(imagesFiles.get(key) != null).append("\n");
        }

        s.append("Audio Files:\n");
        for(String key: audioFiles.keySet()) {
            s.append("\t").append(key).append(" ").append(audioFiles.get(key) != null).append("\n");
        }

        s.append("Text Files:\n");
        for(String key: textFiles.keySet()) {
            s.append("\t").append(key).append(" ").append(textFiles.get(key) != null).append(" ").append("\n");
        }

        s.append("Font Files:\n");
        for(String key: fontFiles.keySet()) {
            s.append("\t").append(key).append(" ").append(fontFiles.get(key) != null).append(" ").append("\n");
        }

        return s.toString();
    }

}
