package models.environments.menus.pausemenumodel.submenus;

import models.environments.EnvironmentsHandler;
import models.environments.menus.mainmenu.submenus.OptionsPage;
import models.environments.menus.startscreen.StartScreenPage;
import models.prototypes.components.menuviews.types.AButtonView;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;

/**
 * <p>The landing page of the Pause Menu. Contains menu button components for navigation into sub pages.</p>
 */
public class PauseMenuPage extends AMenu {

    /**
     * <p>Builds the paused main page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public PauseMenuPage(AMenuEnvironment parentEnvironment) {
        super(parentEnvironment);

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        // Resume button
        AButtonView button_resume = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                175,
                buttonW,
                buttonH)
        {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                System.out.println("Pressed");

                getEnvironmentsHandler().getGameEnvironment().setPaused(false);
                getParentEnvironment().onExit();
                getEnvironmentsHandler().setCurrentEnvironmentType(EnvironmentsHandler.EnvironmentType.GAME);
                getEnvironmentsHandler().applyEnvironment(false);
                return true;

            }
        };
        button_resume.setText("Resume Game");
        button_resume.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // Options button
        AButtonView button_options = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                405,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new OptionsPage(getParentEnvironment()));

                return true;
            }
        };
        button_options.setText("Options");
        button_options.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // Help button
        AButtonView button_help = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                520,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new PauseHelpPage(getParentEnvironment()));

                return true;
            }
        };
        button_help.setText("Help");
        button_help.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // Quit button
        AButtonView button_quit = new AButtonView(parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                800,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentsHandler().getGameEnvironment().onExit();
                getEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.MAIN_MENU, true);
                if(getEnvironmentsHandler().getMenuEnvironment().getTopPage()
                        instanceof StartScreenPage ssp) {
                    ssp.navigateToMainMenuPage();
                }
                getEnvironmentsHandler().applyEnvironment();
                getEnvironmentsHandler().getGameEnvironment().setPaused(false);

                return true;
            }
        };
        button_quit.setText("Exit to Main Menu");
        button_quit.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // Add all components
        addComponent(button_resume);
        addComponent(button_options);
        addComponent(button_help);
        addComponent(button_quit);

    }

}
