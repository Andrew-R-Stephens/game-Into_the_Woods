package models.prototypes.components.menuviews.types;

import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p></p>
 */
public class AImageView extends AMenuComponent implements IDrawable, IUpdatable {

    /**
     * <p></p>
     * @param parentMenuModel -
     * @param x -
     * @param y -
     * @param w -
     * @param h -
     * @param bufferedImage -
     * @param scaleType -
     */
    public AImageView(AMenuEnvironment parentMenuModel, int x, int y, int w, int h, BufferedImage bufferedImage, ImageScale scaleType) {
        super(parentMenuModel);

        setBackgroundImage(bufferedImage);
        setImageScaling(scaleType);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

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
