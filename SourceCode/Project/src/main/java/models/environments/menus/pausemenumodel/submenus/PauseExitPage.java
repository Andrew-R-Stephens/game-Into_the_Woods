package models.environments.menus.pausemenumodel.submenus;

import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;

/**
 * <p>The Pause Exit Page. A Confirmation to ensure the player's intention.</p>
 * @author Andrew Stephens
 */
public class PauseExitPage extends AMenu {

    /**
     * <p>Builds the Exit page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public PauseExitPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AButtonView button_back = new AButtonView(
                parentEnvironment,
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
        button_back.setText("Back");
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        addComponent(button_back);
    }

}
