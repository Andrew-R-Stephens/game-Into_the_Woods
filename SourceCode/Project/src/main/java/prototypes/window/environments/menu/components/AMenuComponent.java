package prototypes.window.environments.menu.components;

import models.data.PreferenceData;
import prototypes.window.environments.menu.AMenuModel;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The abstract type AMenuButton. Implements IUpdatable and IDrawable.
 * <p>
 * This is to be used for the creation of anonymous buttons.
 * This can also be used as a parent class for specific AMenuButton types.
 */
public abstract class AMenuComponent implements IUpdatable, IDrawable {

    protected ImageScale scaleType = ImageScale.FILL_XY;

    /**
     * The enum Image scale.
     */
    public enum ImageScale {
        FIT_CENTERED,
        CENTER_CROP,
        FILL_XY
    }

    protected BufferedImage backgroundImage;

    /**
     * The Parent menu model.
     */
    // The Parent menu model which holds all pages and subpages.
    protected AMenuModel parentMenuModel;

    // Text to be displayed.
    // Will be removed if we add actual images in place of awt graphics.
    protected String text = "";

    protected int x, y; // Coordinates on the 2D space, relative to the default screen size
    protected int w, h; // The width of the component

    protected boolean isFocused = false;

    /**
     * Instantiates a new AComponent.
     *
     * @param parentMenuModel the menu Model
     */
    public AMenuComponent(AMenuModel parentMenuModel) {
        this.parentMenuModel = parentMenuModel;
    }

    /**
     * Checks if the passed coordinates are within the bounds of the component.
     * Takes screen scale into consideration.
     *
     * @param mx the mx
     * @param my the my
     * @return if the passed coordinates are within the bounds of the object.
     */
    protected boolean isInBounds(float mx, float my) {
        mx /= PreferenceData.scaledW;
        my /= PreferenceData.scaledH;

        boolean horizBound = (mx >= x && mx <= (x + w));
        boolean vertBound = (my >= y && my <= (y + h));

        setIsFocused(horizBound && vertBound);

        return horizBound && vertBound;
    }

    /**
     * If the object is allowed to have a focused state, this will cause the button to react when the user
     * mouses over it.
     *
     * @param isFocused - if the component is focused on.
     */
    protected void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public abstract boolean onClick(float x, float y);

    @Override
    public void draw(Graphics g) {
        /*
        g.setColor(Color.RED);
        float sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;
        g.drawRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));

        if(backgroundImage != null) {
            switch (scaleType) {
                case FIT_CENTERED -> {
                    float sBW = w / (float)backgroundImage.getWidth();
                    float sBH = h / (float)backgroundImage.getHeight();
                    float s = Math.min(sBW, sBH);
                    g.drawImage(backgroundImage,
                            (int)(((x * sW) + (w * .5f)) - (backgroundImage.getWidth() * s * .5f)),
                            (int)(((y * sH) + (h * .5f)) - (backgroundImage.getHeight() * s * .5f)),
                            (int)(backgroundImage.getWidth() * s),
                            (int)(backgroundImage.getHeight() * s),
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
                            (int)(w * sH),
                            (int)(h * sH),
                            null);
                }
                default -> {
                    g.drawImage(backgroundImage, (int)(x * sW), (int)(y * sW), (int)(w * sH), (int)(h * sH), null);
                }
            }
        }

        g.drawString(text, (int)((x * sW) + (5 * sW)), (int)((y * sH) + (20 * sH)));

        if(isFocused) {
            g.setColor(new Color(255, 255, 255, 50));
            g.fillRect((int) (x * sW), (int) (y * sH), (int) (w * sW), (int) (h * sH));
        }
        */
    }

    @Override
    public void update(float delta) {
        registerInput();
    }

    /**
     * Checked every update.
     * Registers the input from the parent MainMenuModel class. That class is defined locally as parentMenuModel.
     */
    public abstract void registerInput();

    /**
     * Sets background image.
     *
     * @param backgroundImage the background image
     */
    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Sets image scaling.
     *
     * @param scaleType the scale type
     */
    public void setImageScaling(ImageScale scaleType) {
        this.scaleType = scaleType;
    }

}
