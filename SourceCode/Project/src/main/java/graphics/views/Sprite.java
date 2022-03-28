package graphics.views;

import java.awt.image.BufferedImage;

/**
 * TODO: Add description
 */
public class Sprite {

    private String filename;
    private int x, y, w, h;
    private int duration = 100;

    /**
     * "filename": "Sprite-0004 0.",
     *     "frame": { "x": 5, "y": 5, "w": 1000, "h": 300 },
     *     "rotated": false,
     *     "trimmed": false,
     *     "spriteSourceSize": { "x": 0, "y": 0, "w": 1000, "h": 300 },
     *     "sourceSize": { "w": 1000, "h": 300 },
     *     "duration": 100
     */

    public Sprite(int x, int y, int w, int h, int duration) {
        this.x = x;
        this.y = y;

        this.w = w;
        this.h = h;

        this.duration = duration;
    }

    public BufferedImage getSubImage(BufferedImage spritesheet) {
        return spritesheet.getSubimage(x, y, w, h);
    }

}
