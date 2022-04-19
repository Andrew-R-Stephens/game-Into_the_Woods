package models.environments.game.background;

import models.camera.Camera;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ParallaxBackground implements IDrawable {

    private final ArrayList<Layer> layers = new ArrayList<>();

    private float moveScale = .1f;

    public void addLayer(BufferedImage bufferedImage) {
        layers.add(new Layer(bufferedImage));
    }

    public void draw(Graphics2D g) {
        for(int i = 0; i < layers.size(); i++) {
            layers.get(i).draw(
                    g,
                    Camera.mapX * (moveScale * (layers.size() - i)),
                    Camera.mapY * (moveScale * (layers.size() - i)),
                    moveScale * (layers.size() - i));
        }
    }

    private class Layer {

        private final BufferedImage image;

        public Layer(BufferedImage image) {
            this.image = image;
        }

        public void draw(Graphics2D g, float offsetX, float offsetY, float scale) {
            g.drawImage(image,
                    (int)((Config.window_width_actual * .5f)
                            - (image.getWidth() * Config.scaledW_zoom * .5f)
                            + (offsetX * .5f * Config.scaledW_zoom)),
                    (int)((Config.window_height_actual * .5f)
                            - (image.getHeight() * Config.scaledH_zoom * .5f)
                            + (offsetY * .5f * Config.scaledH_zoom)),
                    (int)(image.getWidth() * Config.scaledW_zoom),
                    (int)(image.getHeight() * Config.scaledH_zoom),
                    null);
        }
    }

}
