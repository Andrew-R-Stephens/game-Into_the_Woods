package models.prototypes.components.overlays;

import models.environments.game.GameEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

public abstract class AOverlayComponent implements IUpdatable, IDrawable {

    protected GameEnvironment gameEnvironment;

    protected int x, y, w, h;

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
