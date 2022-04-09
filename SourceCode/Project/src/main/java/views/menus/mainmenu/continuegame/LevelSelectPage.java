package views.menus.mainmenu.continuegame;

import models.environments.EnvironmentsHandler;
import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuModel;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.awt.image.BufferedImage;

public class LevelSelectPage extends AMenu {

    public LevelSelectPage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_rectbutton = Resources.getImage("button_hrect");
        BufferedImage img_sqbutton = Resources.getImage("button_square");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        //Level 1 Button
        AMenuButton button_level1 = new AMenuButton(
                parentModel,
                (int) (mx - (200 * .5f)) - 250,
                300,
                200,
                200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //parentMenuModel.pop();
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().reset();
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().setCurrentLevel(0);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironmentType(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_level1.setText("Level 1");
        button_level1.setBackgroundImage(img_sqbutton);
        button_level1.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        //Level 2 Button
        AMenuButton button_level2 = new AMenuButton(
                parentModel,
                (int) (mx - (200 * .5f)), 300,
                200, 200
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //parentMenuModel.pop();
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().reset();
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().setCurrentLevel(2);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironmentType(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_level2.setText("Level 2");
        button_level2.setBackgroundImage(img_sqbutton);
        button_level2.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        //Level 3 Button
        AMenuButton button_level3 = new AMenuButton(
                parentModel,
                (int) (mx - (200 * .5f) + 250),
                300,
                200,
                200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //parentMenuModel.pop();
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().reset();
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().setCurrentLevel(0);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironmentType(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_level3.setText("Level 3");
        button_level3.setBackgroundImage(img_sqbutton);
        button_level3.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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
        button_back.setBackgroundImage(img_rectbutton);
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);


        //components.add(imageView_label);
        components.add(button_level1);
        components.add(button_level2);
        components.add(button_level3);
        components.add(button_back);
    }

}