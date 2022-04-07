package views.menus.mainmenu.options;

import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuModel;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.utils.files.Resources;

import java.awt.image.BufferedImage;

/**
 * The type Options page.
 */
public class OptionsPage extends AMenu {

    /**
     * Instantiates a new Options page.
     *
     * @param parentModel the parent model
     */
    public OptionsPage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_button = Resources.getImage("button_hrect");

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

                parentMenuModel.pop();

                return true;
            }
        };
        button_back.setText("Back");
        button_back.setBackgroundImage(img_button);
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(button_back);
    }

}
