package models.environments.menu.mainmenu.primarymenu.newgame;

import models.environments.EnvironmentsHandler;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.config.PreferenceData;
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

        bundle.addPage(new CharacterSelectPage(parentModel));

        float mx = PreferenceData.DEFAULT_WINDOW_WIDTH * .5f;
        float my = PreferenceData.DEFAULT_WINDOW_HEIGHT * .5f;

        BufferedImage img_button = Resources.getImage("img_button2");

        AMenuButton button_characterCreate = new AMenuButton(parentModel,(int)(mx - (200 * .5f)), 125, 200, 50) {
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
        button_characterCreate.setImageScaling(AMenuButton.ImageScale.FILL_XY);



        AMenuButton button_characterConfirm = new AMenuButton(parentModel,(int)(mx - (200 * .5f)), 200, 200, 50) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.parentEnvironmentsModel.swapToEnvironment(EnvironmentsHandler.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

                return true;
            }
        };
        button_characterConfirm.setText("Confirm Character");
        button_characterConfirm.setBackgroundImage(img_button);
        button_characterConfirm.setImageScaling(AMenuButton.ImageScale.FILL_XY);


        AMenuButton button_back = new AMenuButton(parentModel,(int)(mx - (200 * .5f)), 500, 200, 50) {
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
        button_characterConfirm.setBackgroundImage(img_button);
        button_back.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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
