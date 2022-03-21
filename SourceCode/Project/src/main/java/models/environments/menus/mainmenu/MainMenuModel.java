package models.environments.menus.mainmenu;

import models.environments.menus.mainmenu.children.*;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;

import java.awt.*;

/**
 * The type Main menu model.
 */
public class MainMenuModel extends AMenuModel {

    private final AMenu landingPage;

    /**
     * Instantiates a new Main menu model.
     */
    public MainMenuModel() {

        //landingPage = new MainMenuPage(this);
        landingPage = new StartScreenPage(this);

        initPage(landingPage);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.RED);
        g.drawString("Main Menu!", 20, 20);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
