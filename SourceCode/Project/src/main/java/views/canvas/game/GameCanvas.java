package views.canvas.game;

import models.environments.game.GameEnvironment;
import models.prototypes.views.ACanvas;

import java.awt.*;

public class GameCanvas extends ACanvas {

    private GameEnvironment gameModel;

    public void init(GameEnvironment gameModel) {
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

        gameModel.draw(g2d);

        g.setColor(Color.RED);

    }

}
