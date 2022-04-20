package models.sprites;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * <p></p>
 */
public class Sprite {

    private final String filename;

    private final int[] frame, spriteSourceSize, sourceSize;
    private final boolean rotated, trimmed;
    private final int duration;

    private float[] scaledPos = new float[2];
    private float[] scaledSize = new float[2];

    /**
     * <p></p>
     * @param filename -
     * @param frame -
     * @param rotated -
     * @param trimmed -
     * @param spriteSourceSize -
     * @param sourceSize -
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
     * <p></p>
     * @return
     */
    public String getName() {
        return filename;
    }

    /**
     * <p></p>
     * @param spritesheet -
     * @return
     */
    public BufferedImage getSubImage(BufferedImage spritesheet) {
        return spritesheet.getSubimage(
                frame[0], frame[1], frame[2], frame[3]);
    }

    /**
     * <p></p>
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * <p></p>
     * @return
     */
    public boolean isTrimmed() {
        return trimmed;
    }

    /**
     * <p></p>
     * @return
     */
    public int[] getSize() {
        if(!isTrimmed()) {
            return sourceSize;
        }
        return new int[] {spriteSourceSize[2], spriteSourceSize[3]};
    }

    /**
     * <p></p>
     * @param scale -
     */
    public void setScaledSize(float[] scale) {
        scaledSize[0] = scale[0] * getSize()[0];
        scaledSize[1] = scale[1] * getSize()[1];
    }

    /**
     * <p></p>
     * @param scale -
     */
    public void setScaledPos(float[] scale) {
        scaledPos[0] = scale[0] * getPosition()[0];
        scaledPos[1] = scale[1] * getPosition()[1];
    }

    /**
     * <p></p>
     * @return
     */
    public float[] getScaledSize() {
        return scaledSize;
    }

    /**
     * <p></p>
     * @return
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
