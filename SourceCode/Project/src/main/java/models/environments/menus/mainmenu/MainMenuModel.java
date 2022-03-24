package models.environments.menus.mainmenu;

import models.environments.menus.mainmenu.children.StartScreenPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;

import java.awt.*;

/**
 * The type Main menu model.
 */
public class MainMenuModel extends AMenuModel {

    private AMenu landingPage;

    /*public MainMenuModel() {

    }*/

    /**
     * Init.
     */
    public void init() {
        //landingPage = new MainMenuPage(this);
        landingPage = new StartScreenPage(this);

        initPage(landingPage);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.RED);
        g.drawString("Main Menu!", 20, 30);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
