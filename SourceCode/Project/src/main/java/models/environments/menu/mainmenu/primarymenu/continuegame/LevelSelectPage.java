package models.environments.menu.mainmenu.primarymenu.continuegame;

import models.environments.EnvironmentsHandler;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuComponent;
import prototypes.window.environments.menu.components.types.AMenuButton;
import prototypes.window.environments.menu.components.types.AMenuImage;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Level select page.
 */
public class LevelSelectPage extends AMenu {

    /**
     * Instantiates a new Level select page.
     *
     * @param parentModel the parent model
     */
    public LevelSelectPage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        BufferedImage img_title = Resources.getImage("testbutton");
        BufferedImage img_button = Resources.getImage("testbutton2");

        AMenuImage imageView_label = new AMenuImage(
                parentModel,
                (int) (mx - (1000 * .5f)),
                100,
                1000,
                200,
                img_title,
                AMenuComponent.ImageScale.FILL_XY
        );

        AMenuButton button_level1 = new AMenuButton(
                parentModel,
                (int) (mx - (200 * .5f)) - 150,
                300,
                200,
                200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //parentMenuModel.pop();
                parentMenuModel.parentEnvironmentsModel.getGameModel().setCurrentLevel(0);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironment(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

                return true;
            }
        };
        button_level1.setText("Level 1");
        button_level1.setBackgroundImage(img_button);
        button_level1.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        AMenuButton button_level2 = new AMenuButton(
                parentModel,
                (int) (mx - (200 * .5f)) + 150, 300,
                200, 200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //parentMenuModel.pop();
                parentMenuModel.parentEnvironmentsModel.getGameModel().setCurrentLevel(2);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironment(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

                return true;
            }
        };
        button_level2.setText("Level 2");
        button_level2.setBackgroundImage(img_button);
        button_level2.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        AMenuButton button_back = new AMenuButton(
                parentModel,
                (int) (mx - (200 * .5f)), 600,
                200, 50) {
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
        button_back.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        components.add(imageView_label);
        components.add(button_level1);
        components.add(button_level2);
        components.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Level Select Model!", 50, 60);
    }
}
