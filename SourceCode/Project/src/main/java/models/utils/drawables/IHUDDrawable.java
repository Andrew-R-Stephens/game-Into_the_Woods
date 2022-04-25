package models.utils.drawables;

import java.awt.*;

/**
 * <p>IHUDDrawable is used by HUD Overlay classes which require draw calls for a render thread.</p>
 */
public interface IHUDDrawable {

    /**
     * <p>Allows for the user to specify a draw call for an Overlay component.</p>
     * @param g - Draw method accepts the Graphics object obtained by the paintComponent method in Canvas.
     */
    void drawAsHUD(Graphics2D g);

}
