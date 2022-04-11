package models.environments.menus.mainmenu.newgame;

import models.environments.EnvironmentsHandler;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.awt.image.BufferedImage;

public class NewGamePage extends AMenu {

    public NewGamePage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        BufferedImage img_sqbutton = Resources.getImage("button_square");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

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
        AMenuButton button_avatar1 = new AMenuButton(
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

                parentMenuEnvironment.parentEnvironmentsHandler.
                        getGameEnvironment().getPlayerAvatar().setCharacterType(ACharacter.CharacterType.TEO);

                return true;
            }
        };
        button_avatar1.setText("TEO");
        button_avatar1.setBackgroundImage(img_sqbutton);
        button_avatar1.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        //Level 3 Button
        AMenuButton button_avatar2 = new AMenuButton(
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

                parentMenuEnvironment.parentEnvironmentsHandler.
                        getGameEnvironment().getPlayerAvatar().setCharacterType(ACharacter.CharacterType.MELYNN);

                return true;
            }
        };
        button_avatar2.setText("MELYNN");
        button_avatar2.setBackgroundImage(img_sqbutton);
        button_avatar2.setImageScaling(AMenuButton.ImageScale.FILL_XY);


        AMenuButton button_characterConfirm = new AMenuButton(
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

                parentMenuEnvironment.parentEnvironmentsHandler.getGameEnvironment().reset();
                parentMenuEnvironment.parentEnvironmentsHandler.getGameEnvironment().getLevelsList().setCurrentLevel(0);

                parentMenuEnvironment.parentEnvironmentsHandler.swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_characterConfirm.setText("Start New Game");
        button_characterConfirm.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

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

                parentMenuEnvironment.pop();

                return true;
            }
        };
        button_back.setText("Back");
        //button_back.setBackgroundImage(img_button);
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(button_avatar1);
        components.add(button_avatar2);
        components.add(button_characterConfirm);
        //components.add(button_characterCreate);
        components.add(button_characterConfirm);
        components.add(button_back);
    }

}
