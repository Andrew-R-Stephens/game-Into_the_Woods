package graphics.ui.menu;

import props.prototypes.window.ACanvas;
import models.environments.menus.MenuModel;

import java.awt.*;

/**
 * Game Canvas extends JPanel. The canvas renders game objects using the scale of chosen window dimensions against the standard dimensions.
 */
public class MenuCanvas extends ACanvas {

    MenuModel menuModel;

    public void init(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void render() {
        //System.out.println("Rendering");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, getWidth(), getHeight());

    }

}
