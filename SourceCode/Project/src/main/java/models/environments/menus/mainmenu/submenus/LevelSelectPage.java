package models.environments.menus.mainmenu.submenus;

import models.environments.EnvironmentType;
import models.environments.levelEnvironment.game.GameEnvironment;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.ATextView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;

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

        ATextView text_title = new ATextView(
                getParentEnvironment(),
                (int)centerW - btn_width, 100,
                (btn_width * 2), (int)(btn_height * .75f),
                "LEVEL SELECT",
                new Color(255, 255, 255, 150)
        ) {};

        AButtonView[] levels = new AButtonView[3];

        //Level 1 Button
        levels[0] = new AButtonView(
                parentEnvironment,
                (int) (centerW - (200 * .5f)) - 250,
                300,
                200,
                200,
                "Level 1",
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false),
                AButtonView.ImageScale.FIT_CENTERED
    ) {
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


        //Level 2 Button
        levels[1] = new AButtonView(
                parentEnvironment,
                (int) (centerW - (200 * .5f)), 300,
                200, 200,
                "Level 2",
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false),
                AButtonView.ImageScale.FIT_CENTERED
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

        //Level 3 Button
        levels[2] = new AButtonView(
                parentEnvironment,
                (int) (centerW - (200 * .5f) + 250),
                300,
                200,
                200,
                "Level 3",
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false),
                AButtonView.ImageScale.FIT_CENTERED
        ) {
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


        int backbtn_w = (int)(btn_width * .5f);
        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
                (int) (centerW - (backbtn_w * .5f)),
                800,
                backbtn_w,
                btn_height,
                "Back",
                AButtonView.ImageScale.FIT_CENTERED
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


        addComponent(text_title);
        // Set Level Selection Visibility
        for(int i = 0; i < levels.length; i++) {
            levels[i].setEnabled(false);
            if((i-1) <= getEnvironmentsHandler().getSaveData().getLevelProgress()) {
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

        GameEnvironment ge = getEnvironmentsHandler().getGameEnvironment();

        ge.reset();
        ge.getLevelsList().setEnvironment(ge);
        ge.setCurrentLevel(levelIndex);
        getEnvironmentsHandler().swapToEnvironment(
                EnvironmentType.GAME, true).applyEnvironment();
    }

}
