package prototypes.window.environments.menu;

import models.controls.MenuControlsModel;
import models.controls.menu.MenuKeyControls;
import models.environments.menu.MenuBundle;
import prototypes.controls.AKeyController;
import prototypes.controls.AMouseController;
import prototypes.window.environments.menu.components.AMenuComponent;
import utils.config.ConfigData;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The type AMenu is an AEnvironment type which inherits both the draw() and update() methods.
 * The AMenu is used in any Menu Page or Menu Model. This assists with data encapsulation but also type-based passing.
 * <p>
 * Contains the parentMenuModel which holds all AMenus, both parent and child to the current AMenu
 * Contains a list of AMenus by the variable name "bundle", which are used as references to branching sub-AMenus.
 * Contains a list of AMenuButtons that define navigation and function of the page.
 */
public abstract class AMenu implements IUpdatable, IDrawable {

    // The Parent Menu Model
    protected AMenuModel parentMenuModel;
    // Controllers
    protected AKeyController keyController;
    protected AMouseController mouseController;
    // Background Image
    protected BufferedImage backgroundImage;

    // The Menu Buttons.
    public ArrayList<AMenuComponent> components = new ArrayList<>();

    /**
     * The Bundle of sub page AMenu objects.
     */
    public MenuBundle bundle = new MenuBundle();

    /**
     * Instantiates a new A menu.
     *
     * @param parentMenuModel the parent menu model
     */
    public AMenu(AMenuModel parentMenuModel) {
        this.parentMenuModel = parentMenuModel;

        keyController = parentMenuModel.getKeyController();
        mouseController = parentMenuModel.getMouseController();
    }

    /**
     *
     */
    public void registerInput() {
        if (parentMenuModel.getKeyController() instanceof MenuKeyControls kc) {
            if (kc.isAction(MenuControlsModel.Actions.ESCAPE)) {
                kc.resetInput();
                parentMenuModel.pop();
            }
        }
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImage,
                0, 0,
                ConfigData.window_width_actual, ConfigData.window_height_actual,
                null);

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
