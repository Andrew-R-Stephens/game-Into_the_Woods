package models.prototypes.window.environments.menu.components;

import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.sprites.SpriteSheet;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AMenuComponent implements IUpdatable, IDrawable {

    protected SpriteSheet spritesheet;

    protected ImageScale scaleType = ImageScale.FILL_XY;

    public enum ImageScale {
        FIT_CENTERED,
        CENTER_CROP,
        FILL_XY
    }

    protected BufferedImage backgroundImage;
    protected BufferedImage tint;

    // The Parent menu model which holds all pages and subpages.
    protected AMenuEnvironment parentMenuEnvironment;

    // Text to be displayed.
    // Will be removed if we add actual images in place of awt views.graphics.
    protected String text = "";

    public int x, y; // Coordinates on the 2D space, relative to the default screen size
    public int w, h; // The width of the component

    protected boolean isFocused = false;
    public boolean isPressed = false;

    public AMenuComponent(AMenuEnvironment parentMenuEnvironment) {
        this.parentMenuEnvironment = parentMenuEnvironment;
    }

    protected boolean isInBounds(float mx, float my) {
        mx /= ConfigData.scaledW;
        my /= ConfigData.scaledH;

        boolean horizBound = (mx >= x && mx <= (x + w));
        boolean vertBound = (my >= y && my <= (y + h));

        setIsFocused(horizBound && vertBound);

        return horizBound && vertBound;
    }

    protected void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public abstract boolean onClick(float x, float y);

    @Override
    public void update(float delta) {
        isFocused = false;
        isPressed = false;
        registerInput();

        if(spritesheet != null) {
            if (backgroundImage == null && isFocused) {
                spritesheet.update(delta);
            } else {
                spritesheet.reset();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(
                (int)(ConfigData.scaledW * x),
                (int)(ConfigData.scaledW * y),
                (int)(ConfigData.scaledH * w),
                (int)(ConfigData.scaledH * h));

    }

    public int right() {
        return x + w;
    }

    public abstract void registerInput();

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setImageScaling(ImageScale scaleType) {
        this.scaleType = scaleType;
    }

    public void reset() {
        if(spritesheet != null) {
            spritesheet.reset();
        }
        isFocused = false;
        isPressed = false;
    }

    public void setSpritesheet(SpriteSheet spritesheet) {
        this.spritesheet = spritesheet;
    }

}
