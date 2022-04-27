package models.sprites;

import models.utils.config.Config;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>SpriteSheet allows for the compilation of individual Sprite data into a list. Gives access to animations.</p>
 * <p>Allows for animation control of the sprites.</p>
 * @author Andrew Stephens
 */
public class SpriteSheet implements IUpdatable {

    private BufferedImage referenceImage;

    private ArrayList<Sprite> frames = new ArrayList<>();

    private final int[] largestSize = new int[2];
    private float[] frameScale = new float[2];

    private int currentFrame = 0, ticks = 0;
    private boolean loopOnLastFrame = true;

    /**
     * <p>Creates a spritesheet based on the sprite arraylist passed.</p>
     * @param spritelist The sprite arraylist created by the SpriteSheetParser.
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
     * <p>Sets the Reference image of the spritesheet.</p>
     * @param referenceImage The spritesheet image.
     */
    public void setReferenceImage(BufferedImage referenceImage) {
        this.referenceImage = referenceImage;
    }

    /**
     * <p>Increments the current frame by the increment amount.</p>
     * @param i The frame increment amount
     */
    public void incrementFrame(int i) {
        i++;
        setFrame(i);
    }

    /**
     * <p>Sets the current frame to the index provided. Cycles the frame to 0 only if the animation is set to loop.</p>
     * @param i The frame requested.
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
     * <p>Checks if the current frame is the last frame.</p>
     * @return if the current frame is the last frame.
     */
    public boolean isLastFrame() {
        return currentFrame >= frames.size() - 1;
    }

    /**
     * <p>Sets if the animation should loop after the last frame.</p>
     * @param b If the animation should loop
     * @return this SpriteSheet
     */
    public SpriteSheet setLoopOnLast(boolean b) {
        loopOnLastFrame = b;

        return this;
    }

    /**
     * <p>Gets the animation cycle percent completed.</p>
     * @return The percent of completion of the animation.
     */
    public float getPercentCompleted() {
        return (currentFrame / (float) (frames.size() - 1));
    }

    /**
     * <p>Records the framescale and sets the framescale into each frame.</p>
     * @param containerW The max width
     * @param containerH The max height
     * @return this Spritesheet
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
     * <p>Gets the scale of the container vs the largest frame size.</p>
     * @return the scale of the container vs the largest frame size
     */
    public float[] getFrameScale() {
        return frameScale;
    }

    /**
     * <p>The current frame's scaled size.</p>
     * @return the current frame's size
     */
    public float[] getCurrentFrameSize() {
        return frames.get(currentFrame).getScaledSize();
    }

    /**
     * <p>Gets the current frame's position.</p>
     * @return the current frame's position
     */
    public int[] getCurrentFramePos() {
        return frames.get(currentFrame).getPosition();
    }

    /**
     * <p>Gets the largest dimensions of the largest dimensions across all frames.</p>
     * @return the largest width and height of the largest frame height and largest frame width
     */
    public int[] getLargestSize() {
        return largestSize;
    }

    /**
     * <p>Checks if the current frame is trimmed.</p>
     * @return if the current frame is trimmed
     */
    public boolean isTrimmed() {
        return frames.get(currentFrame).isTrimmed();
    }

    /**
     * <p>Draws the Sprite image, pulled as a subimage from the Spritesheet.</p>
     * @param g The Graphics object
     * @param x the horizontal position of the subimage
     * @param y the vertical position of the subimage
     * @param w the width of the subimage
     * @param h the height of the subimage
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
     * <p>Resets the spritesheet data.</p>
     */
    public void reset() {
        currentFrame = 0;
        ticks = 0;
    }

    /**
     * @return a brief description of the spritesheet data
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < frames.size(); i++) {
            s += frames + "\n";
        }
        return s + "";
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

}