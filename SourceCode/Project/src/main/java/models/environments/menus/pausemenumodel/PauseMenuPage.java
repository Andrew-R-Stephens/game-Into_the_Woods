package models.environments.menus.pausemenumodel;

import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.environments.menus.pausemenumodel.help.PauseHelpPage;
import models.environments.menus.pausemenumodel.options.PauseOptionsPage;
import models.environments.menus.startscreen.StartScreenPage;

public class PauseMenuPage extends AMenu {

    public PauseMenuPage(AMenuEnvironment parentMenuModel) {
        super(parentMenuModel);

        bundle.addPage(new PauseOptionsPage(parentMenuModel));
        bundle.addPage(new PauseHelpPage(parentMenuModel));

        //BufferedImage img_button = Resources.getImage("button_hrect");

        float centerHoriz = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        // Resume button
        AMenuButton button_resume = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
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
                /*parentMenuEnvironment.parentEnvironmentsHandler.swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, false).applyEnvironment(false);
*/
                parentMenuEnvironment.parentEnvironmentsHandler.setCurrentEnvironmentType(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuEnvironment.parentEnvironmentsHandler.applyEnvironment(false);
                return true;

            }
        };
        button_resume.setText("Resume Game");
        //button_resume.setBackgroundImage(img_button);
        button_resume.setTint("#F1FFEC10");
        button_resume.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        // Options button
        AMenuButton button_options = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                405,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuEnvironment.push(bundle.getPage(0));

                return true;
            }
        };
        button_options.setText("Options");
        //button_options.setBackgroundImage(img_button);
        button_options.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        // Help button
        AMenuButton button_help = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                520,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuEnvironment.push(bundle.getPage(1));

                return true;
            }
        };
        button_help.setText("Help");
        //button_help.setBackgroundImage(img_button);
        button_help.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        // Quit button
        AMenuButton button_quit = new AMenuButton(parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                800,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuEnvironment.parentEnvironmentsHandler.getGameEnvironment().onExit();
                parentMenuEnvironment.parentEnvironmentsHandler.swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.MAIN_MENU, true);
                //parentMenuModel.parentEnvironmentsModel.getCurrentEnvironment().onResume();
                if(parentMenuEnvironment.parentEnvironmentsHandler.getMenuEnvironment().getTopPage()
                        instanceof StartScreenPage ssp) {
                    ssp.navigateToMainMenuPage();
                }
                parentMenuEnvironment.parentEnvironmentsHandler.applyEnvironment();

                parentMenuEnvironment.parentEnvironmentsHandler.getGameEnvironment().setPaused(false);

                return true;
            }
        };
        button_quit.setText("Exit to Main Menu");
        //button_quit.setBackgroundImage(img_button);
        button_quit.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        // Add all components
        components.add(button_resume);
        components.add(button_options);
        components.add(button_help);
        components.add(button_quit);

    }

}
