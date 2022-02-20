package graphics.ui;

import files.Preferences;
import viewmodels.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    private GameModel gameModel;

    double tick = 0, MAX_TICK = Math.PI*2;
    double w = 20, h = 20;
    double x, y, ox = 0, oy = 0;

    int red = 100, green = 100, blue = 100;

    public GameCanvas(GameModel gameModel) {
        this.gameModel = gameModel;

        double sW = gameModel.getPreferences().getScaledW();
        double sH = gameModel.getPreferences().getScaledH();

        ox =  (gameModel.getPreferences().getWindowWidth()/2.0 - (sW * w));
        oy =  (gameModel.getPreferences().getWindowHeight()/2.0 - (sH *h));
    }

    public void update() {
        //System.out.println("Updating");

        tick += 1/(double)gameModel.getPreferences().getFrameRate();
        if(tick > MAX_TICK) {
            tick = 0;
        }
        //System.out.println(tick);

        double sW = gameModel.getPreferences().getScaledW();
        double sH = gameModel.getPreferences().getScaledH();

        x = (ox + (sW * (Math.cos(-tick)) * sW * 100));
        y = (oy - (sW * (Math.sin(-tick)) * sH * 100));

        //System.out.println(ox + " " + x + " / " + oy + " " + y);

        if(red < 255) {
            red++;
        } else {
            if (green < 255) {
                green++;
            } else {
                if (blue < 255) {
                    blue++;
                } else {
                    red = 0;
                    green = 0;
                    blue = 0;
                }
            }
        }
    }

    public void render() {
        //System.out.println("Rendering");
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(new Color(red, green, blue));
        g2d.fillOval((int)x, (int)y, (int)w, (int)h);
    }
}
