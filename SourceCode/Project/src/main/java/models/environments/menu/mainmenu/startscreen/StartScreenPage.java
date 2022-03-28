package models.environments.menu.mainmenu.startscreen;

import models.environments.menu.mainmenu.primarymenu.MainMenuPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.PreferenceData;
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

        BufferedImage img_button = Resources.getImage("testbutton2");

        float mx = PreferenceData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = PreferenceData.DEFAULT_WINDOW_HEIGHT * .5f;

        AMenuButton startButton = new AMenuButton(
                parentMenuModel,
                (int)(mx - (250 * .5f)),
                (int)(my - (50 * .5f)),
                250,
                50) {
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
        startButton.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        components.add(startButton);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.green);
        g.drawString("Start screen!", 50, 50);
    }

}
