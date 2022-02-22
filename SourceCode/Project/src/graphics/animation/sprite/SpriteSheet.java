package graphics.animation.sprite;

import utils.AFileLoader;
import utils.IImageLoader;
import utils.ISpriteSheetParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class SpriteSheet extends AFileLoader implements IImageLoader, ISpriteSheetParser {

    private BufferedImage sheet;

    private int w = 0, h = 0;

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
    public BufferedImage grabImage(int currentFrame) {
        BufferedImage img = sheet.getSubimage(currentFrame*w, currentFrame*h, w, h);

        return img;
    }
}
