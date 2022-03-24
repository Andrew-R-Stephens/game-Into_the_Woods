package models.environments.game.hud.components;

import utils.config.PreferenceData;
import prototypes.window.environments.game.AOverlayComponent;

import java.awt.*;

/**
 * The type Player stats overlay.
 */
public class PlayerStatsOverlay extends AOverlayComponent {

    /**
     * Instantiates a new Player stats overlay.
     *
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    public PlayerStatsOverlay(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = (float) PreferenceData.scaledW, sH = (float)PreferenceData.scaledH;

        g.setColor(Color.PINK);
        g.fillRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));
        g.setColor(Color.white);
        g.drawString("Stats Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
