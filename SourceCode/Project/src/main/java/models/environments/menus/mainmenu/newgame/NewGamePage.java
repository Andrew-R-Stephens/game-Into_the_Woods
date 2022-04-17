package models.environments.menus.mainmenu.newgame;

import models.environments.EnvironmentsHandler;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;
import models.utils.config.Config;
import models.utils.resources.Resources;

import java.awt.image.BufferedImage;

public class NewGamePage extends AMenu {

    public NewGamePage(AMenuEnvironment parentModel) {
        super(parentModel);

        BufferedImage img_sqbutton = getResources().getImage("button_square");

        float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
        float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        /*
        AMenuButton button_characterCreate = new AMenuButton(
                parentModel,
                (int)(mx - (btn_width * .5f)),
                125,
                btn_width,
                btn_height) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.push(bundle.getPage(0));

                return true;
            }
        };
        button_characterCreate.setText("Create Character");
        //button_characterCreate.setBackgroundImage(img_button);
        button_characterCreate.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);
        */

        //Level 1 Button
        AButtonView button_avatar1 = new AButtonView(
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

                parentMenuEnvironment.getParentEnvironmentsHandler().
                        getGameEnvironment().getPlayerAvatar().setCharacterType(ACharacter.CharacterType.TEO);

                return true;
            }
        };
        button_avatar1.setText("TEO");
        button_avatar1.setBackgroundImage(img_sqbutton);
        button_avatar1.setImageScaling(AButtonView.ImageScale.FILL_XY);

        //Level 3 Button
        AButtonView button_avatar2 = new AButtonView(
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

                parentMenuEnvironment.getParentEnvironmentsHandler().
                        getGameEnvironment().getPlayerAvatar().setCharacterType(ACharacter.CharacterType.MELYNN);

                return true;
            }
        };
        button_avatar2.setText("MELYNN");
        button_avatar2.setBackgroundImage(img_sqbutton);
        button_avatar2.setImageScaling(AButtonView.ImageScale.FILL_XY);


        AButtonView button_characterConfirm = new AButtonView(
                parentModel,
                (int)(mx - (btn_width * .5f)),
                550,
                btn_width,
                btn_height) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().reset();
                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().getLevelsList().setCurrentLevel(0);

                parentMenuEnvironment.getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_characterConfirm.setText("Start New Game");
        button_characterConfirm.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

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
        //button_back.setBackgroundImage(img_button);
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        components.add(button_avatar1);
        components.add(button_avatar2);
        components.add(button_characterConfirm);
        //components.add(button_characterCreate);
        components.add(button_characterConfirm);
        components.add(button_back);
    }

}
