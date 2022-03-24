package prototypes.window.environments.menu;

import models.environments.menus.MenuBundle;
import prototypes.window.environments.AEnvironment;
import prototypes.window.environments.menu.components.AMenuComponent;

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
public abstract class AMenu extends AEnvironment {

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

    @Override
    public void draw(Graphics g) {



        for(AMenuComponent c: components) {
            c.draw(g);
        }

    }

    @Override
    public void update(float delta) {
        for(AMenuComponent c: components) {
            c.update(delta);
        }

    }
}
