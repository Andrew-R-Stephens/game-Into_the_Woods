package graphics.ui.menu;

import viewmodels.states.game.GameModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Game Canvas extends JPanel. The canvas renders game objects using the scale of chosen window dimensions against the standard dimensions.
 */
public class MainMenuCanvas extends JPanel {

    ArrayList<MenuButton> buttons = new ArrayList<>();

    public MainMenuCanvas() {
        // TODO: Nothing yet
        buttons.add(new MenuButton());
    }

    public void init(GameModel gameModel) {

    }

    public void update(float updateRate) {
        //loop through all buttons in menu
            //if a button is within bounds on mouse click
            // execute action
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
