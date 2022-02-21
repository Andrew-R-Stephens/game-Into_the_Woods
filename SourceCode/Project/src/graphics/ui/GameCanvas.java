package graphics.ui;

import game.objects.GameObject;
import game.objects.types.Entity;
import viewmodels.game.GameViewModel;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    private GameViewModel gameModel;

    private double sW, sH;

    public GameCanvas() {
        // TODO: Nothing yet
    }

    public void init(GameViewModel gameModel) {
        this.gameModel = gameModel;

        sW = gameModel.getPreferences().getScaledW();
        sH = gameModel.getPreferences().getScaledH();
    }

    public void update() {

        for(GameObject object: gameModel.getGameObjects()) {
            if (object instanceof Entity) {
                Entity e = (Entity) object;
                e.update();
            }
        }

    }

    public void render() {
        //revalidate();
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

        // Draw test objects
        for(GameObject object: gameModel.getGameObjects()) {
            if (object instanceof Entity) {
                Entity e = ((Entity) object);
                e.draw(g2d);
            }
        }

    }
}
