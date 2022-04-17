package models.environments.menus.mainmenu.continuegame;

import models.environments.EnvironmentsHandler;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;
import models.utils.config.Config;
import models.utils.resources.Resources;

public class LevelSelectPage extends AMenu {

    public LevelSelectPage(AMenuEnvironment parentModel) {
        super(parentModel);

        float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
        float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        //Level 1 Button
        AButtonView button_level1 = new AButtonView(
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
                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().reset();
                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().setCurrentLevel(0);
                parentMenuEnvironment.getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_level1.setText("Level 1");
        button_level1.setSpritesheet(
                Resources.getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        //button_level1.setBackgroundImage(img_sqbutton);
        button_level1.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        //Level 2 Button
        AButtonView button_level2 = new AButtonView(
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
                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().reset();
                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().setCurrentLevel(1);
                parentMenuEnvironment.getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_level2.setText("Level 2");
        button_level2.setSpritesheet(
                Resources.getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        button_level2.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        //Level 3 Button
        AButtonView button_level3 = new AButtonView(
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
                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().reset();
                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().setCurrentLevel(2);
                parentMenuEnvironment.getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_level3.setText("Level 3");
        button_level3.setSpritesheet(
                Resources.getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        //button_level3.setBackgroundImage(img_sqbutton);
        button_level3.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        AButtonView button_back = new AButtonView(
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

                parentMenuEnvironment.pop();

                return true;
            }
        };
        button_back.setText("Back");
        //button_back.setBackgroundImage(img_rectbutton);
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);


        //components.add(imageView_label);
        components.add(button_level1);
        components.add(button_level2);
        components.add(button_level3);
        components.add(button_back);
    }

}
