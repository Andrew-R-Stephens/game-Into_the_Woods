package graphics.views;

import java.awt.*;
import java.util.ArrayList;

public class SpriteSheet {

    private ArrayList<Sprite> sprites = new ArrayList<>();
    private int currentSprite = 0;

    public SpriteSheet(ArrayList<Sprite> spritelist) {
        sprites.addAll(spritelist);
    }

    public void draw(Graphics g, float scaleW, float scaleH, int x, int y, int w, int h) {
        /*
        g.drawImage(
                frames.get(currentSprite).getSubImage(spritesheet),
                (int)(x * scaleW),
                (int)(y * scaleH),
                w,
                h,
               null);
        */
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < sprites.size(); i++) {
            s += sprites + "\n";
        }
        return s + "";
    }
}
