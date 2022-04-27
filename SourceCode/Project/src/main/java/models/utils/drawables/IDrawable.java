package models.utils.drawables;

import java.awt.*;

/**
 * <p>IDrawable is used by classes which require draw calls for a render thread.</p>
 * @author Andrew Stephens
 */
public interface IDrawable {

    /**
     * <p>Allows for standard draw calls from a Canvas object.</p>
     * @param g Draw method accepts the Graphics object obtained by the paintComponent method in Canvas.
     */
    void draw(Graphics2D g);

}
