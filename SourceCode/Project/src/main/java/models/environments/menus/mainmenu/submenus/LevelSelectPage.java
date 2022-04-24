package models.environments.menus.mainmenu.submenus;

import models.environments.EnvironmentsHandler;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

/**
 * <p></p>
 */
public class LevelSelectPage extends AMenu {

    /**
     * <p></p>
     * @param parentModel
     */
    public LevelSelectPage(AMenuEnvironment parentModel) {
        super(parentModel);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        //Level 1 Button
        AButtonView button_level1 = new AButtonView(
                parentModel,
                (int) (centerW - (200 * .5f)) - 250,
                300,
                200,
                200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                navigateToLevel(0);

                reset();

                return true;
            }
        };
        button_level1.setText("Level 1");
        button_level1.setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        button_level1.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        //Level 2 Button
        AButtonView button_level2 = new AButtonView(
                parentModel,
                (int) (centerW - (200 * .5f)), 300,
                200, 200
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                navigateToLevel(1);

                reset();

                return true;
            }
        };
        button_level2.setText("Level 2");
        button_level2.setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        button_level2.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        //Level 3 Button
        AButtonView button_level3 = new AButtonView(
                parentModel,
                (int) (centerW - (200 * .5f) + 250),
                300,
                200,
                200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                navigateToLevel(2);

                reset();

                return true;
            }
        };
        button_level3.setText("Level 3");
        button_level3.setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        button_level3.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

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
        button_back.setText("Back");
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);


        addComponent(button_level1);
        addComponent(button_level2);
        addComponent(button_level3);
        addComponent(button_back);
    }

    public void navigateToLevel(int levelIndex) {
        getMouseController().setPos(
                (int)(Config.window_width_actual * .5f),
                (int)(Config.window_height_actual * .5f));
        getEnvironmentsHandler().getGameEnvironment().reset();
        getEnvironmentsHandler().getGameEnvironment().setCurrentLevel(levelIndex);
        getEnvironmentsHandler().swapToEnvironment(
                EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();
    }

}
