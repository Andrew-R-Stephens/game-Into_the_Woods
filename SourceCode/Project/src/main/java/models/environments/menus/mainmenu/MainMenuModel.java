package models.environments.menus.mainmenu;

import models.environments.menus.mainmenu.children.*;
import props.prototypes.window.environments.menu.AMenu;
import props.prototypes.window.environments.menu.AMenuModel;

import java.awt.*;

public class MainMenuModel extends AMenuModel {

    private final AMenu landingPage;

    public MainMenuModel() {

        landingPage = new MainMenuPage(this);

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
