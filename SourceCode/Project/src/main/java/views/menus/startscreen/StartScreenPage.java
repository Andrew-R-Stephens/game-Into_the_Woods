package views.menus.startscreen;

import views.menus.mainmenu.MainMenuPage;
import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuModel;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.awt.image.BufferedImage;

public class StartScreenPage extends AMenu {

    public StartScreenPage(AMenuModel parentMenuModel) {

        super(parentMenuModel);

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_button = Resources.getImage("button_hrect");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuButton startButton = new AMenuButton(
                parentMenuModel,
                (int)(mx - (btn_width * .5f)),
                (int)(my - (btn_height * .5f)),
                btn_width,
                btn_height) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }

                navigateToDefaultPage();
                return true;

            }
        };

        startButton.setText("Start button");
        startButton.setBackgroundImage(img_button);
        startButton.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(startButton);

    }

    public void navigateToDefaultPage() {
        parentMenuModel.push(new MainMenuPage(parentMenuModel));
    }
}
