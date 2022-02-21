package graphics.ui;

import viewmodels.game.GameViewModel;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    private GameViewModel gameModel;

    double tick = 0, MAX_TICK = Math.PI*2;
    double w = 20, h = 20;
    double vx, vy, MAX_VEL = 1;
    double x, y, ox = 0, oy = 0;

    int red = 100, green = 100, blue = 100;

    double sW;
    double sH;

    boolean[] isPressed = new boolean[4];

    public GameCanvas() {
        // TODO: Nothing yet
    }

    public void init(GameViewModel gameModel) {
        this.gameModel = gameModel;

        sW = gameModel.getPreferences().getScaledW();
        sH = gameModel.getPreferences().getScaledH();

        ox =  (gameModel.getPreferences().getWindowWidth()/2.0 - (sW * w));
        oy =  (gameModel.getPreferences().getWindowHeight()/2.0 - (sH *h));
    }

    public void update() {

        if(isPressed[0]) {
            vx -= .1;
        }
        if(isPressed[1]) {
            vx += .1;
        }
        if(isPressed[2]) {
            vy -= .1;
        }
        if(isPressed[3]) {
            vy += .1;
        }

        ox += vx;
        oy += vy;

        if(Math.abs(vx) < 0) {
            vx = 0;
        }
        if(Math.abs(vy) < 0) {
            vy = 0;
        }

        if(Math.abs(vx) > MAX_VEL) {
            if(vx < 0) {
                vx = -MAX_VEL;
            } else {
                if(vx > 0) {
                    vx = MAX_VEL;
                }
            }
        }
        if(Math.abs(vy) > MAX_VEL) {
            if(vy < 0) {
                vy = -MAX_VEL;
            } else {
                if(vy > 0) {
                    vy = MAX_VEL;
                }
            }
        }

        tick += 1/(double)gameModel.getPreferences().getFrameRate();
        if(tick > MAX_TICK) {
            tick = 0;
        }

        x = (ox + (sW * (Math.cos(-tick)) * sW * 50));
        y = (oy - (sW * (Math.sin(-tick)) * sH * 50));


        // CHANGE COLORS
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

        if(vx < 0) {
            vx += .001;
        } else {
            if(vx > 0) {
                vx -= .001;
            }
        }
        if(vy < 0) {
            vy += .001;
        } else {
            if(vy > 0) {
                vy -= .001;
            }
        }

    }

    public void render() {
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        // Fill background
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw object
        g2d.setColor(new Color(red, green, blue));
        g2d.fillOval((int)x, (int)y, (int)w, (int)h);

        g.setColor(Color.BLACK);
        g2d.fillOval((int)ox, (int)oy, 5, 5);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Consolas", Font.PLAIN, (int)(sH * 12)));
        g.drawString("Origin", (int)(ox) + 10, (int)(oy) + 6);

        g.setFont(new Font("Consolas", Font.BOLD, (int)(sH * 30)));
        g.drawString("ESCAPE: ESC", 0, (int)(sH * 30));
        g.drawString("UP, DOWN, LEFT, RIGHT = Arrow keys", 0, (int)(sH * 60));
    }
}
