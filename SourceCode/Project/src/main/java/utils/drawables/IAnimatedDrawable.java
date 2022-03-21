package utils.drawables;

import java.awt.*;

/**
 * TODO: Add description
 */
public interface IAnimatedDrawable extends IDrawable {

    @Override
    void draw(Graphics g);

    /**
     * Sets key frames.
     */
    void setKeyFrames();

    /**
     * Obtain next frame.
     */
    void obtainNextFrame();

}
