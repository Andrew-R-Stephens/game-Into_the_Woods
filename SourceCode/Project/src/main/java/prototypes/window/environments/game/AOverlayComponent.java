package prototypes.window.environments.game;

import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;

/**
 * The type A overlay component.
 */
public abstract class AOverlayComponent implements IUpdatable, IDrawable {

    protected int x, y, w, h;

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

    }

    @Override
    public void update(float delta) {

    }
}
