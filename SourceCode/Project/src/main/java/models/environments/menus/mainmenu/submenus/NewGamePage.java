package models.environments.menus.mainmenu.submenus;

import models.environments.EnvironmentsHandler;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.components.menuviews.types.AButtonView;
import models.utils.config.Config;

import java.awt.image.BufferedImage;

/**
 * <p></p>
 */
public class NewGamePage extends AMenu {

    /**
     * <p></p>
     * @param parentModel -
     */
    public NewGamePage(AMenuEnvironment parentModel) {
        super(parentModel);

        BufferedImage img_sqbutton = getResources().getImage("button_square");

        float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
        float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        //Level 1 Button
        AButtonView button_avatar1 = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (200 * .5f)) - 250,
                300,
                200,
                200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentHandler().getGameEnvironment().getPlayerAvatar()
                        .setCharacterType(ACharacter.CharacterType.TEO);

                return true;
            }
        };
        button_avatar1.setText("TEO");
        button_avatar1.setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        button_avatar1.setImageScaling(AButtonView.ImageScale.FILL_XY);

        //Level 3 Button
        AButtonView button_avatar2 = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (200 * .5f) + 250),
                300,
                200,
                200) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentHandler().getGameEnvironment().getPlayerAvatar()
                        .setCharacterType(ACharacter.CharacterType.MELYNN);

                return true;
            }
        };
        button_avatar2.setText("MELYNN");
        button_avatar2.setSpritesheet(
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false));
        button_avatar2.setImageScaling(AButtonView.ImageScale.FILL_XY);


        AButtonView button_characterConfirm = new AButtonView(
                getParentEnvironment(),
                (int)(mx - (btn_width * .5f)),
                550,
                btn_width,
                btn_height) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentHandler().getGameEnvironment().reset();
                getEnvironmentHandler().getGameEnvironment().getLevelsList().setCurrentLevel(0);

                getEnvironmentHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_characterConfirm.setText("Start New Game");
        button_characterConfirm.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
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

                getParentEnvironment().pop();

                return true;
            }
        };
        button_back.setText("Back");
        //button_back.setBackgroundImage(img_button);
        button_back.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        addComponent(button_avatar1);
        addComponent(button_avatar2);
        addComponent(button_characterConfirm);
        addComponent(button_characterConfirm);
        addComponent(button_back);
    }

}
