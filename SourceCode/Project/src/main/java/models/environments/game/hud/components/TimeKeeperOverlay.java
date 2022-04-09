package models.environments.game.hud.components;

import models.environments.game.GameEnvironment;
import models.prototypes.window.environments.overlays.AOverlayComponent;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class TimeKeeperOverlay extends AOverlayComponent implements IDrawable, IUpdatable {

    public void init(GameEnvironment gameEnvironment, int x, int y, int w, int h) {
        super.init(gameEnvironment, x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = ConfigData.scaledW, sH = ConfigData.scaledH;

        g.setColor(Color.white);
        g.drawString("Time Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
