package models.environments.menus.pausemenumodel.submenus;

import models.environments.EnvironmentsHandler;
import models.environments.menus.startscreen.StartScreenPage;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;

public class PauseMenuPage extends AMenu {

    public PauseMenuPage(AMenuEnvironment parentMenuModel) {
        super(parentMenuModel);

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        // Resume button
        AButtonView button_resume = new AButtonView(
                parentMenuModel,
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

                getEnvironmentHandler().getGameEnvironment().setPaused(false);
                getParentEnvironment().onExit();
                getEnvironmentHandler().setCurrentEnvironmentType(EnvironmentsHandler.EnvironmentType.GAME);
                getEnvironmentHandler().applyEnvironment(false);
                return true;

            }
        };
        button_resume.setText("Resume Game");
        button_resume.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // Options button
        AButtonView button_options = new AButtonView(
                parentMenuModel,
                (int)(centerW - (buttonW * .5f)),
                405,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getParentEnvironment().push(new PauseOptionsPage(getParentEnvironment()));

                return true;
            }
        };
        button_options.setText("Options");
        button_options.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // Help button
        AButtonView button_help = new AButtonView(
                parentMenuModel,
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
        AButtonView button_quit = new AButtonView(parentMenuModel,
                (int)(centerW - (buttonW * .5f)),
                800,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                getEnvironmentHandler().getGameEnvironment().onExit();
                getEnvironmentHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.MAIN_MENU, true);
                if(getEnvironmentHandler().getMenuEnvironment().getTopPage()
                        instanceof StartScreenPage ssp) {
                    ssp.navigateToMainMenuPage();
                }
                getEnvironmentHandler().applyEnvironment();
                getEnvironmentHandler().getGameEnvironment().setPaused(false);

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
