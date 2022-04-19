package models.prototypes.components.menuviews.types;

import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class ATextView extends AMenuComponent implements IDrawable, IUpdatable {

    public ATextView(AMenuEnvironment parentMenuModel, int x, int y, int w, int h) {
        super(parentMenuModel);

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

        g.setColor(backgroundColor);
        g.fillRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)(h*sH));

        g.setColor(foregroundColor);
        int strHeight = h;
        g.setFont(
                getParentEnvironment().getResources().getFont("bahnschrift")
                        .deriveFont(AffineTransform.getScaleInstance(.8, 1))
                        .deriveFont(Font.BOLD, strHeight * sW));

        int strWidth = g.getFontMetrics().stringWidth(text.toUpperCase());
        while(strWidth > w * sW) {
            strHeight--;
            g.setFont(
                    getParentEnvironment().getResources().getFont("bahnschrift")
                            .deriveFont(AffineTransform.getScaleInstance(.8, 1))
                            .deriveFont(Font.BOLD, strHeight * sW));

            strWidth = g.getFontMetrics().stringWidth(text.toUpperCase());
        }
        int strHeightDiff = (int) (((g.getFontMetrics().getAscent() * sH) - (h * sW)) * .5f);
        g.drawString(
                text.toUpperCase(),
                (int)((x * sW) + (w * sW * .5) - (strWidth * .5)),
                (int)(((y * sH) + (h*sH))) + (strHeightDiff * sH));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void registerInput() {
        // DO NOTHING
    }

    public void reset() {
        playSound = true;
        super.reset();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }
}
