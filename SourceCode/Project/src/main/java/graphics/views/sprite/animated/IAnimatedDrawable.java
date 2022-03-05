package graphics.views.sprite.animated;

import utils.IDrawable;

import java.awt.*;

public interface IAnimatedDrawable extends IDrawable {

    @Override
    void draw(Graphics g);

    void setKeyFrames();

    void obtainNextFrame();

}
