package models.environments.menu.mainmenu;

import models.controls.MenuControlsModel;
import models.environments.EnvironmentsHandler;
import models.environments.menu.mainmenu.startscreen.StartScreenPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import utils.files.AudioPlayer;
import utils.files.Resources;

import java.awt.*;

/**
 * The type Main menu model.
 */
public class MainMenuEnvironment extends AMenuModel {

    /**
     * Init.
     */
    public void init(EnvironmentsHandler environmentsHandler, MenuControlsModel menuControlsModel) {
        super.init(environmentsHandler, menuControlsModel);

        AMenu landingPage = new StartScreenPage(this);

        initPage(landingPage);
    }

    public AMenu getTopPage() {
        return peek();
    }

    @Override
    public void startBackgroundAudio() {
        audioPlayer = Resources.playAudio("mainmenu");
    }

    @Override
    public void reset() {
        popToFirst();
    }
}
