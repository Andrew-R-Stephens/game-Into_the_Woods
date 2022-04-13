package models.environments.menus.pausemenumodel.quit;

import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;

public class PauseExitPage extends AMenu {

    public PauseExitPage(AMenuEnvironment parentModel) {
        super(parentModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuButton button_back = new AMenuButton(
                parentModel,
                (int) (centerW - (btn_width * .5f)),
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
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(button_back);
    }

}