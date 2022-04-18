package models.prototypes.environments.menu.components.types;

import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.AMenuComponent;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AImageView extends AMenuComponent implements IDrawable, IUpdatable {

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

        g.setColor(new Color(255, 0, 0, 100));
        float sW = Config.scaledW, sH = Config.scaledH;

        g.drawRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));

        if(isFocused) {
            g.setColor(new Color(255, 255, 255, 50));
            g.fillRect((int) (x * sW), (int) (y * sH), (int) (w * sW), (int) (h * sH));
        }
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
