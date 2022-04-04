package graphics.views;

import utils.config.ConfigData;
import utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet implements IUpdatable {

    private BufferedImage referenceImage;

    private ArrayList<Sprite> frames = new ArrayList<>();
    private int currentFrame = 0;

    private int ticks = 0;

    public SpriteSheet(ArrayList<Sprite> spritelist) {
        frames.addAll(spritelist);
    }

    public void addReferenceImage(BufferedImage referenceImage) {
        this.referenceImage = referenceImage;
    }

    @Override
    public void update(float delta) {
        ticks += 1000/ (ConfigData.GAME_UPDATE_RATE*delta);

        int newFrame = currentFrame;
        if(ticks > frames.get(currentFrame).getDuration()) {
            newFrame += 1;
            if(newFrame >= frames.size()) {
                newFrame = 0;
            }
            currentFrame = newFrame;
            ticks = 0;
        }
    }

    public void draw(Graphics g, int x, int y, int w, int h) {
        g.drawImage(
                frames.get(currentFrame).getSubImage(referenceImage),
                x,
                y,
                w,
                h,
               null);
    }

    public String toString() {
        String s = "";
        for(int i = 0; i < frames.size(); i++) {
            s += frames + "\n";
        }
        return s + "";
    }

}
