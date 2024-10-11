package models.environments.menus.editpausemenumodel.submenus;

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
public class EditorPauseMenuPage extends AMenu {

    /**
     * <p>Builds the paused main page.</p>
     * @param parentEnvironment The parent AMenuEnvironment
     */
    public EditorPauseMenuPage(AMenuEnvironment parentEnvironment, EnvironmentType environmentType) {
        super(parentEnvironment);

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        // Resume button
        AButtonView button_resume = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                175,
                buttonW,
                buttonH,
                "Resume",
                AButtonView.ImageScale.FIT_CENTERED
        )
        {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                EditorEnvironment environment = getEnvironmentsHandler().getEditorEnvironment();
                environment.setPaused(false);

                getParentEnvironment().onExit();
                getEnvironmentsHandler().setCurrentEnvironmentType(environmentType);
                getEnvironmentsHandler().applyEnvironment(false);
                return true;

            }
        };

        // Save button
        AButtonView button_save = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                405,
                buttonW,
                buttonH,
                "Save",
                AButtonView.ImageScale.FIT_CENTERED
        ){
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //getParentEnvironment().push(new OptionsPage(getParentEnvironment()));
                //return true;

                return false;
            }
        };

        // Discard button
        AButtonView button_discard = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                520,
                buttonW,
                buttonH,
                "Discard",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                //getParentEnvironment().push(new HelpPage(getParentEnvironment()));
                //return true;

                return false;
            }
        };

        // Discard button
        AButtonView button_test = new AButtonView(
                parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                635,
                buttonW,
                buttonH,
                "Test Level",
                AButtonView.ImageScale.FIT_CENTERED
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                AEnvironment currentEnvironment = getEnvironmentsHandler().getEnvironment(environmentType);
                getEnvironmentsHandler().getEditorEnvironment().setPaused(true);
                currentEnvironment.onExit();

                getEnvironmentsHandler().getGameEnvironment().setPaused(true);
                getEnvironmentsHandler()
                        .swapToEnvironment(
                                EnvironmentType.GAME_PAUSE_MENU,
                                false)
                        .applyEnvironment(true);

                return true;
            }
        };

        // Quit button
        AButtonView button_quit = new AButtonView(parentEnvironment,
                (int)(centerW - (buttonW * .5f)),
                800,
                buttonW,
                buttonH,
                "Exit Editor",
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
        addComponent(button_save);
        addComponent(button_discard);
        addComponent(button_test);
        addComponent(button_quit);

    }

}
