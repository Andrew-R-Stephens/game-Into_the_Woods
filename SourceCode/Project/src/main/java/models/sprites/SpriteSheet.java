package models.sprites;

import models.utils.config.ConfigData;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet implements IUpdatable {

    private BufferedImage referenceImage;

    private ArrayList<Sprite> frames = new ArrayList<>();
    private int currentFrame = 0;

    private int ticks = 0;

    private boolean loopOnLastFrame = true;

    public SpriteSheet(ArrayList<Sprite> spritelist) {
        frames.addAll(spritelist);
    }

    public SpriteSheet addReferenceImage(BufferedImage referenceImage) {
        this.referenceImage = referenceImage;

        return this;
    }

    @Override
    public void update(float delta) {
        ticks += 1000/ (ConfigData.GAME_UPDATE_RATE*delta);

        int newFrame = currentFrame;
        if(ticks > frames.get(currentFrame).getDuration()) {
            incrementCurrentFrame(newFrame);

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

    public void incrementCurrentFrame(int i) {
        i ++;
        setCurrentFrame(i);
    }

    public void setCurrentFrame(int i) {
        if(!loopOnLastFrame){
            if (isLastFrame()) {
                i = frames.size() - 1;
            } else if (i < 0) {
                i = 0;
            }
        } else {
            if (isLastFrame()) {
                i = 0;
            } else if (i < 0) {
                i = frames.size() - 1;
            }
        }

        currentFrame = i;
    }

    public boolean isLastFrame() {
        return currentFrame >= frames.size() -1;
    }

    public void reset() {
        currentFrame = 0;
        ticks = 0;
    }

    public SpriteSheet setLoopOnLast(boolean b) {
        loopOnLastFrame = b;

        return this;
    }

    public float getPercentCompleted() {
        return (currentFrame / (float)(frames.size()-1));
    }
}
