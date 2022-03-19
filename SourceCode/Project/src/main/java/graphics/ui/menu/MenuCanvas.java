package graphics.ui.menu;

import models.environments.menus.mainmenu.MainMenuModel;
import props.prototypes.window.ACanvas;

import java.awt.*;

/**
 * Game Canvas extends JPanel. The canvas renders game objects using the scale of chosen window dimensions against the standard dimensions.
 */
public class MenuCanvas extends ACanvas {

    private MainMenuModel menuModel;

    public void init(MainMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void render() {
        //System.out.println("Rendering");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        //super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        menuModel.draw(g);

    }

}
