package models.environments.menus.startscreen;

import models.environments.menus.mainmenu.submenus.MainMenuPage;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;

public class StartScreenPage extends AMenu {

    public StartScreenPage(AMenuEnvironment parentMenuModel) {

        super(parentMenuModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AButtonView startButton = new AButtonView(
                getParentEnvironment(),
                (int)(centerW - (btn_width * .5f)),
                (int)(centerH - (btn_height * .5f)),
                btn_width,
                btn_height) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }

                navigateToMainMenuPage();
                return true;

            }
        };

        startButton.setText("Start button");
        startButton.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        addComponent(startButton);

    }

    public MainMenuPage navigateToMainMenuPage() {
        getParentEnvironment().push(new MainMenuPage(getParentEnvironment()));
        return (MainMenuPage) (getParentEnvironment().peek());
    }
}
