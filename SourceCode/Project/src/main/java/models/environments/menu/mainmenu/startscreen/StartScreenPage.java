package models.environments.menu.mainmenu.startscreen;

import models.environments.menu.mainmenu.primarymenu.MainMenuPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Start screen page.
 */
public class StartScreenPage extends AMenu {

    /**
     * Instantiates a new A menu.
     *
     * @param parentMenuModel the parent menu model
     */
    public StartScreenPage(AMenuModel parentMenuModel) {

        super(parentMenuModel);

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_button = Resources.getImage("testbutton2");

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

                parentMenuModel.push(new MainMenuPage(parentMenuModel));
                return true;

            }
        };

        startButton.setText("Start button");
        startButton.setBackgroundImage(img_button);
        startButton.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(startButton);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.green);
        g.drawString("Start screen!", 50, 50);
    }

}
