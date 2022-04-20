package models.environments.menus.mainmenu.submenus;

import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;

/**
 * <p></p>
 */
public class QuitPage extends AMenu {

    public QuitPage(AMenuEnvironment parentModel) {
        super(parentModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
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

                getParentEnvironment().pop();

                return true;
            }
        };
        button_back.setText("Exit to Main Menu");
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        addComponent(button_back);
    }

}
