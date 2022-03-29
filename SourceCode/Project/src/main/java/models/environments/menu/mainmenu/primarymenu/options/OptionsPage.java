package models.environments.menu.mainmenu.primarymenu.options;

import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
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

        BufferedImage img_button = Resources.getImage("testbutton2");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuButton button_back = new AMenuButton(parentModel,500, 500, btn_width,
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

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Options Menu Model!", 50, 60);
    }
}
