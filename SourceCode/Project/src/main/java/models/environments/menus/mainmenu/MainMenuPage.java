package models.environments.menus.mainmenu;

import models.environments.menus.mainmenu.continuegame.LevelSelectPage;
import models.environments.menus.mainmenu.help.HelpPage;
import models.environments.menus.mainmenu.newgame.NewGamePage;
import models.environments.menus.mainmenu.options.OptionsPage;
import models.prototypes.environments.menu.AMenu;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.prototypes.environments.menu.components.types.AButtonView;
import models.utils.resources.Resources;

public class MainMenuPage extends AMenu {

    public MainMenuPage(AMenuEnvironment parentModel) {
        super(parentModel);

        image_background = Resources.getImage("menubackground");

        int buttonW = 400, buttonH = (int)(buttonW * .25);

        AButtonView button_newGame = new AButtonView(
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

                parentMenuEnvironment.push(new NewGamePage(parentMenuModel));

                return true;
            }
        };
        button_newGame.setText("New Game");
        button_newGame.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        AButtonView button_continueGame = new AButtonView(
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
        button_continueGame.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

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

                parentMenuEnvironment.push(new OptionsPage(parentMenuModel));

                return true;
            }
        };
        button_options.setText("Options");
        button_options.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

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

                parentMenuEnvironment.push(new HelpPage(parentMenuModel));

                return true;
            }
        };
        button_help.setText("Help");
        button_help.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

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
                System.exit(0);

                return true;
            }
        };
        button_quit.setText("Quit to Desktop");
        button_quit.setBackgroundImage(image_buttonRect);
        button_quit.setImageScaling(AButtonView.ImageScale.FIT_CENTERED);

        components.add(button_newGame);
        components.add(button_continueGame);
        components.add(button_options);
        components.add(button_help);
        components.add(button_quit);

    }

    public void navigateToLevelSelectPage() {
        parentMenuModel.push(new LevelSelectPage(parentMenuModel));
    }

}
