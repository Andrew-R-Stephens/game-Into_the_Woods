package graphics.ui.game;

import models.environments.game.GameModel;
import props.prototypes.window.ACanvas;

import java.awt.*;

/**
 * Game Canvas extends JPanel. The canvas renders game objects using the scale of chosen window dimensions against the standard dimensions.
 */
public class GameCanvas extends ACanvas {

    private GameModel gameModel;

    public GameCanvas() {
        // TODO: Nothing yet
    }

    public void init(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw test objects
        gameModel.draw(g);
    }

}
