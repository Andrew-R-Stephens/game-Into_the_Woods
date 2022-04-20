package models.prototypes.components.menuviews;

import models.environments.EnvironmentsHandler;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.sprites.SpriteSheet;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p></p>
 */
public abstract class AMenuComponent implements IUpdatable, IDrawable {

    protected SpriteSheet spritesheet;

    protected ImageScale scaleType = ImageScale.FILL_XY;

    protected Color foregroundColor = new Color(0, 0, 0);
    protected Color backgroundColor = new Color(0, 0, 0, 0);
    protected BufferedImage backgroundImage;
    protected BufferedImage tint;

    // The Parent menu model which holds all pages and subpages.
    private final AMenuEnvironment parentMenuEnvironment;

    // Text to be displayed.
    // Will be removed if we add actual images in place of awt views.graphics.
    protected String text = "";

    protected int x, y; // Coordinates on the 2D space, relative to the default screen size
    protected int w, h; // The width of the component

    protected boolean isFocused = false;
    protected boolean isPressed = false;
    protected boolean playSound = true;

    /**
     * <p></p>
     * @param parentMenuEnvironment
     */
    public AMenuComponent(AMenuEnvironment parentMenuEnvironment) {
        this.parentMenuEnvironment = parentMenuEnvironment;
    }

    /**
     * <p></p>
     * @return
     */
    protected AMenuEnvironment getParentEnvironment() {
        return parentMenuEnvironment;
    }

    /**
     * <p></p>
     * @return
     */
    protected EnvironmentsHandler getEnvironmentHandler() {
        return parentMenuEnvironment.getParentEnvironmentsHandler();
    }

    /**
     * <p></p>
     * @param mx -
     * @param my -
     * @return
     */
    protected boolean isInBounds(float mx, float my) {
        mx /= Config.scaledW;
        my /= Config.scaledH;

        boolean horizBound = (mx >= x && mx <= (x + w));
        boolean vertBound = (my >= y && my <= (y + h));

        setIsFocused(horizBound && vertBound);

        return horizBound && vertBound;
    }

    /**
     * <p></p>
     * @param isFocused -
     */
    protected void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    /**
     * <p></p>
     * @param x -
     * @param y -
     * @return
     */
    public abstract boolean onClick(float x, float y);

    /**
     * <p></p>
     */
    public abstract void registerInput();

    /**
     * <p></p>
     * @param delta -
     */
    public void updateSpriteSheet(float delta) {
        if(spritesheet != null) {
            if (backgroundImage == null && isFocused) {
                spritesheet.update(delta);
            } else {
                spritesheet.reset();
            }
        }
    }

    /**
     * <p></p>
     * @return
     */
    public int right() {
        return x + w;
    }

    /**
     * <p></p>
     * @param backgroundImage -
     */
    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * <p></p>
     * @param scaleType -
     */
    public void setImageScaling(ImageScale scaleType) {
        this.scaleType = scaleType;
    }

    /**
     * <p></p>
     * @param text -
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * <p></p>
     * @param spritesheet -
     */
    public void setSpritesheet(SpriteSheet spritesheet) {
        this.spritesheet = spritesheet;
    }

    /**
     * <p></p>
     * @param canPlaySound -
     */
    public void setPlaySound(boolean canPlaySound) {
        playSound = canPlaySound;
    }

    /**
     * <p></p>
     * @return
     */
    public boolean isPressed() {
        return isPressed;
    }

    /**
     * <p></p>
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * <p></p>
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * <p></p>
     * @return
     */
    public int getW() {
        return w;
    }

    /**
     * <p></p>
     * @return
     */
    public int getH() {
        return h;
    }

    public void reset() {
        if(spritesheet != null) {
            spritesheet.reset();
        }
        isFocused = false;
        isPressed = false;
    }

    @Override
    public void update(float delta) {
        isFocused = false;
        isPressed = false;

        registerInput();
        updateSpriteSheet(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        /*
        g.setColor(Color.RED);
        g.drawRect(
                (int)(Config.scaledW * x),
                (int)(Config.scaledW * y),
                (int)(Config.scaledH * w),
                (int)(Config.scaledH * h));
        */
    }

    /**
     * <p></p>
     */
    public enum ImageScale {
        FIT_CENTERED,
        CENTER_CROP,
        FILL_XY
    }

}
