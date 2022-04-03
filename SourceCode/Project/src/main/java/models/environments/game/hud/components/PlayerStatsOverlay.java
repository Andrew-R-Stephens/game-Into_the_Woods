package models.environments.game.hud.components;

import models.environments.game.GameEnvironment;
import prototypes.window.environments.game.AOverlayComponent;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * The type Player stats overlay.
 */
public class PlayerStatsOverlay extends AOverlayComponent {

    /**
     * Instantiates a new Player stats overlay.
     *
     * @param gameEnvironment
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    public PlayerStatsOverlay(GameEnvironment gameEnvironment, int x, int y, int w, int h) {
        super(gameEnvironment, x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = ConfigData.scaledW, sH = ConfigData.scaledH;

        g.setColor(Color.white);
        g.drawString("Stats Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));

        g.drawString("Keys: " + gameEnvironment.getPlayerInventory().getKeysCount(), (int)((x * sW) + (20 * sW)),
                (int)((y * sH) + (50 * sH)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
