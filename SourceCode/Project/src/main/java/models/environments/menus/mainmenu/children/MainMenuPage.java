package models.environments.menus.mainmenu.children;

import models.data.PreferenceData;
import props.prototypes.window.environments.menu.AMenu;
import props.prototypes.window.environments.menu.AMenuModel;
import props.prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

public class MainMenuPage extends AMenu {

    public MainMenuPage(AMenuModel parentModel) {
        super(parentModel);

        pages.add(new NewGamePage(parentMenuModel));
        pages.add(new LevelSelectPage(parentMenuModel));
        pages.add(new OptionsPage(parentMenuModel));
        pages.add(new HelpPage(parentMenuModel));
        pages.add(new QuitPage(parentMenuModel));

        float centerHoriz = PreferenceData.DEFAULT_WINDOW_WIDTH * .5f;
        //float centerVert = PreferenceData.DEFAULT_WINDOW_HEIGHT * .5f;
        int buttonW = 200, buttonH = 50;

        AMenuButton button_newGame = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                50,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.push(pages.get(0));

                return true;
            }
        };
        button_newGame.setText("New Game");

        AMenuButton button_continueGame = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                125,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.push(pages.get(1));

                return true;
            }
        };
        button_continueGame.setText("Continue Game");

        AMenuButton button_options = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                200,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.push(pages.get(2));

                return true;
            }
        };
        button_options.setText("Options");

        AMenuButton button_help = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                275,
                buttonW,
                buttonH) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.push(pages.get(3));

                return true;
            }
        };
        button_help.setText("Help");

        AMenuButton button_quit = new AMenuButton(parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                375,
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
        button_quit.setText("Quit Button");

        buttons.add(button_newGame);
        buttons.add(button_continueGame);
        buttons.add(button_options);
        buttons.add(button_help);
        buttons.add(button_quit);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Landing Page!", 50, 60);
    }

}
