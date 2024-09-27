package views.renders;

import models.utils.resources.Resources;
import views.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>A Platform is a physical barrier object. It is the fundamental piece that allows
 * the other actors to behave as if in a physical world.</p>
 * @author Andrew Stephens
 */
public class PlatformBodyTile {

    /**
     * The final drawn image
     */
    private BufferedImage image;

    public PlatformBodyTile(Resources resources, BufferedImage[] images) {
        createImage(images[1]);
    }

    public void createImage(BufferedImage body) {

        if(body != null) {
            Image tempBodyImage = body.getScaledInstance(
                    Tile.W,
                    Tile.H,
                    BufferedImage.SCALE_SMOOTH
            );

            BufferedImage tempPlatformImage = new BufferedImage(
                    Tile.W,
                    Tile.H,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = tempPlatformImage.createGraphics();

            bGr.drawImage(
                    tempBodyImage,
                    0, 0,
                    Tile.W, Tile.H,
                    null
            );

            bGr.dispose();

            image = tempPlatformImage;
        }
    }

}
