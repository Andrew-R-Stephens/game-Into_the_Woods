package models.prototypes.window.environments.menu.components;

import models.prototypes.window.environments.menu.AMenuModel;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

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
    protected BufferedImage tint;

    /**
     * The Parent menu model.
     */
    // The Parent menu model which holds all pages and subpages.
    protected AMenuModel parentMenuModel;

    // Text to be displayed.
    // Will be removed if we add actual images in place of awt views.graphics.
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
        mx /= ConfigData.scaledW;
        my /= ConfigData.scaledH;

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

    public void setTint(String colorHex)
    {
        Color c = new Color(
                Integer.valueOf( colorHex.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorHex.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorHex.substring( 5, 7 ), 16 ),
                Integer.valueOf( colorHex.substring( 7, 9 ), 16 )
        );

        BufferedImage tintedSprite =
                new BufferedImage(backgroundImage.getWidth(), backgroundImage.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = tintedSprite.createGraphics();
        graphics.drawImage(backgroundImage, 0, 0, null);
        graphics.dispose();

        /*
        System.out.println("Alpha is " + c.getAlpha());
        System.out.println("Red is " + c.getRed());
        System.out.println("Green is " + c.getGreen());
        System.out.println("Blue is " + c.getBlue());
        */

        for (int i = 0; i < tintedSprite.getWidth(); i++)
        {
            for (int j = 0; j < tintedSprite.getHeight(); j++)
            {
                int ax = tintedSprite.getColorModel().getAlpha(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int rx = tintedSprite.getColorModel().getRed(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int gx = tintedSprite.getColorModel().getGreen(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                int bx = tintedSprite.getColorModel().getBlue(tintedSprite.getRaster().
                        getDataElements(i, j, null));
                rx *= c.getRed();
                gx *= c.getGreen();
                bx *= c.getBlue();
                ax *= c.getAlpha();
                tintedSprite.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx));
            }
        }

        tint = tintedSprite;

    }

}
