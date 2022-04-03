package models.environments.menu.mainmenu;

import models.environments.menu.mainmenu.startscreen.StartScreenPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;

import java.awt.*;

/**
 * The type Main menu model.
 */
public class MainMenuEnvironment extends AMenuModel {

    //private AMenu landingPage;

    /**
     * Init.
     */
    public void init() {
        AMenu landingPage = new StartScreenPage(this);

        initPage(landingPage);
    }

    @Override
    public void reset() {
        popToFirst();
    }
}
