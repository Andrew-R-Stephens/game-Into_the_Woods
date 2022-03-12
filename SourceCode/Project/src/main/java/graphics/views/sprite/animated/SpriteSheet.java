package graphics.views.sprite.animated;

import utils.files.AFileLoader;
import utils.drawables.IImageLoader;
import utils.drawables.ISpriteSheetParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * TODO: Add description
 */
public class SpriteSheet extends AFileLoader implements IImageLoader, ISpriteSheetParser {

    private BufferedImage sheet;

    private int keyFrameWidth = 0, getKeyFrameHeight = 0;

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
        BufferedImage img = sheet.getSubimage(currentFrame* keyFrameWidth, currentFrame* getKeyFrameHeight, keyFrameWidth, getKeyFrameHeight);

        return img;
    }
}
