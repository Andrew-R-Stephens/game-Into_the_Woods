package graphics.sprite;

import game.objects.types.Drawable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite implements Drawable {

    private BufferedImage img;

    public Sprite(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

}
