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
            layers.get(i).draw(g, Camera.camX * (moveScale * (layers.size() - i)), Camera.camY * (moveScale * (layers.size()) - i));
        }
    }

    private class Layer {

        private final BufferedImage image;

        public Layer(BufferedImage image) {
            this.image = image;
        }

        /* TODO: Scale image against window size
         *
         */

        public void draw(Graphics2D g, float offsetX, float offsetY) {
            g.drawImage(image, (int)offsetX, (int)offsetY, image.getWidth(), image.getHeight(), null);
        }
    }

}
