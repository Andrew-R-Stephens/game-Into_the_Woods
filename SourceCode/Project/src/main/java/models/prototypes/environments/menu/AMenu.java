package models.prototypes.environments.menu;

import controls.MenuControls;
import controls.menu.MenuKeyControls;
import models.environments.EnvironmentsHandler;
import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.controls.AKeyController;
import models.prototypes.controls.AMouseController;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>The AMenu is a superclass to all menu pages. This is a dynamic class which enables for unlimited AMenuComponent
 * additions. This class exists to deliver a seamless experience to the user with little cost to performance.</p>
 * <p>The AMenu handles user input and either passes the input to each AMenuComponent. Or handles the request
 * locally, depending on the input made.</p>
 * <p>All AMenus exist within an AMenuEnvironment. To be seen or controlled, they must exist at the top of the
 * environment's stack.</p>
 * @author Andrew Stephens
 */
public abstract class AMenu implements IUpdatable, IDrawable {

    /**<p>The Parent Menu Model</p>*/
    private AMenuEnvironment parentEnvironment;
    /**<p> Background Image</p>*/
    protected BufferedImage image_buttonRect;

    /**<p>The Menu Buttons.</p>*/
    private ArrayList<AMenuComponent> components = new ArrayList<>();

    /**<p>The centered position of the screen.</p>*/
    protected float centerW = Config.DEFAULT_WINDOW_WIDTH * .5f, centerH = Config.DEFAULT_WINDOW_HEIGHT * .5f;

    /**
     * <p>Assigns the the Menu with the parent AMenuEnvironment for reflection.</p>
     * <p>Should be called through subclass inheritance.</p>
     * @param parentEnvironment The AMenuEnvironment that this Menu is contained within.
     */
    public AMenu(AMenuEnvironment parentEnvironment) {
        this.parentEnvironment = parentEnvironment;
    }

    /**
     * <p>Obtains the controller and listens for specific input and executes the content if input is accepted.</p>
     * @return if the input has been accepted.
     */
    public boolean registerInput() {
        boolean isActivated = false;
        if (parentEnvironment.getKeyController() instanceof MenuKeyControls kc) {
            isActivated = kc.isAction(MenuControls.Actions.ESCAPE);
            if (isActivated) {
                kc.reset();
                parentEnvironment.pop();
            }
        }
        return isActivated;
    }

    /**
     * <p>Gets the AMenuEnvironment containing this Menu</p>
     * @return The AMenuEnvironment that this Menu is contained within.
     */
    public AMenuEnvironment getParentEnvironment() {
        return parentEnvironment;
    }

    /**
     * <p>Gets the EnvironmentsHander that contains this AMenuEnvironment.</p>
     * @return The EnvironmentsHandler that this Menu is held within. Should not be null.
     */
    public EnvironmentsHandler getEnvironmentsHandler() {
        return parentEnvironment.getParentEnvironmentsHandler();
    }

    /**
     * <p>Gets the Resources.</p>
     * @return the parentEnvironment's Resources. Should not be null.
     */
    public Resources getResources() {
        return parentEnvironment.getResources();
    }

    /**
     * <p>Gets the MouseController for the containing Environment.</p>
     * @return the parentEnvironment's MouseController. Should not be null.
     */
    public AMouseController getMouseController() {
        return parentEnvironment.getMouseController();
    }

    /**
     * <p>Gets the KeyController for the containing Environment.</p>
     * @return the parentEnvironment's KeyController. Should not be null.
     */
    public AKeyController getKeyController() {
        return parentEnvironment.getKeyController();
    }

    /**
     * <p>Accepts an AMenuComponent object and adds it to the components list.</p>
     * @param component An AMenuComponent that defines a functionality in the Menu
     */
    public void addComponent(AMenuComponent component){
        components.add(component);
    }

    /**
     * <p>Resets all the contained AMenuComponents.</p>
     */
    public void reset() {
        for(AMenuComponent c: components) {
            c.reset();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        for (AMenuComponent c : components) {
            c.draw(g);
        }
    }

    @Override
    public void update(float delta) {
        registerInput();

        for (AMenuComponent c : components) {
            c.update(delta);
        }
    }

}
