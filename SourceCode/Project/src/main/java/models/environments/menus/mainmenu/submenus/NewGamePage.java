package models.environments.menus.mainmenu.submenus;

import models.environments.EnvironmentType;
import models.environments.EnvironmentsHandler;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.AImageView;
import models.prototypes.components.menuviews.types.ATextView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * <p>The New Game Page. Contains the character selection and the option to begin a fresh play-through.</p>
 * @author Andrew Stephens
 */
public class NewGamePage extends AMenu {

    /**
     * <p>Builds the New Game page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public NewGamePage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        HashMap<ACharacter.CharacterType, BufferedImage> avatars = new HashMap<>();
        avatars.put(ACharacter.CharacterType.TEO, getResources().getImage("avatar1"));
        avatars.put(ACharacter.CharacterType.MELYNN, getResources().getImage("avatar2"));

        float mx = Config.DEFAULT_WINDOW_WIDTH * .5f;
        float my = Config.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        ATextView text_title = new ATextView(
                getParentEnvironment(),
                (int)mx - btn_width, 100,
                (btn_width * 2), (int)(btn_height * .75f),
                "CREATE NEW GAME",
                new Color(255, 255, 255, 150)
        ) {};

        AImageView img_character = new AImageView(
                getParentEnvironment(),
                (int) (mx - (200 * .5f)),300,
                200,200,
                avatars.get(getEnvironmentsHandler().getGameEnvironment().getPlayerAvatar()
                        .getCharacterType()),
                AMenuComponent.ImageScale.FIT_CENTERED
        ){
            @Override
            public void update(float delta) {
                backgroundImage = avatars.get(getEnvironmentsHandler().getGameEnvironment().getPlayerAvatar()
                        .getCharacterType());

                super.update(delta);
            }
        };

        //Level 1 Button
        AButtonView button_avatar1 = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (200 * .5f)) - 250,
                300,
                200,
                200,
                "TEO",
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false),
                AButtonView.ImageScale.FILL_XY
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentsHandler().getGameEnvironment().getPlayerAvatar()
                        .setCharacterType(ACharacter.CharacterType.TEO);

                return true;
            }
        };

        //Level 3 Button
        AButtonView button_avatar2 = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (200 * .5f) + 250),
                300,
                200,
                200,
                "MELYNN",
                getResources().getSpriteSheet("spritesheet_buttonsq").setLoopOnLast(false),
                AButtonView.ImageScale.FILL_XY
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentsHandler().getGameEnvironment().getPlayerAvatar()
                        .setCharacterType(ACharacter.CharacterType.MELYNN);

                return true;
            }
        };


        AButtonView button_characterConfirm = new AButtonView(
                getParentEnvironment(),
                (int)(mx - (btn_width * .5f)),
                550,
                btn_width,
                btn_height,
                "Start New Game",
                AButtonView.ImageScale.FIT_CENTERED
        )
        {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentsHandler().getGameEnvironment().reset();
                getEnvironmentsHandler().getGameEnvironment().getLevelsList().setCurrentLevel(0);
                Thread t = new Thread(() -> {
                    getEnvironmentsHandler().getSaveData().createNewGame();
                    getEnvironmentsHandler().swapToEnvironment(
                            EnvironmentType.GAME, true).applyEnvironment();
                });
                t.start();

                return true;
            }
        };

        int backbtn_w = (int)(btn_width * .5f);
        AButtonView button_back = new AButtonView(
                getParentEnvironment(),
                (int) (mx - (backbtn_w * .5f)),
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
        addComponent(button_avatar1);
        addComponent(button_avatar2);
        addComponent(img_character);
        addComponent(button_characterConfirm);
        addComponent(button_characterConfirm);
        addComponent(button_back);
    }

}
