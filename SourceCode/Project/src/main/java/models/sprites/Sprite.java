package models.sprites;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * <p>The Sprite class is for data read from the SpriteSheetParser. It contains all data of an individual frame in
 * the spritesheet. The Sprite should be contained within a list in the SpriteSheet class.</p>
 * @author Andrew Stephens
 */
public class Sprite {

    /**<p>The Sprite frame name.</p>*/
    private final String filename;

    /**<p>The frame size.</p>*/
    private final int[] frame, spriteSourceSize, sourceSize;
    /**<p>A modification of the frame.</p>*/
    private final boolean rotated, trimmed;
    /**<p>The duration that the frame should last for in milliseconds.</p>*/
    private final int duration;

    /**<p>The scaled position relative to the max sprite size.</p>*/
    private float[] scaledPos = new float[2];
    /**<p>The scaled size relative to the max sprite size.</p>*/
    private float[] scaledSize = new float[2];

    /**
     * <p>Sets the data pulled from the SpriteSheet Json file by means of the SpriteSheetParser.</p>
     * @param filename The name of the frame
     * @param frame The position and actual size of the frame
     * @param rotated If the frame is rotated
     * @param trimmed If the frame is trimmed
     * @param spriteSourceSize The standard size of the frame
     * @param sourceSize The trimmed size of the frame
     * @param duration -
     */
    public Sprite(String filename, int[] frame, boolean rotated, boolean trimmed, int[] spriteSourceSize,
                  int[] sourceSize, int duration) {
        this.filename = filename;
        this.frame = frame;
        this.rotated = rotated;
        this.trimmed = trimmed;
        this.spriteSourceSize = spriteSourceSize;
        this.sourceSize = sourceSize;
        this.duration = duration;
    }

    /**
     * <p>Gets the name of the frame.</p>
     * @return The name of the frame.
     */
    public String getName() {
        return filename;
    }

    /**
     * <p>Gets the frame from the master sprite sheet image.</p>
     * @param spritesheet The master Sprite Sheet image.
     * @return The frame from the master sprite sheet.
     */
    public BufferedImage getSubImage(BufferedImage spritesheet) {
        return spritesheet.getSubimage(
                frame[0], frame[1], frame[2], frame[3]);
    }

    /**
     * <p>Gets the frame duration.</p>
     * @return The number of milliseconds that this frame should exist for.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * <p>Gets if the frame is trimmed.</p>
     * @return If the frame is trimmed.
     */
    public boolean isTrimmed() {
        return trimmed;
    }

    /**
     * <p>Gets the actual size of the frame.</p>
     * @return The size of the frame.
     */
    public int[] getSize() {
        if(!isTrimmed()) {
            return sourceSize;
        }
        return new int[] {spriteSourceSize[2], spriteSourceSize[3]};
    }

    /**
     * <p>Gets the scaled size against the largest width frame and the largest height frame.</p>
     * @param scale The scaled size of the largest width frame and the largest height frame
     */
    public void setScaledSize(float[] scale) {
        scaledSize[0] = scale[0] * getSize()[0];
        scaledSize[1] = scale[1] * getSize()[1];
    }

    /**
     * <p>Gets the scaled position vs the largest frame width and largest frame height.</p>
     * @param scale The scale of the largest width frame and the largest height frame
     */
    public void setScaledPos(float[] scale) {
        scaledPos[0] = scale[0] * getPosition()[0];
        scaledPos[1] = scale[1] * getPosition()[1];
    }

    /**
     * <p>Gets the scale of the frame in relation to the largest frame.</p>
     * @return The scaled size of the frame
     */
    public float[] getScaledSize() {
        return scaledSize;
    }

    /**
     * <p>Gets the scaled position of the frame in relation to the max frame size.</p>
     * @return The scaled position of the frame
     */
    public int[] getPosition() {
        return new int[] {(int)scaledPos[0], (int)scaledPos[1]};
    }

    @Override
    public String toString() {
        return "Sprite: \t" +
                "filename='" + filename + '\'' +
                ", frame=" + Arrays.toString(frame) +
                ", rotated=" + rotated +
                ", trimmed=" + trimmed +
                ", spriteSourceSize=" + Arrays.toString(spriteSourceSize) +
                ", sourceSize=" + Arrays.toString(sourceSize) +
                ", duration=" + duration;
    }

}
