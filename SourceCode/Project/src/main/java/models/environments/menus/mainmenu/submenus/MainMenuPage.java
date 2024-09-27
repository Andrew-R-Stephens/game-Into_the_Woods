package models.environments.menus.mainmenu.submenus;

import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.components.menuviews.types.AImageView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;

/**
 * <p>The Main Menu Page. Contains menu button components to allow menu navigation.</p>
 * @author Andrew Stephens
 */
public class MainMenuPage extends AMenu {

    /**
     * <p>Builds the Main Menu page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public MainMenuPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        AButtonView button_newGame = new AButtonView(
                getParentEnvironment(),
                (int)(centerW - (buttonW * .5f)),
                175,
                buttonW,
                buttonH,
                "New Game",
                AButtonView.ImageScale.FIT_CENTERED
        )
        {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }
                getParentEnvironment().push(new NewGamePage(getParentEnvironment()));

                return true;
            }
        };

        AButtonView button_continueGame = new AButtonView(
                getParentEnvironment(),
                (int)(centerW - (buttonW * .5f)),
                290,
                buttonW,
                buttonH,
                "Continue Game",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }
                navigateToLevelSelectPage();

                return true;
            }
        };


        AButtonView button_options = new AButtonView(
                getParentEnvironment(),
                (int)(centerW - (buttonW * .5f)),
                405,
                buttonW,
                buttonH,
                "Options",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }
                getParentEnvironment().push(new OptionsPage(getParentEnvironment()));

                return true;
            }
        };

        AButtonView button_help = new AButtonView(
                getParentEnvironment(),
                (int)(centerW - (buttonW * .5f)),
                520,
                buttonW,
                buttonH,
                "Help",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }
                getParentEnvironment().push(new HelpPage(getParentEnvironment()));

                return true;
            }
        };

        AButtonView button_quit = new AButtonView(
                getParentEnvironment(),
                (int)(centerW - (buttonW * .5f)),
                800,
                buttonW,
                buttonH,
                "Quit to Desktop",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }
                getEnvironmentsHandler().getSaveData().save();

                System.exit(0);

                return true;
            }
        };

        AImageView image_editorMode = new AImageView(
                getParentEnvironment(),
                Config.DEFAULT_WINDOW_WIDTH - (int)(64 * 2.25),
                Config.DEFAULT_WINDOW_HEIGHT - (int)(64 * 2.25),
                64,
                64,
                getResources().getImage("gear"),
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }
                getParentEnvironment().push(new EditLevelPage(getParentEnvironment()));

                return true;
            }
        };

        addComponent(button_newGame);
        addComponent(button_continueGame);
        addComponent(button_options);
        addComponent(button_help);
        addComponent(button_quit);
        addComponent(image_editorMode);

    }

    /**
     * <p>Navigates the user to the Level Select Page.</p>
     */
    public void navigateToLevelSelectPage() {
        getParentEnvironment().push(new LevelSelectPage(getParentEnvironment()));
    }

}
