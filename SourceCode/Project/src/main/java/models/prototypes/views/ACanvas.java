package models.prototypes.views;

import javax.swing.*;

/**
 * <p>The abstract wrapper class for an environment's canvas.</p>
 */
public abstract class ACanvas extends JPanel {

    /**
     * <p>The render method that all ACanvases must define.</p>
     */
    public abstract void render();

}
