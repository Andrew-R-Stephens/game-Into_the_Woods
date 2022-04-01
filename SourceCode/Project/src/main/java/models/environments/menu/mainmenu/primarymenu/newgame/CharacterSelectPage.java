package models.environments.menu.mainmenu.primarymenu.newgame;

import prototypes.actor.pawn.character.ACharacter;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.image.BufferedImage;

/**
 * The type Character create page.
 */
public class CharacterSelectPage extends AMenu {

    private final int chosenCharacter = 0;

    /**
     * Instantiates a new Character create page.
     *
     * @param parentModel the parent model
     */
    public CharacterSelectPage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_rectbutton = Resources.getImage("testbutton2");
        BufferedImage img_sqbutton = Resources.getImage("squareButton");

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
