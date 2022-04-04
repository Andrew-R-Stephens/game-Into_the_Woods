package models.environments.menus.mainmenu.primarymenu;

import models.environments.menus.mainmenu.primarymenu.continuegame.LevelSelectPage;
import models.environments.menus.mainmenu.primarymenu.help.HelpPage;
import models.environments.menus.mainmenu.primarymenu.newgame.NewGamePage;
import models.environments.menus.mainmenu.primarymenu.options.OptionsPage;
import models.environments.menus.mainmenu.primarymenu.quit.QuitPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.image.BufferedImage;

/**
 * The type Main menu page.
 */
public class MainMenuPage extends AMenu {

    /**
     * Instantiates a new Main menu page.
     *
     * @param parentModel the parent model
     */
    public MainMenuPage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        bundle.addPage(new NewGamePage(parentMenuModel));
        bundle.addPage(new LevelSelectPage(parentMenuModel));
        bundle.addPage(new OptionsPage(parentMenuModel));
        bundle.addPage(new HelpPage(parentMenuModel));
        bundle.addPage(new QuitPage(parentMenuModel));

        BufferedImage img_button = Resources.getImage("button_hrect");

        float centerHoriz = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        AMenuButton button_newGame = new AMenuButton(
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

                parentMenuModel.push(bundle.getPage(0));

                return true;
            }
        };
        button_newGame.setText("New Game");
        button_newGame.setBackgroundImage(img_button);
        button_newGame.setTint("#F1FFEC10");
        button_newGame.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        AMenuButton button_continueGame = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                290,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.push(bundle.getPage(1));

                return true;
            }
        };
        button_continueGame.setText("Continue Game");
        button_continueGame.setBackgroundImage(img_button);
        button_continueGame.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

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

                parentMenuModel.push(bundle.getPage(2));

                return true;
            }
        };
        button_options.setText("Options");
        button_options.setBackgroundImage(img_button);
        button_options.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

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

                parentMenuModel.push(bundle.getPage(3));

                return true;
            }
        };
        button_help.setText("Help");
        button_help.setBackgroundImage(img_button);
        button_help.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

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
                System.exit(0);

                return true;
            }
        };
        button_quit.setText("Quit to Desktop");
        button_quit.setBackgroundImage(img_button);
        button_quit.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(button_newGame);
        components.add(button_continueGame);
        components.add(button_options);
        components.add(button_help);
        components.add(button_quit);

    }

}
