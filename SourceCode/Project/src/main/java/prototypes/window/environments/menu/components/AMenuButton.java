package prototypes.window.environments.menu.components;

import models.controls.menu.MenuMouseControls;
import models.data.PreferenceData;
import prototypes.window.environments.menu.AMenuModel;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;

/**
 * The abstract type AMenuButton. Implements IUpdatable and IDrawable.
 *
 * This is to be used for the creation of anonymous buttons.
 * This can also be used as a parent class for specific AMenuButton types.
 */
public abstract class AMenuButton implements IUpdatable, IDrawable {

    // The Parent menu model which holds all pages and subpages.
    protected AMenuModel parentMenuModel;

    // Will be removed if we add actual images in place of awt graphics.
    private String text = "";

    private final int x, y; // Coordinates on the 2D space, relative to the default screen size
    private final int w, h; // The width of the component

    /**
     * Instantiates a new A menu button.
     * @param parentMenuModel the menu Model
     * @param x the x coordinate
     * @param y the y coordinate
     * @param w the width
     * @param h the height
     */
    public AMenuButton(AMenuModel parentMenuModel, int x, int y, int w, int h) {
        this.parentMenuModel = parentMenuModel;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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

        return horizBound && vertBound;
    }

    // TODO : To be used for activating animations or changing colors of the component
    //      Must be defined and implemented.
    //      Must define a global state variable to hold the passed value.
    protected void setActiveState(boolean isActive) {

    }

    /**
     * The abstract method which is defined either upon instantiation or within a deriving class.
     * It is used to define actions that the button will execute.
     * This method is called automatically through the registerInput() method every time the Menu updates.
     *
     * @param x the x
     * @param y the y
     * @return Returns true if the button registers as clicked. Use IsInBounds() in the definition to test this.
     */
    public abstract boolean onClick(float x, float y);

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        float sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;
        g.drawRect((int)(x * sW), (int)(y * sH), (int)(w * sW), (int)(h * sH));

        g.drawString(text, (int)((x * sW) + (5 * sW)), (int)((y * sH) + (20 * sH)));
    }

    @Override
    public void update(float delta) {
        registerInput();
    }

    /**
     * Checked every update.
     * Registers the input from the parent MainMenuModel class. That class is defined locally as parentMenuModel.
     */
    public void registerInput() {
        if(parentMenuModel.getMouseController() instanceof MenuMouseControls mc) {

            if (mc.isLeftPressed()) {
                if(onClick(mc.getPos()[0], mc.getPos()[1])) {
                    mc.resetInput();
                }
            }
        }
    }

    /**
     * Sets text of the button.
     * Should be removed once we add image sources.
     *
     * @param s - the String of text
     */
    public void setText(String s) {
        text = s;
    }
}
