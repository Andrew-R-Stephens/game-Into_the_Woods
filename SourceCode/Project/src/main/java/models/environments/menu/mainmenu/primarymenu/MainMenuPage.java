package models.environments.menu.mainmenu.primarymenu;

import models.environments.menu.mainmenu.primarymenu.continuegame.LevelSelectPage;
import models.environments.menu.mainmenu.primarymenu.help.HelpPage;
import models.environments.menu.mainmenu.primarymenu.newgame.NewGamePage;
import models.environments.menu.mainmenu.primarymenu.options.OptionsPage;
import models.environments.menu.mainmenu.primarymenu.quit.QuitPage;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
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

        BufferedImage img_button = Resources.getImage("testbutton2");

        float centerHoriz = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
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
        button_newGame.setBackgroundImage(img_button);
        button_newGame.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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
        button_continueGame.setBackgroundImage(img_button);
        button_continueGame.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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
        button_options.setBackgroundImage(img_button);
        button_options.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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
        button_help.setBackgroundImage(img_button);
        button_help.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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
        button_quit.setBackgroundImage(img_button);
        button_quit.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        components.add(button_newGame);
        components.add(button_continueGame);
        components.add(button_options);
        components.add(button_help);
        components.add(button_quit);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.blue);
        g.drawString("Landing Page!", 50, 50);
    }

}
