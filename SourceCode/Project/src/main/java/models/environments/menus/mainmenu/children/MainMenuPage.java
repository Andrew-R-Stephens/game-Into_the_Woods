package models.environments.menus.mainmenu.children;

import models.data.PreferenceData;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

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

        bundle.addPage(new NewGamePage(parentMenuModel));
        bundle.addPage(new LevelSelectPage(parentMenuModel));
        bundle.addPage(new OptionsPage(parentMenuModel));
        bundle.addPage(new HelpPage(parentMenuModel));
        bundle.addPage(new QuitPage(parentMenuModel));

        float centerHoriz = PreferenceData.window_width_actual * .5f;
        //float centerVert = PreferenceData.DEFAULT_WINDOW_HEIGHT * .5f;
        int buttonW = 200, buttonH = 50;

        AMenuButton button_newGame = new AMenuButton(
                parentMenuModel,
                (int)(centerHoriz - (buttonW * .5f)),
                50,
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

                parentMenuModel.push(bundle.getPage(1));

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

                parentMenuModel.push(bundle.getPage(2));

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

                parentMenuModel.push(bundle.getPage(3));

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

        g.setColor(Color.blue);
        g.drawString("Landing Page!", 50, 50);
    }

}
