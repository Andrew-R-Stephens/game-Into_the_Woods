package models.environments.menus.mainmenu;

import controls.MenuControls;
import models.environments.EnvironmentsHandler;
import views.menus.startscreen.StartScreenPage;
import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuModel;
import models.utils.files.Resources;

/**
 * The type Main menu model.
 */
public class MainMenuEnvironment extends AMenuModel {

    /**
     * Init.
     */
    public void init(EnvironmentsHandler environmentsHandler, MenuControls menuControlsModel) {
        super.init(environmentsHandler, menuControlsModel);

        AMenu landingPage = new StartScreenPage(this);

        initPage(landingPage);
    }

    public AMenu getTopPage() {
        return peek();
    }

    @Override
    public void startBackgroundAudio() {
        audioPlayer = Resources.playAudio_Player("mainmenu");
    }

    @Override
    public void reset() {
        popToFirst();
    }
}
