package graphics.ui.game;

import props.prototypes.window.ACanvas;
import models.environments.game.GameModel;
import props.threads.gameloop.GameRenderRunnable;
import props.threads.gameloop.GameUpdateRunnable;

import java.awt.*;

/**
 * Game Canvas extends JPanel. The canvas renders game objects using the scale of chosen window dimensions against the standard dimensions.
 */
public class GameCanvas extends ACanvas {

    private GameModel gameModel;

    private boolean isUpdated = true;

    public GameCanvas() {
        // TODO: Nothing yet
    }

    public void init(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void update(float updateRate) {
        gameModel.update(updateRate);
        isUpdated = true;
    }

    public void render() {
        repaint();
        isUpdated = false;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw test objects
        gameModel.draw(g);

        g.setColor(Color.RED);
        g.drawString("FPS: " + GameRenderRunnable.lastFrames, 10, 10);
        g.drawString("Ticks: " + GameUpdateRunnable.lastUpdates, 10, 30);

    }

}
