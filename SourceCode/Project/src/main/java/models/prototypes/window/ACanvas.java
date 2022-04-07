package models.prototypes.window;

import javax.swing.*;

/**
 * The wrapper type for both GameCanvas and MenuCanvas objects.
 */
public abstract class ACanvas extends JPanel {

    /**
     * Render.
     */
    public abstract void render();

}
