package prototypes.window.environments.game;

import models.environments.game.GameEnvironment;
import utils.config.ConfigData;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;

/**
 * The type A overlay component.
 */
public abstract class AOverlayComponent implements IUpdatable, IDrawable {

    protected GameEnvironment gameEnvironment;

    /**
     * The X.
     */
    protected int x, y, w, h;

    /**
     * Instantiates a new A overlay component.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param w the width
     * @param h the height
     */
    protected AOverlayComponent(GameEnvironment gameEnvironment, int x, int y, int w, int h) {
        this.gameEnvironment = gameEnvironment;
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
