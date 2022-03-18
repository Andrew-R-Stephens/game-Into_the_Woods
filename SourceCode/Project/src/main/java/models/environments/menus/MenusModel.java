package models.environments.menus;

import props.prototypes.window.environments.AEnvironment;
import props.prototypes.window.environments.menu.AMenuBundle;
import models.environments.menus.mainmenu.MainMenuModel;
import models.environments.menus.pausemenumodel.PauseMenuModel;
import props.prototypes.window.environments.menu.AMenu;
import utils.drawables.IDrawable;
import utils.updates.IUpdatable;

import java.awt.*;
import java.util.ArrayList;

/**
 * MenusModel class
 *
 * Contains the list of all AMenu objects.
 */
public class MenusModel extends AEnvironment implements IUpdatable, IDrawable {

    private ArrayList<AMenuBundle> menusList = new ArrayList<>();
    private int currentMenu = 0;

    public MenusModel() {
        menusList.add(new MainMenuModel());
        menusList.add(new PauseMenuModel());
    }

    public void setCurrentMenu() {

    }

    public AMenu getCurrentMenu() {
        return menusList.get(currentMenu);
    }

    @Override
    public void draw(Graphics g) {
        menusList.get(currentMenu).draw(g);
    }

    @Override
    public void update(float delta) {
        menusList.get(currentMenu).update(delta);
    }
}
