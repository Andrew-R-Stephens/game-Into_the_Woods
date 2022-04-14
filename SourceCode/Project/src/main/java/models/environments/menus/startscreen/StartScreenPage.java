package models.environments.menus.startscreen;

import models.environments.menus.mainmenu.MainMenuPage;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;
import models.utils.resources.Resources;

public class StartScreenPage extends AMenu {

    public StartScreenPage(AMenuEnvironment parentMenuModel) {

        super(parentMenuModel);

        image_background = Resources.getImage("menubackground");

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AButtonView startButton = new AButtonView(
                parentMenuModel,
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

        components.add(startButton);

    }

    public MainMenuPage navigateToMainMenuPage() {
        parentMenuModel.push(new MainMenuPage(parentMenuModel));
        return (MainMenuPage) (parentMenuModel.peek());
    }
}
