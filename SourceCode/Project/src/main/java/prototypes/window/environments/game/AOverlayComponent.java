package prototypes.window.environments.game;

import utils.config.ConfigData;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;

/**
 * The type A overlay component.
 */
public abstract class AOverlayComponent implements IUpdatable, IDrawable {

    /**
     * The X.
     */
    protected int x, /**
     * The Y.
     */
    y, /**
     * The W.
     */
    w, /**
     * The H.
     */
    h;

    /**
     * Instantiates a new A overlay component.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param w the width
     * @param h the height
     */
    protected AOverlayComponent(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(Graphics g) {
        float sW = ConfigData.scaledW, sH = ConfigData.scaledH;
        g.setColor(new Color(255, 150, 150, 100));
        g.fillRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));
    }

    @Override
    public void update(float delta) {

    }
}
