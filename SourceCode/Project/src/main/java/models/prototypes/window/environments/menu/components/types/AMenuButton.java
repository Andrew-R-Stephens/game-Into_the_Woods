package models.prototypes.window.environments.menu.components.types;

import models.prototypes.controls.AMouseController;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.AMenuComponent;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class AMenuButton extends AMenuComponent implements IDrawable, IUpdatable {

    public boolean playSound = true;

    public AMenuButton(AMenuEnvironment parentMenuModel, int x, int y, int w, int h) {
        super(parentMenuModel);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        spritesheet = Resources.getSpriteSheet("buttonhrect_spritesheet").setLoopOnLast(false);
    }

    public AMenuButton(AMenuEnvironment parentMenuModel, int x, int y) {
        super(parentMenuModel);

        this.x = x;
        this.y = y;
        this.w = 200;
        this.h = 50;

        spritesheet = Resources.getSpriteSheet("buttonhrect_spritesheet").setLoopOnLast(false);
    }

    public void playSound() {
        if(playSound) {
            Resources.playAudio("buttonclick");
        }
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.RED);
        g.drawRect(
                (int)(ConfigData.scaledW * x),
                (int)(ConfigData.scaledW * y),
                (int)(ConfigData.scaledW * w),
                (int)(ConfigData.scaledW * h));

        float sW = ConfigData.scaledW, sH = ConfigData.scaledH;

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
                Resources.getFont("bahnschrift")
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
        AMouseController mc = parentMenuEnvironment.getMouseController();
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

    public void setText(String s) {
        text = s;
    }

    public void reset() {
        playSound = true;
        super.reset();
    }

}
