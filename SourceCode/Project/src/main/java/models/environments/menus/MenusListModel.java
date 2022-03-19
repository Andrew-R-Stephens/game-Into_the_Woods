package models.environments.menus;

import props.prototypes.window.environments.AEnvironment;
import props.prototypes.window.environments.menu.AMenuModel;

import java.awt.*;
import java.util.ArrayList;

/**
 * MenusModel class
 *
 * Contains the list of all AMenu objects.
 */
public class MenusListModel extends AEnvironment {

    private ArrayList<AMenuModel> menuModels = new ArrayList<>();
    private int currentMenu = 0;

    public void init(AMenuModel mainMenuModel) {
        menuModels.add(mainMenuModel);
    }

    @Override
    public void draw(Graphics g) {
        menuModels.get(currentMenu).draw(g);
    }

    @Override
    public void update(float delta) {
        menuModels.get(currentMenu).update(delta);
    }

}
