package models.environments.menus.mainmenu.options;

import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.prototypes.window.environments.menu.components.types.AMenuSlider;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

public class OptionsPage extends AMenu {

    public OptionsPage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        //BufferedImage img_button = Resources.getImage("button_hrect");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuSlider slider = new AMenuSlider(
                parentMenuModel,
                (int) (mx - (800 * .5f)),
                400,
                800,
                btn_height);

        AMenuButton button_back = new AMenuButton(
                parentMenuModel,
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
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(slider);
        components.add(button_back);
    }

}
