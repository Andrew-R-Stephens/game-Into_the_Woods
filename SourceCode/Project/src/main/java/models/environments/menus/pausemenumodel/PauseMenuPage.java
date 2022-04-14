package models.environments.menus.pausemenumodel;

import models.environments.EnvironmentsHandler;
import models.environments.menus.pausemenumodel.help.PauseHelpPage;
import models.environments.menus.pausemenumodel.options.PauseOptionsPage;
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

                ((PauseMenuEnvironment)parentMenuEnvironment).gameEnvironment.setPaused(false);
                parentMenuEnvironment.onExit();
                parentMenuEnvironment.getParentEnvironmentsHandler().setCurrentEnvironmentType(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuEnvironment.getParentEnvironmentsHandler().applyEnvironment(false);
                return true;

            }
        };
        button_resume.setText("Resume Game");
        //button_resume.setBackgroundImage(img_button);
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

                parentMenuEnvironment.push(new PauseOptionsPage(parentMenuModel));

                return true;
            }
        };
        button_options.setText("Options");
        //button_options.setBackgroundImage(img_button);
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

                parentMenuEnvironment.push(new PauseHelpPage(parentMenuModel));

                return true;
            }
        };
        button_help.setText("Help");
        //button_help.setBackgroundImage(img_button);
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

                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().onExit();
                parentMenuEnvironment.getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.MAIN_MENU, true);
                //parentMenuModel.parentEnvironmentsModel.getCurrentEnvironment().onResume();
                if(parentMenuEnvironment.getParentEnvironmentsHandler().getMenuEnvironment().getTopPage()
                        instanceof StartScreenPage ssp) {
                    ssp.navigateToMainMenuPage();
                }
                parentMenuEnvironment.getParentEnvironmentsHandler().applyEnvironment();

                parentMenuEnvironment.getParentEnvironmentsHandler().getGameEnvironment().setPaused(false);

                return true;
            }
        };
        button_quit.setText("Exit to Main Menu");
        //button_quit.setBackgroundImage(img_button);
        button_quit.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        // Add all components
        components.add(button_resume);
        components.add(button_options);
        components.add(button_help);
        components.add(button_quit);

    }

}
