package views.canvas.menu;

import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.views.ACanvas;

import java.awt.*;

/**
 * <p></p>
 */
public class MenuCanvas extends ACanvas {

    private MainMenuEnvironment mainMenuEnvironment;

    /**
     * <p></p>
     * @param mainMenuEnvironment -
     */
    public void init(MainMenuEnvironment mainMenuEnvironment) {
        this.mainMenuEnvironment = mainMenuEnvironment;
    }

    /**
     * <p></p>
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

        mainMenuEnvironment.draw(g2d);

        g.setColor(Color.RED);

    }

}
