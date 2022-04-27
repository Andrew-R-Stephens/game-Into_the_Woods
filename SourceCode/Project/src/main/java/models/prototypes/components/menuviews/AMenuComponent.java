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
 * <p>AMenuComponent is the parent class of all Menu components. They are both updated and drawn.</p>
 * <p>In their basic form, they contain positional data and size data relative to the default screen dimensions.</p>
 * <p>Text may be present in the component if allowed. Sound may also emit if a trigger is present.</p>
 * <p>Contain a spriteSheet, foreground and backgrounds, background images, and tints.</p>
 * @author Andrew Stephens
 */
public abstract class AMenuComponent implements IUpdatable, IDrawable {

    /**
     * <p>The Parent menu model which holds all pages and subpages.</p>
     */
    private final AMenuEnvironment parentMenuEnvironment;

    /**<p>The SpriteSheet that will be used to draw this component.</p>*/
    protected SpriteSheet spritesheet;
    /**<p>The scaling procedure of the background image of this component.</p>*/
    protected ImageScale scaleType = ImageScale.FILL_XY;
    /**<p>The foreground color overlaying the other parts of the component.</p>*/
    protected Color foregroundColor = new Color(0, 0, 0);
    /**<p>The background color under the other parts of the component.</p>*/
    protected Color backgroundColor = new Color(0, 0, 0, 0);

    /**<p>The background image of the component.</p>*/
    protected BufferedImage backgroundImage;
    /**<p>The image's tint of an image in the component.</p>*/
    protected BufferedImage tint;

    /**<p>Text to be displayed. Will be removed if we add actual images in place of awt views.graphics.</p>*/
    protected String text = "";

    /**<p>The horizontal position of the component.</p>*/
    protected int x;
    /**<p>The vertical position of the component.</p>*/
    protected int y;

    /**<p>The width of the component.</p>*/
    protected int w;
    /**<p>The height of the component.</p>*/
    protected int h;

    /**<p>If the component has focus from the user.</p>*/
    protected boolean isFocused = false;
    /**<p>If the component has been activated by the user.</p>*/
    protected boolean isPressed = false;
    /**<p>If the component should play audio.</p>*/
    protected boolean playSound = true;

    /**
     * <p>Initializes the AMenuComponent</p>
     * @param parentMenuEnvironment The AMenuEnvironment containing the Menu that contains this component.
     * @param x The horizontal position of the component
     * @param y The vertical position of the component
     * @param w The width of the component
     * @param h The height of the component
     */
    public AMenuComponent(AMenuEnvironment parentMenuEnvironment, int x, int y, int w, int h) {
        this.parentMenuEnvironment = parentMenuEnvironment;
    }

    /**
     * <p>Gets the parent AMenuEnvironment that contains the Menu of this component.</p>
     * @return the Menu's AMenuEnvironment.
     */
    protected AMenuEnvironment getParentEnvironment() {
        return parentMenuEnvironment;
    }

    /**
     * <p>Gets the parent EnvironmentsHandler that contains the Menu of this component.</p>
     * @return the Menu's AMenuEnvironment.
     */
    protected EnvironmentsHandler getEnvironmentsHandler() {
        return parentMenuEnvironment.getParentEnvironmentsHandler();
    }

    /**
     * <p>Checks if the coordinates passed are within the bounds of this component.</p>
     * <p>Typically used with the mouse coordinates. Typically called from the registerInput() methods.</p>
     * @param mx The horizontal position.
     * @param my The vertical position.
     * @return If the coordinate passed is both horizontally and vertically within the bounds.
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
     * <p>Sets if the component is being hovored over.</p>
     * @param isFocused If the component has focus.
     */
    protected void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    /**
     * <p>Defined by the subtype component or by abstract creation. Meant to be used on a click event.</p>
     * @param x The horizontal position of the mouse.
     * @param y The vertical position of the mouse.
     * @return If there was a successful click event.
     */
    public abstract boolean onClick(float x, float y);

    /**
     * <p>Defined by the subtype component or by abstract creation. Meant to be used on an update method to check
     * for any user input from the current controller.</p>
     */
    public abstract void registerInput();

    /**
     * <p>Updates the spriteSheet to the next frame.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
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
     * <p>Gets the right side of the component</p>
     * @return x position + width
     */
    public int right() {
        return x + w;
    }

    /**
     * <p>Sets the background image</p>
     * @param backgroundImage the background buffered image
     */
    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * <p>Sets the Image Scale</p>
     * @param scaleType The type of scaling that the background image will follow.
     */
    public void setImageScaling(ImageScale scaleType) {
        this.scaleType = scaleType;
    }

    /**
     * <p>Sets the text of the component.</p>
     * @param text The text for the component.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * <p>Sets the spriteSheet for the component animation.</p>
     * @param spritesheet The spriteSheet to be used in the animation.
     */
    public void setSpritesheet(SpriteSheet spritesheet) {
        this.spritesheet = spritesheet;
    }

    /**
     * <p>Sets if the component can play audio.</p>
     * @param canPlaySound If the component can play audio.
     */
    public void setPlaySound(boolean canPlaySound) {
        playSound = canPlaySound;
    }

    /**
     * <p>Gets if the component is pressed.</p>
     * @return if the component is pressed.
     */
    public boolean isPressed() {
        return isPressed;
    }

    /**
     * <p>Gets the X coordinate.</p>
     * @return The horizontal coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * <p>Gets the Y coordinate.</p>
     * @return The vertical coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * <p>Gets the Width of the component</p>
     * @return the width of the component.
     */
    public int getW() {
        return w;
    }

    /**
     * <p>Gets the Height of the component.</p>
     * @return The height of the component.
     */
    public int getH() {
        return h;
    }

    /**
     * <p>Resets the spriteSheet and the state of the component to default.</p>
     */
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
     * <p>The Image Scale enumeration for easier configuration.</p>
     */
    public enum ImageScale {
        FIT_CENTERED,
        CENTER_CROP,
        FILL_XY
    }

}
