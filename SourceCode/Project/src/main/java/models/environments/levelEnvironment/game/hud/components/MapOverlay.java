package models.environments.levelEnvironment.game.hud.components;

import models.environments.levelEnvironment.game.GameEnvironment;
import models.prototypes.components.overlays.AOverlayComponent;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>The MapOverlay is drawn to the GameEnvironment's Canvas, which allows for the broader representation of the current
 * level. It shows a depiction of the player position and shows obstacles.</p>
 * @author Andrew Stephens
 */
public class MapOverlay extends AOverlayComponent implements IDrawable, IUpdatable {

    /**<p>The overlay image.</p>*/
    private BufferedImage overlay;

    /**
     * <p>Destroys and reconstructs the map image as a blank image.</p>
     */
    public void reset() {
        overlay = new BufferedImage(
                Config.window_width_actual, Config.window_height_actual, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void init(GameEnvironment gameEnvironment, int x, int y, int w, int h) {
        super.init(gameEnvironment, x, y, w, h);

        overlay = new BufferedImage(
                Config.window_width_actual, Config.window_height_actual, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        gameEnvironment.drawAsHUD((Graphics2D) overlay.getGraphics());
        g.drawImage(overlay, (int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH), null);

        g.setColor(new Color(75, 25, 75));
        int stroke = (int)(5 * sW);
        g.setStroke(new BasicStroke(stroke * sW, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g.drawRect(
                (int)((x * sW) - (stroke * .5)), (int)((y * sH) + (stroke * .5)),
                (int)((w * sW)), (int)((h * sH) ));

        /*
        int squareDim = (int)(5 * sW);
        g.setColor(Color.RED);
        g.fillRect(
                (int)((x * sW) + (w * sW * .5) - (squareDim)),
                (int)((y * sH) + (h * sH * .5) - (squareDim)),
                squareDim,
                squareDim
                );
        */

    }

}
