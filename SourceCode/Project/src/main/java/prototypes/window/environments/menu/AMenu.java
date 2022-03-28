package prototypes.window.environments.menu;

import models.controls.MenuControlsModel;
import models.controls.menu.MenuKeyControls;
import models.controls.menu.MenuMouseControls;
import models.environments.menu.MenuBundle;
import prototypes.controls.AKeyController;
import prototypes.controls.AMouseController;
import prototypes.window.environments.menu.components.AMenuComponent;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;
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

    /**
     * The Parent menu model.
     */
    protected AMenuModel parentMenuModel;
    /**
     * The Menu Buttons.
     */
    public ArrayList<AMenuComponent> components = new ArrayList<>();
    /**
     * The Bundle of sub page AMenu objects.
     */
    public MenuBundle bundle = new MenuBundle();

    protected AKeyController keyController;
    protected AMouseController mouseController;

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

    public void registerInput() {
        if(parentMenuModel.getKeyController() instanceof MenuKeyControls kc) {
            if(kc.isAction(MenuControlsModel.Actions.ESCAPE)) {
                kc.resetInput();
                parentMenuModel.pop();
            }
        }
    }

    @Override
    public void draw(Graphics g) {

        for(AMenuComponent c: components) {
            c.draw(g);
        }

    }

    @Override
    public void update(float delta) {

        registerInput();

        for(AMenuComponent c: components) {
            c.update(delta);
        }

    }
}
