package models.environments.menus.mainmenu.children;

import models.data.PreferenceData;
import models.environments.EnvironmentsModel;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

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

        float mx = PreferenceData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = PreferenceData.DEFAULT_WINDOW_HEIGHT * .5f;

        AMenuButton button_level1 = new AMenuButton(
                parentModel,
                (int) (mx - (200 * .5f)) - 150, 300,
                200, 200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //parentMenuModel.pop();
                parentMenuModel.parentEnvironmentsModel.getGameModel().setCurrentLevel(0);
                parentMenuModel.parentEnvironmentsModel.setCurrentEnvironment(EnvironmentsModel.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

                return true;
            }
        };
        button_level1.setText("Level 1");

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
                parentMenuModel.parentEnvironmentsModel.setCurrentEnvironment(EnvironmentsModel.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

                return true;
            }
        };
        button_level2.setText("Level 2");

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

        buttons.add(button_level1);
        buttons.add(button_level2);

        buttons.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Level Select Model!", 50, 60);
    }
}
