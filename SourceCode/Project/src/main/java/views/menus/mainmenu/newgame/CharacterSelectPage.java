package views.menus.mainmenu.newgame;

import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuModel;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import java.awt.image.BufferedImage;

public class CharacterSelectPage extends AMenu {

    private final int chosenCharacter = 0;

    public CharacterSelectPage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_rectbutton = Resources.getImage("button_hrect");
        BufferedImage img_sqbutton = Resources.getImage("button_square");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

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

                parentMenuModel.parentEnvironmentsModel.
                        getGameEnvironment().getPlayerAvatar().setCharacterType(ACharacter.CharacterType.MALE);

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

                parentMenuModel.parentEnvironmentsModel.
                        getGameEnvironment().getPlayerAvatar().setCharacterType(ACharacter.CharacterType.FEMALE);

                return true;
            }
        };
        button_avatar2.setText("MELYNN");
        button_avatar2.setBackgroundImage(img_sqbutton);
        button_avatar2.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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

        components.add(button_avatar1);
        components.add(button_avatar2);
        components.add(button_back);
    }



}
