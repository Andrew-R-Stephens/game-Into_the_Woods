package models.environments.menus.mainmenu.help;

import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

public class HelpPage extends AMenu {

    public HelpPage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuButton button_back = new AMenuButton(
                parentModel,
                (int) (mx - (btn_width * .5f)),
                800,
                btn_width,
                btn_height
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuEnvironment.pop();

                return true;
            }
        };
        button_back.setText("Back");
        //button_back.setBackgroundImage(img_button);
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(button_back);
    }

}