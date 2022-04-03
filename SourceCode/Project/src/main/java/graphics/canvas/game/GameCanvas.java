package graphics.canvas.game;

import models.environments.game.GameEnvironment;
import prototypes.window.ACanvas;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Game Canvas is what is drawn on to represent the Game Model.
 * The canvas renders game objects using the scale of chosen window dimensions against the standard dimensions.
 */
public class GameCanvas extends ACanvas {

    private GameEnvironment gameModel;

    /**
     * Init.
     *
     * @param gameModel the game model
     */
    public void init(GameEnvironment gameModel) {
        this.gameModel = gameModel;
    }

    /**
     * Render.
     */
    public void render() {
        repaint();
    }

    /**
     *
     * @param g The parent Graphics that come from the JPanel's Canvas
     */
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

        g.setColor(Color.RED);
        float sW = ConfigData.scaledW, sH = ConfigData.scaledH;

    }

}
