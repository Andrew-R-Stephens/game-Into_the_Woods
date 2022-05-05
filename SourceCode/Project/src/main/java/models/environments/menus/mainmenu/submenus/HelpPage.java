package models.environments.menus.mainmenu.submenus;

import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.ATextView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;

/**
 * <p>The Help Page, containing text content to assist the player.</p>
 * @author Andrew Stephens
 */
public class HelpPage extends AMenu {

    /**
     * <p>Builds the Help page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public HelpPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
        float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        ATextView text_title = new ATextView(
                getParentEnvironment(),
                (int)mx - btn_width, 100,
                (btn_width * 2), (int)(btn_height * .75f),
                "HELP"
                ) {};
        text_title.setBackgroundColor(new Color(255, 255, 255, 150));

        AButtonView button_objectives = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (btn_width * .5f)),
                300,
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
        button_objectives.setText("Objectives");
        button_objectives.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        AButtonView button_collectibles = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (btn_width * .5f)),
                button_objectives.getY() + btn_height + 25,
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
        button_collectibles.setText("Collectibles");
        button_collectibles.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        AButtonView button_obstacles = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (btn_width * .5f)),
                button_collectibles.getY() + btn_height + 25,
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
        button_obstacles.setText("Obstacles");
        button_obstacles.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        int backbtn_w = (int)(btn_width * .5f);
        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (backbtn_w * .5f)),
                800,
                backbtn_w,
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


        addComponent(text_title);
        addComponent(button_objectives);
        addComponent(button_collectibles);
        addComponent(button_obstacles);
        addComponent(button_back);
    }

}
