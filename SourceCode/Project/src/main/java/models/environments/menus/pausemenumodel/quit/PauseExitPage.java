package models.environments.menus.pausemenumodel.quit;

import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;

public class PauseExitPage extends AMenu {

    public PauseExitPage(AMenuEnvironment parentModel) {
        super(parentModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AButtonView button_back = new AButtonView(
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
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        components.add(button_back);
    }

}
