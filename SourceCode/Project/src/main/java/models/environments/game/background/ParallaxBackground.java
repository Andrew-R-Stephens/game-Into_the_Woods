package models.environments.game.background;

import models.camera.Camera;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>The ParallaxBackground contains references to images. These images act as layers in the moving background of the
 * level.</p>
 * <p>The level background will move, and each layer will move at separate rates, respective to their layer number.
 * The closer the layer is to the player, the faster the layers will move.</p>
 */
public class ParallaxBackground implements IDrawable {

    private final ArrayList<Layer> layers = new ArrayList<>();

    private float moveScale = .05f;

    /**
     * Adds a new Image layer to the list of background images.
     * @param bufferedImage The new image layer
     */
    public void addLayer(BufferedImage bufferedImage) {
        layers.add(new Layer(bufferedImage));
    }

    @Override
    public void draw(Graphics2D g) {
        for(int i = 0; i < layers.size(); i++) {
            layers.get(i).draw(
                    g,
                    Camera.camX/Camera.zoomLevel * (moveScale * (i+1)),
                    Camera.camY/Camera.zoomLevel * (moveScale * (i+1))
            );
        }
    }

    /**
     * A private record which contains a reference to a specific local background image.
     * @param image The background image this Layer will draw
     */
    private record Layer(BufferedImage image) {

        /**
         * A custom draw method that accepts the offset of the player to control to position of the background.
         * @param g The graphics object used to draw
         * @param offsetX The horizontal offset of the player
         * @param offsetY The vertical offset of the player
         */
        public void draw(Graphics2D g, float offsetX, float offsetY) {
            g.drawImage(image,
                    (int) ((Config.window_width_actual * .5f)
                            - (image.getWidth() * Config.scaledW_zoom * .5f)
                            + (offsetX * .5f)),
                    (int) ((Config.window_height_actual * .5f)
                            - (image.getHeight() * Config.scaledH_zoom * .5f)
                            + (offsetY * .5f)),
                    (int) (image.getWidth() * Config.scaledW_zoom),
                    (int) (image.getHeight() * Config.scaledH_zoom),
                    null);
        }
    }

}
