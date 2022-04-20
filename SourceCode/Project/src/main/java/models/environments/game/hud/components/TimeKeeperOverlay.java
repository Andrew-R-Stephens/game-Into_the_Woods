package models.environments.game.hud.components;

import models.prototypes.components.overlays.AOverlayComponent;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p></p>
 */
public class TimeKeeperOverlay extends AOverlayComponent implements IDrawable, IUpdatable {

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        g.setColor(Color.white);
        g.drawString("Time Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {

    }
}
