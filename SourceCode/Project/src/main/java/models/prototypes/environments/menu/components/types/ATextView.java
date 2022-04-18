package models.prototypes.environments.menu.components.types;

import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.AMenuComponent;
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
    public void draw(Graphics2D g) {

        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        if(backgroundImage != null) {
            switch (scaleType) {

                case FIT_CENTERED -> {
                    float sBW = w / (float)backgroundImage.getWidth();
                    float sBH = h / (float)backgroundImage.getHeight();
                    float s = Math.min(sBW, sBH);
                    g.drawImage(backgroundImage,
                            (int)(((x * sW) + (w * sW * .5f)) - (backgroundImage.getWidth() * sW * s * .5f)),
                            (int)(((y * sH) + (h * sH * .5f)) - (backgroundImage.getHeight() * sH * s * .5f)),
                            (int)(backgroundImage.getWidth() * sW * s),
                            (int)(backgroundImage.getHeight() * sH * s),
                            null);

                }
                case CENTER_CROP -> {
                    float sBW = w / (float)backgroundImage.getWidth();
                    float sBH = h / (float)backgroundImage.getHeight();
                    float s = Math.min(sBW, sBH);
                    int     nx = (int)((backgroundImage.getWidth() - (backgroundImage.getWidth() * s)) * .5f),
                            nw = (int)(backgroundImage.getWidth() * s);
                    BufferedImage croppedImage =
                            backgroundImage.getSubimage(
                                    nx, 0,
                                    nw, backgroundImage.getHeight()
                            );
                    g.drawImage(croppedImage,
                            (int)(x * sW),
                            (int)(y * sH),
                            (int)(w * sW),
                            (int)(h * sH),
                            null);
                }
                default -> {
                    g.drawImage(backgroundImage, (int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH), null);

                }

            }
        } else {
            spritesheet.draw(g, (int) (x * sW), (int) (y * sH), (int) (w * sW), (int) (h * sH));
        }

        g.setColor(Color.BLACK);

        g.setFont(
                getParentEnvironment().getResources().getFont("bahnschrift")
                        .deriveFont(AffineTransform.getScaleInstance(.8, 1))
                        .deriveFont(Font.BOLD, 36 * sW));

        int strWidth = g.getFontMetrics().stringWidth(text.toUpperCase());
        g.drawString(
                text.toUpperCase(),
                (int)((x * sW) + (w * sW * .5) - (strWidth * .5)),
                (int)((y * sH) + (h * sH * .5) + (h * .2 * sH * (spritesheet.getPercentCompleted()))));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void registerInput() {

    }

    public void setText(String s) {
        text = s;
    }

    public void reset() {
        playSound = true;
        super.reset();
    }

}
