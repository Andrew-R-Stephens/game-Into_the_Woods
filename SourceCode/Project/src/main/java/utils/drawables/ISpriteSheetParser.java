package utils.drawables;

import java.awt.image.BufferedImage;

/**
 * TODO: Add description
 */
public interface ISpriteSheetParser {

    /**
     * Grab image buffered image.
     *
     * @param currentFrame the current frame
     * @return the buffered image
     */
    BufferedImage grabImage(int currentFrame);

}
