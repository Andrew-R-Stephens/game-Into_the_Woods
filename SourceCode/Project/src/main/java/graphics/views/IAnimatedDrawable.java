package graphics.views;

import utils.drawables.IDrawable;

import java.awt.*;

/**
 * TODO: Add description
 */
public interface IAnimatedDrawable extends IDrawable {

    @Override
    void draw(Graphics g);

    void setKeyFrames();

    void obtainNextFrame();

}
