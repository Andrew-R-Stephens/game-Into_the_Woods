package models.environments.game.hud.components;

import models.environments.game.GameEnvironment;
import models.prototypes.window.environments.overlays.AOverlayComponent;
import models.utils.config.ConfigData;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Map overlay.
 */
public class MapOverlay extends AOverlayComponent {

    private BufferedImage overlay;

    public void init(GameEnvironment gameEnvironment, int x, int y, int w, int h) {
        super.init(gameEnvironment, x, y, w, h);

        overlay = new BufferedImage(
                ConfigData.window_width_actual, ConfigData.window_height_actual, BufferedImage.TYPE_INT_ARGB);
    }

    public void reset() {
        overlay = new BufferedImage(
                ConfigData.window_width_actual, ConfigData.window_height_actual, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        float sW = ConfigData.scaledW, sH = ConfigData.scaledH;

        gameEnvironment.getCurrentLevel().drawAsHUD(overlay.getGraphics());
        g.drawImage(overlay, (int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH), null);

        g.setColor(Color.white);
        g.drawString("Map Overlay", (int)((x * sW) + (20 * sW)), (int)((y * sH) + (20 * sH)));

        g.setColor(new Color(75, 25, 75));
        Graphics2D g2d = (Graphics2D)g;
        int stroke = (int)(5 * sW);
        g2d.setStroke(new BasicStroke(stroke * sW, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2d.drawRect(
                (int)((x * sW) - (stroke * .5)), (int)((y * sH) + (stroke * .5)),
                (int)((w * sW)), (int)((h * sH) ));

        int squareDim = (int)(10 * sW);
        g.setColor(Color.RED);
        g.fillRect(
                (int)((x * sW) + (w * sW * .5) - (squareDim * .5)),
                (int)((y * sH) + (h * sH * .5) - (squareDim * .5)),
                squareDim,
                squareDim
                );

    }

}
