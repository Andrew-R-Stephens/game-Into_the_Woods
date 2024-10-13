package models.prototypes.components.overlays;

import models.environments.levelEnvironment.game.GameEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>The AOverlayComponent is the superclass for all HUD overlay components. Contains positional
 * and size data for all components. Also contains a reference to the parent Game Environment.</p>
 * @author Andrew Stephens
 */
public abstract class AOverlayComponent implements IUpdatable, IDrawable {

    /**<p>The parent GameEnvironment.</p>*/
    protected GameEnvironment gameEnvironment;

    /**<p>The positional coordinate of the component.</p>*/
    protected int x, y;
    /**<p>The dimensions of the component.</p>*/
    protected int w, h;

    /**
     * <p>Initializes the components with positional and size data.</p>
     * @param gameEnvironment The Game Environment that contains the parent HUD Model
     * @param x horizontal position, relative to default dimensions.
     * @param y vertical position, relative to default dimensions.
     * @param w width, relative to default dimensions.
     * @param h height, relative to default dimensions.
     */
    public void init(GameEnvironment gameEnvironment, int x, int y, int w, int h) {
        this.gameEnvironment = gameEnvironment;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(Graphics2D g) {
        float sW = Config.scaledW, sH = Config.scaledH;
        g.setColor(new Color(255, 150, 150, 100));
        g.fillRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));
    }

}
