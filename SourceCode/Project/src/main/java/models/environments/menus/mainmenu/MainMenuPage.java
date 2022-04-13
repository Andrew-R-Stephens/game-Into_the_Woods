package models.environments.menus.mainmenu;

import models.prototypes.window.environments.menu.AMenu;
import models.prototypes.window.environments.menu.AMenuEnvironment;
import models.prototypes.window.environments.menu.components.types.AMenuButton;
import models.utils.config.ConfigData;
import models.utils.resources.Resources;
import models.environments.menus.mainmenu.continuegame.LevelSelectPage;
import models.environments.menus.mainmenu.help.HelpPage;
import models.environments.menus.mainmenu.newgame.NewGamePage;
import models.environments.menus.mainmenu.options.OptionsPage;
import models.environments.menus.mainmenu.quit.QuitPage;

public class MainMenuPage extends AMenu {

    public MainMenuPage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        bundle.addPage(new NewGamePage(parentMenuModel));
        bundle.addPage(new LevelSelectPage(parentMenuModel));
        bundle.addPage(new OptionsPage(parentMenuModel));
        bundle.addPage(new HelpPage(parentMenuModel));
        bundle.addPage(new QuitPage(parentMenuModel));

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        AMenuButton button_newGame = new AMenuButton(
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

                parentMenuEnvironment.push(bundle.getPage(0));

                return true;
            }
        };
        button_newGame.setText("New Game");
        button_newGame.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        AMenuButton button_continueGame = new AMenuButton(
                parentMenuModel,
                (int)(centerW - (buttonW * .5f)),
                290,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                navigateToLevelSelectPage();

                return true;
            }
        };
        button_continueGame.setText("Continue Game");
        button_continueGame.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        AMenuButton button_options = new AMenuButton(
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

                parentMenuEnvironment.push(bundle.getPage(2));

                return true;
            }
        };
        button_options.setText("Options");
        button_options.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        AMenuButton button_help = new AMenuButton(
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

                parentMenuEnvironment.push(bundle.getPage(3));

                return true;
            }
        };
        button_help.setText("Help");
        button_help.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        AMenuButton button_quit = new AMenuButton(parentMenuModel,
                (int)(centerW - (buttonW * .5f)),
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
        button_quit.setBackgroundImage(image_buttonRect);
        button_quit.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(button_newGame);
        components.add(button_continueGame);
        components.add(button_options);
        components.add(button_help);
        components.add(button_quit);

    }

    public void navigateToLevelSelectPage() {
        parentMenuModel.push(bundle.getPage(1));
    }

}
