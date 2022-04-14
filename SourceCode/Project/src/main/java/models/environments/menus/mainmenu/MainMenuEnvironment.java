package models.environments.menus.mainmenu;

import controls.MenuControls;
import models.environments.EnvironmentsHandler;
import models.environments.menus.startscreen.StartScreenPage;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.resources.Resources;

public class MainMenuEnvironment extends AMenuEnvironment {

    public void init(EnvironmentsHandler environmentsHandler, MenuControls menuControlsModel) {
        super.init(environmentsHandler, menuControlsModel);

        AMenu landingPage = new StartScreenPage(this);

        initPage(landingPage);
    }

    public AMenu getTopPage() {
        return peek();
    }

    public void popToFirst() {
        while(getStackDepth() > 1) {
            pop();
        }
    }

    @Override
    public void onExit() {
        super.onExit();
        popToFirst();
    }

    public void navigateToLevelSelectPage() {
        ((StartScreenPage)peek()).navigateToMainMenuPage().navigateToLevelSelectPage();
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
