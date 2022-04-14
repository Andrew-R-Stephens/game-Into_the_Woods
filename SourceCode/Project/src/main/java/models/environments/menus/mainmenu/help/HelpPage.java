package models.environments.menus.mainmenu.help;

import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

public class HelpPage extends AMenu {

    public HelpPage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AButtonView button_back = new AButtonView(
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
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        components.add(button_back);
    }

}
