package graphics.views;

import utils.drawables.IDrawable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TODO: Add description
 */
public class Sprite implements IDrawable {

    private BufferedImage img;

    /**
     * Instantiates a new Sprite.
     *
     * @param img the img
     */
    public Sprite(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
