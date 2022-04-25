package models.utils.drawables;

import java.awt.*;

/**
 * <p>IDrawable is used by classes which require draw calls for a render thread.</p>
 */
public interface IDrawable {

    /**
     * <p>Draw method accepts the Graphics object obtained by the paintComponent method in Canvas.</p>
     * @param g
     */
    void draw(Graphics2D g);

}
