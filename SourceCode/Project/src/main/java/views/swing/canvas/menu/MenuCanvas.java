package views.swing.canvas.menu;

import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.window.ACanvas;
import models.utils.config.ConfigData;

import java.awt.*;

/**
 * Game Canvas extends ACanvas.
 * The canvas renders game objects using the scale of chosen window dimensions against the standard dimensions.
 */
public class MenuCanvas extends ACanvas {

    private MainMenuEnvironment mainMenuEnvironment;

    /**
     * Init.
     *
     * @param mainMenuEnvironment the menu model
     */
    public void init(MainMenuEnvironment mainMenuEnvironment) {
        this.mainMenuEnvironment = mainMenuEnvironment;
    }

    /**
     * Render.
     */
    public void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        mainMenuEnvironment.draw(g);

        g.setColor(Color.RED);
        float sW = ConfigData.scaledW;

    }

}
