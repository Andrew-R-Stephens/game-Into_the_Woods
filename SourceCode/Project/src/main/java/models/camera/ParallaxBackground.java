package models.camera;

import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

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
            layers.get(i).draw(g, Camera.targX * (moveScale * (layers.size() - i)), Camera.targY * (moveScale * (layers.size() - i)));
        }
    }

    private class Layer {

        private final BufferedImage image;

        public Layer(BufferedImage image) {
            this.image = image;
        }

        public void draw(Graphics2D g, float offsetX, float offsetY) {
            g.drawImage(image,
                    (int)((Config.window_width_actual * .5f) - (image.getWidth() * Config.scaledW * .5f) + offsetX),
                    (int)((Config.window_height_actual * .5f) - (image.getHeight() * Config.scaledH * .5f) + offsetY),
                    (int)(image.getWidth() * Config.scaledW),
                    (int)(image.getHeight() * Config.scaledH), null);
        }
    }

}
