package models.environments.menus.gamepausemenumodel.submenus;

import models.environments.EnvironmentType;
import models.environments.editor.EditorEnvironment;
import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.submenus.HelpPage;
import models.environments.menus.mainmenu.submenus.OptionsPage;
import models.environments.menus.startscreen.StartScreenPage;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.environments.AEnvironment;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;

/**
 * <p>The landing page of the Pause Menu. Contains menu button components for navigation into sub pages.</p>
 * @author Andrew Stephens
 */
public class GamePauseMenuPage extends AMenu {

    /**
     * <p>Builds the paused main page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public GamePauseMenuPage(AMenuEnvironment parentEnvironment, EnvironmentType environmentType) {
        super(parentEnvironment);

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        // Resume button
        AButtonView button_resume = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                175,
                buttonW,
                buttonH,
                "Resume Game",
                AButtonView.ImageScale.FIT_CENTERED
        )
        {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                GameEnvironment environment = getEnvironmentsHandler().getGameEnvironment();
                environment.setPaused(false);

                getParentEnvironment().onExit();
                getEnvironmentsHandler().setCurrentEnvironmentType(environmentType);
                getEnvironmentsHandler().applyEnvironment(false);

                return true;

            }
        };

        // Options button
        AButtonView button_options = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                405,
                buttonW,
                buttonH,
                "Options",
                AButtonView.ImageScale.FIT_CENTERED
        ){
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new OptionsPage(getParentEnvironment()));

                return true;
            }
        };

        // Help button
        AButtonView button_help = new AButtonView(
                parentEnvironment,
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

        // Quit button
        AButtonView button_quit = new AButtonView(parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                800,
                buttonW,
                buttonH,
                "Exit to Main Menu",
                AButtonView.ImageScale.FIT_CENTERED) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                AEnvironment currentEnvironment = getEnvironmentsHandler().getEnvironment(environmentType);
                currentEnvironment.onExit();
                getEnvironmentsHandler().swapToEnvironment(
                        EnvironmentType.MAIN_MENU, true);
                if(getEnvironmentsHandler().getMenuEnvironment().getTopPage()
                        instanceof StartScreenPage ssp) {
                    ssp.navigateToMainMenuPage();
                }
                getEnvironmentsHandler().applyEnvironment();
                if(currentEnvironment instanceof GameEnvironment ge) {
                    ge.setPaused(false);
                }else if (currentEnvironment instanceof EditorEnvironment ee) {
                    ee.setPaused(false);
                }

                return true;
            }
        };

        // Add all components
        addComponent(button_resume);
        addComponent(button_options);
        addComponent(button_help);
        addComponent(button_quit);

    }

}
