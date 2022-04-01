package models.environments.game.hud.components;

import prototypes.window.environments.game.AOverlayComponent;
import utils.config.ConfigData;

import java.awt.*;

/**
 * The type Map overlay.
 */
public class MapOverlay extends AOverlayComponent {

    /**
     * Instantiates a new Map overlay.
     *
     * @param x the x
     * @param y the y
     * @param w the w
     * @param h the h
     */
    public MapOverlay(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = ConfigData.scaledW, sH = ConfigData.scaledH;

        g.setColor(Color.white);
        g.drawString("Map Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
