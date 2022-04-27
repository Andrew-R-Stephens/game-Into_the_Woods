package models.environments.menus.mainmenu.submenus;

import models.environments.EnvironmentsHandler;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

/**
 * <p>Level Select Page. Allows the user to choose a previously completed level, or a level that they need to
 * complete next.</p>
 * @author Andrew Stephens
 */
public class LevelSelectPage extends AMenu {

    /**
     * <p>Builds the Level Select page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public LevelSelectPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AButtonView[] levels = new AButtonView[3];

        //Level 1 Button
        levels[0] = new AButtonView(
                parentEnvironment,
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

            @Override
            public void registerInput() {
                if(!isEnabled) {
                    return;
                }
                super.registerInput();
            }
        };
        levels[0].setText("Level 1");
        levels[0].setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        levels[0].setImageScaling(AButtonView.ImageScale.FIT_CENTERED);


        //Level 2 Button
        levels[1] = new AButtonView(
                parentEnvironment,
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

            @Override
            public void registerInput() {
                if(!isEnabled) {
                    return;
                }
                super.registerInput();
            }
        };
        levels[1].setText("Level 2");
        levels[1].setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        levels[1].setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        //Level 3 Button
        levels[2] = new AButtonView(
                parentEnvironment,
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

            @Override
            public void registerInput() {
                if(!isEnabled) {
                    return;
                }
                super.registerInput();
            }
        };
        levels[2].setText("Level 3");
        levels[2].setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        levels[2].setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

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

        // Set Level Selection Visibility
        for(int i = 0; i < levels.length; i++) {
            levels[i].setEnabled(false);
            if((i-1) <= getEnvironmentsHandler().getSaveData().getLevelProgress()) {
                System.out.println(i + " vs " + getEnvironmentsHandler().getSaveData().getLevelProgress());
                levels[i].setEnabled(true);
            }
            addComponent(levels[i]);
        }

        addComponent(button_back);
    }

    /**
     * Navigates to the specified level and swaps the Environment.
     * @param levelIndex The specified level.
     */
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
