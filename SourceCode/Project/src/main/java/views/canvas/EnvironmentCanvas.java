package views.canvas;

import models.prototypes.environments.AEnvironment;
import models.prototypes.views.ACanvas;

import java.awt.*;

/**
 * <p></p>
 */
public class EnvironmentCanvas<T extends AEnvironment> extends ACanvas {

    private T environment;

    /**
     * <p></p>
     * @param environment -
     */
    public void init(T environment) {
        this.environment = environment;
    }

    /**
     * <p></p>
     */
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

        environment.draw(g2d);

        g.setColor(Color.RED);

    }

}