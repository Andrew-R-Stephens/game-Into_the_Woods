package models.environments.levelEnvironment.game.hud.components;

import models.prototypes.components.overlays.AOverlayComponent;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>The PlayerStatsOverlay is drawn to the GameEnvironment's Canvas, which allows for the representation of the player's
 * inventory.</p>
 * @author Andrew Stephens
 */
public class PlayerStatsOverlay extends AOverlayComponent implements IDrawable, IUpdatable {

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        g.setColor(Color.white);
        g.drawString("Stats Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));

        g.drawString("Keys: " + gameEnvironment.getPlayerInventory().getKeyCount(), (int)((x * sW) + (20 * sW)),
                (int)((y * sH) + (50 * sH)));
    }

    @Override
    public void update(float delta) {
        // DO NOTHING YET
    }
}
