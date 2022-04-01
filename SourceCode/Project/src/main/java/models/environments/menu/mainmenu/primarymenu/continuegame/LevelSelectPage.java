package models.environments.menu.mainmenu.primarymenu.continuegame;

import models.environments.EnvironmentsHandler;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
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

        BufferedImage img_rectbutton = Resources.getImage("testbutton2");
        BufferedImage img_sqbutton = Resources.getImage("squareButton");

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
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().setCurrentLevel(0);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironment(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

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
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().setCurrentLevel(2);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironment(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

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
                parentMenuModel.parentEnvironmentsModel.getGameEnvironment().setCurrentLevel(0);
                parentMenuModel.parentEnvironmentsModel.swapToEnvironment(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

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

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Level Select Model!", 50, 60);
    }
}
