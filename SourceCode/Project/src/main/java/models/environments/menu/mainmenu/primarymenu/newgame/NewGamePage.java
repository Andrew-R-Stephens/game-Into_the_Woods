package models.environments.menu.mainmenu.primarymenu.newgame;

import models.environments.EnvironmentsHandler;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type New game page.
 */
public class NewGamePage extends AMenu {

    /**
     * Instantiates a new New game page.
     *
     * @param parentModel the parent model
     */
    public NewGamePage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        bundle.addPage(new CharacterSelectPage(parentModel));

        BufferedImage img_button = Resources.getImage("testbutton2");

        float mx = ConfigData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = ConfigData.DEFAULT_WINDOW_HEIGHT * .5f;

        int btn_width = 400, btn_height = (int)(btn_width * .25);

        AMenuButton button_characterCreate = new AMenuButton(
                parentModel,
                (int)(mx - (btn_width * .5f)),
                125,
                btn_width,
                btn_height) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.push(bundle.getPage(0));

                return true;
            }
        };
        button_characterCreate.setText("Create Character");
        button_characterCreate.setBackgroundImage(img_button);
        button_characterCreate.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);


        AMenuButton button_characterConfirm = new AMenuButton(
                parentModel,
                (int)(mx - (btn_width * .5f)),
                250,
                btn_width,
                btn_height) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.parentEnvironmentsModel.swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, true).applyEnvironment();

                return true;
            }
        };
        button_characterConfirm.setText("Confirm Character");
        button_characterConfirm.setBackgroundImage(img_button);
        button_characterConfirm.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);


        AMenuButton button_back = new AMenuButton(
                parentModel,
                (int) (mx - (btn_width * .5f)),
                800,
                btn_width,
                btn_height
        ) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.pop();

                return true;
            }
        };
        button_back.setText("Back");
        button_back.setBackgroundImage(img_button);
        button_back.setImageScaling(AMenuButton.ImageScale.FIT_CENTERED);

        components.add(button_characterCreate);
        components.add(button_characterConfirm);
        components.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("New Game Model!", 50, 60);
    }

}
