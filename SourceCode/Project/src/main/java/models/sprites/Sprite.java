package models.sprites;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Sprite {

    private final String filename;

    private final int[] frame, spriteSourceSize, sourceSize;
    private final boolean rotated, trimmed;
    private final int duration;

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

    public BufferedImage getSubImage(BufferedImage spritesheet) {
        return spritesheet.getSubimage(
                frame[0], frame[1], frame[2], frame[3]);
    }

    public int getDuration() {
        return duration;
    }

    public boolean isTrimmed() {
        return trimmed;
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
