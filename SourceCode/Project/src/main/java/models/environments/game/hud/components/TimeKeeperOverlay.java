package models.environments.game.hud.components;

import prototypes.window.environments.game.AOverlayComponent;
import utils.config.ConfigData;

import java.awt.*;

/**
 * The type Time keeper overlay.
 */
public class TimeKeeperOverlay extends AOverlayComponent {

    /**
     * Instantiates a new Time keeper overlay.
     *
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    public TimeKeeperOverlay(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = (float) ConfigData.scaledW, sH = (float) ConfigData.scaledH;

        g.setColor(Color.PINK);
        g.fillRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));
        g.setColor(Color.white);
        g.drawString("Time Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
