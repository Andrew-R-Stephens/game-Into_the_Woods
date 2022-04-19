package models.prototypes.components.menuviews.types;

import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.controls.AMouseController;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class AButtonView extends AMenuComponent implements IDrawable, IUpdatable {

    public boolean isEnabled = true;

    public AButtonView(AMenuEnvironment parentMenuModel, int x, int y, int w, int h) {
        super(parentMenuModel);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        spritesheet =
                getParentEnvironment().getResources().getSpriteSheet("spritesheet_buttonhrect").setLoopOnLast(false);
    }

    public void playSound() {
        if(playSound) {
            getParentEnvironment().getResources().playAudio("buttonclick");
        }
    }

    @Override
    public void draw(Graphics2D g) {

        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        float opacity = 1f;
        if(!isEnabled) {
            opacity = .05f;
        }
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

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

        g.setColor(foregroundColor);

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

    public void setX(int x) {
        this.x = x;
    }

    public void registerInput() {
        AMouseController mc = getParentEnvironment().getMouseController();
        if (mc.isLeftPressed()) {
            isPressed = onClick(mc.getPos()[0], mc.getPos()[1]);
            if(isPressed) {
                mc.reset();
                playSound();
                playSound = false;
            } else {
                playSound = true;
            }
        } else {
            isInBounds(mc.getPos()[0], mc.getPos()[1]);
        }
    }

    public void reset() {
        playSound = true;
        super.reset();
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
