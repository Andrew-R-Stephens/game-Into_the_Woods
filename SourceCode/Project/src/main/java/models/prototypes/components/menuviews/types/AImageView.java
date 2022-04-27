package models.prototypes.components.menuviews.types;

import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>The AImageView is a class that simply displays an image based on the rendering procedure specified.</p>
 * @author Andrew Stephens
 */
public class AImageView extends AMenuComponent implements IDrawable, IUpdatable {

    /**
     * <p>Initializes the AImageView</p>
     * @param parentMenuEnvironment The AMenuEnvironment containing the Menu that contains this component.
     * @param x The horizontal position of the component
     * @param y The vertical position of the component
     * @param w The width of the component
     * @param h The height of the component
     * @param bufferedImage the background image
     * @param scaleType The scaling type of the background image
     */
    public AImageView(AMenuEnvironment parentMenuEnvironment,
                      int x, int y, int w, int h,
                      BufferedImage bufferedImage, ImageScale scaleType) {
        super(parentMenuEnvironment, x, y, w, h);

        setBackgroundImage(bufferedImage);
        setImageScaling(scaleType);

    }

    @Override
    public boolean onClick(float x, float y) {
        return false;
    }

    @Override
    public void draw(Graphics2D g) {

        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        g.drawRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void registerInput() {
        // DO NOTHING
    }

}
