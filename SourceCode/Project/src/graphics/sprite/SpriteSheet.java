package graphics.sprite;

import Utils.FileLoader;
import Utils.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class SpriteSheet extends FileLoader implements ImageLoader, SpriteSheetParser {

    private BufferedImage sheet;

    public SpriteSheet(String filename) throws FileNotFoundException {
        setFile(filename);
    }

    @Override
    public void loadImage() throws Exception {

        String tempName = file.getName().toLowerCase();
        if(! (tempName.endsWith(".png") || tempName.endsWith(".jpg") || tempName.endsWith(".jpeg"))) {
            throw new Exception("Cannot load file. File extension does not match required type.");
        }

        sheet = ImageIO.read(file);
    }

    @Override
    public BufferedImage grabImage(int x, int y, int w, int h) {
        return null;
    }
}
