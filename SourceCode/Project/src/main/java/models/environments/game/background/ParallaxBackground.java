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
 * @author Andrew Stephens
 */
public class ParallaxBackground implements IDrawable {

    /**<p>The list of background layers.</p>*/
    private final ArrayList<Layer> layers = new ArrayList<>();
    /**<p>The standard scale of movement for the front-most image.</p>*/
    private final float moveScale = .05f;

    /**
     * Adds a new Image layer to the list of background images.
     * @param bufferedImage The new image layer
     */
    public void addLayer(BufferedImage bufferedImage) {
        layers.add(new Layer(bufferedImage));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        for(int i = 0; i < layers.size(); i++) {
            layers.get(i).draw(
                    g,
                    ((Camera.camX + (Config.window_width_actual * .5f))/Camera.zoomLevel) * (moveScale * (i+1)),
                    ((Camera.camY + (Config.window_height_actual * .5f))/Camera.zoomLevel) * (moveScale * (i+1))
            );
        }

        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
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
            int windowWHalf = (int)Math.floor(Config.window_width_actual * .5f);
            int windowHHalf = (int)Math.floor(Config.window_height_actual * .5f);
            int scaleImgW = (int)Math.ceil(image.getWidth() * Config.scaledW_zoom);
            int scaleImgH = (int)Math.ceil(image.getHeight() * Config.scaledH_zoom);

            g.drawImage(image,
                    (int) (windowWHalf
                            - Math.ceil(scaleImgW * .5f)
                            + Math.ceil(offsetX * .5f)),
                    (int) (windowHHalf
                            - Math.ceil(scaleImgH * .5f)
                            + Math.ceil(offsetY * .5f)),
                    scaleImgW, scaleImgH,
                    null);
            /*
            g.drawImage(image,
                (int) ((Config.window_halfWidth_actual)
                        - (scaleImgW * .5f)
                        + (offsetX * .5f)),
                (int) ((Config.window_halfHeight_actual)
                        - (scaleImgH * .5f)
                        + (offsetY * .5f)),
                (int) scaleImgW, (int) scaleImgH,
                null);
            */
        }
    }

}
