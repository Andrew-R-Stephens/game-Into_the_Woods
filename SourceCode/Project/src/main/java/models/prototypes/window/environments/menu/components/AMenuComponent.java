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

    protected int x, y; // Coordinates on the 2D space, relative to the default screen size
    protected int w, h; // The width of the component

    protected boolean isFocused = false;

    public AMenuComponent(AMenuEnvironment parentMenuModel) {
        this.parentMenuEnvironment = parentMenuModel;
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
    public void draw(Graphics g) {

    }

    @Override
    public void update(float delta) {
        isFocused = false;
        registerInput();

        if(backgroundImage == null && isFocused) {
            spritesheet.update(delta);
        } else {
            spritesheet.reset();
        }
    }

    public abstract void registerInput();

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setImageScaling(ImageScale scaleType) {
        this.scaleType = scaleType;
    }

    public void setTint(String colorHex)
    {
        /*
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

        *//*
        System.out.println("Alpha is " + c.getAlpha());
        System.out.println("Red is " + c.getRed());
        System.out.println("Green is " + c.getGreen());
        System.out.println("Blue is " + c.getBlue());
        *//*

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
    */

    }

    public void reset() {
        spritesheet.reset();
        isFocused = false;
    }

    public void setSpritesheet(SpriteSheet spritesheet) {
        this.spritesheet = spritesheet;
    }

}
