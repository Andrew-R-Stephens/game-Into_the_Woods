package models.prototypes.environments.menu;

import controls.MenuControls;
import controls.menu.MenuKeyControls;
import models.environments.EnvironmentsHandler;
import models.prototypes.controls.AKeyController;
import models.prototypes.controls.AMouseController;
import models.prototypes.components.menuviews.AMenuComponent;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

    public AMenu(AMenuEnvironment parentMenuModel) {
        this.parentEnvironment = parentMenuModel;
    }

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

    public AMenuEnvironment getParentEnvironment() {
        return parentEnvironment;
    }

    public EnvironmentsHandler getEnvironmentsHandler() {
        return parentEnvironment.getParentEnvironmentsHandler();
    }

    public Resources getResources() {
        return parentEnvironment.getResources();
    }

    public AMouseController getMouseController() {
        return parentEnvironment.getMouseController();
    }

    public AKeyController getKeyController() {
        return parentEnvironment.getKeyController();
    }

    public void addComponent(AMenuComponent component){
        components.add(component);
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

    public void reset() {
        for(AMenuComponent c: components) {
            c.reset();
        }
    }
}
