package models.sprites;

import models.utils.config.Config;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p></p>
 */
public class SpriteSheet implements IUpdatable {

    private BufferedImage referenceImage;

    private ArrayList<Sprite> frames = new ArrayList<>();

    private final int[] largestSize = new int[2];
    private float[] frameScale = new float[2];

    private int currentFrame = 0, ticks = 0;
    private boolean loopOnLastFrame = true;

    /**
     * <p></p>
     * @param spritelist -
     */
    public SpriteSheet(ArrayList<Sprite> spritelist) {
        frames.addAll(spritelist);

        int newFrame = currentFrame;
        if (ticks > frames.get(currentFrame).getDuration()) {
            incrementFrame(newFrame);

            ticks = 0;
            for (Sprite s : frames) {
                for (int i = 0; i < largestSize.length; i++) {
                    if (s.getSize()[i] > largestSize[i]) {
                        largestSize[i] = s.getSize()[i];
                    }
                }
            }
        }
    }

    /**
     * <p></p>
     * @param referenceImage -
     */
    public void setReferenceImage(BufferedImage referenceImage) {
        this.referenceImage = referenceImage;
    }

    /**
     * <p></p>
     * @param i -
     */
    public void incrementFrame(int i) {
        i++;
        setFrame(i);
    }

    /**
     * <p></p>
     * @param i -
     */
    public void setFrame(int i) {
        if (!loopOnLastFrame) {
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

    /**
     * <p></p>
     * @return
     */
    public boolean isLastFrame() {
        return currentFrame >= frames.size() - 1;
    }

    /**
     * <p></p>
     * @param b -
     * @return
     */
    public SpriteSheet setLoopOnLast(boolean b) {
        loopOnLastFrame = b;

        return this;
    }

    /**
     * <p></p>
     * @return
     */
    public float getPercentCompleted() {
        return (currentFrame / (float) (frames.size() - 1));
    }

    /**
     * <p></p>
     * @param containerW -
     * @param containerH -
     * @return
     */
    public SpriteSheet setFrameScale(float containerW, float containerH) {
        frameScale = new float[]{
                containerW / largestSize[0],
                containerH / largestSize[1]
        };

        System.out.println(frameScale);

        for (Sprite s : frames) {
            s.setScaledSize(frameScale);
            s.setScaledPos(frameScale);
        }

        return this;
    }

    /**
     * <p></p>
     * @return
     */
    public float[] getFrameScale() {
        return frameScale;
    }

    /**
     * <p></p>
     * @return
     */
    public float[] getCurrentFrameSize() {
        return frames.get(currentFrame).getScaledSize();
    }

    /**
     * <p></p>
     * @return
     */
    public int[] getCurrentFramePos() {
        return frames.get(currentFrame).getPosition();
    }

    /**
     * <p></p>
     * @return
     */
    public int[] getLargestSize() {
        return largestSize;
    }

    /**
     * <p></p>
     * @return
     */
    public boolean isTrimmed() {
        return frames.get(currentFrame).isTrimmed();
    }

    /**
     * <p></p>
     * @param g
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void draw(Graphics g, int x, int y, int w, int h) {
        BufferedImage croppedImage = frames.get(currentFrame).getSubImage(referenceImage);
        g.drawImage(
                croppedImage,
                x,
                y,
                w,
                h,
                null);
        croppedImage.flush();
    }

    /**
     * <p></p>
     */
    public void reset() {
        currentFrame = 0;
        ticks = 0;
    }

    @Override
    public void update(float delta) {
        ticks += 1000 / (Config.GAME_UPDATE_RATE * delta);

        int newFrame = currentFrame;
        if (ticks > frames.get(currentFrame).getDuration()) {
            incrementFrame(newFrame);

            ticks = 0;
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < frames.size(); i++) {
            s += frames + "\n";
        }
        return s + "";
    }

}