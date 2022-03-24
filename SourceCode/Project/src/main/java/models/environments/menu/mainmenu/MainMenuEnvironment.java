package models.environments.menu.mainmenu;

import models.environments.menu.mainmenu.startscreen.StartScreenPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;

import java.awt.*;

/**
 * The type Main menu model.
 */
public class MainMenuEnvironment extends AMenuModel {

    private AMenu landingPage;

    /**
     * Init.
     */
    public void init() {
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

    @Override
    public void reset() {
        popToFirst();
    }
}
