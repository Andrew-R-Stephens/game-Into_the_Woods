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
 */
public abstract class AMenu implements IUpdatable, IDrawable {

    // The Parent Menu Model
    private AMenuEnvironment parentEnvironment;
    // Background Image
    protected BufferedImage image_buttonRect;

    // The Menu Buttons.
    private ArrayList<AMenuComponent> components = new ArrayList<>();

    // The Bundle of sub page AMenu objects.
    //public MenuBundle bundle = new MenuBundle();

    protected float centerW = Config.DEFAULT_WINDOW_WIDTH * .5f, centerH = Config.DEFAULT_WINDOW_HEIGHT * .5f;

    /**
     * <p></p>
     * @param parentMenuModel -
     */
    public AMenu(AMenuEnvironment parentMenuModel) {
        this.parentEnvironment = parentMenuModel;
    }

    /**
     * <p></p>
     * @return
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
     * <p></p>
     * @return
     */
    public AMenuEnvironment getParentEnvironment() {
        return parentEnvironment;
    }

    /**
     * <p></p>
     * @return
     */
    public EnvironmentsHandler getEnvironmentsHandler() {
        return parentEnvironment.getParentEnvironmentsHandler();
    }

    /**
     * <p></p>
     * @return
     */
    public Resources getResources() {
        return parentEnvironment.getResources();
    }

    /**
     * <p></p>
     * @return
     */
    public AMouseController getMouseController() {
        return parentEnvironment.getMouseController();
    }

    /**
     * <p></p>
     * @return
     */
    public AKeyController getKeyController() {
        return parentEnvironment.getKeyController();
    }

    /**
     * <p></p>
     * @param component
     */
    public void addComponent(AMenuComponent component){
        components.add(component);
    }

    /**
     *
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
