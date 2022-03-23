package models.environments.menus.mainmenu.children;

import models.data.PreferenceData;
import models.environments.EnvironmentsModel;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

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

        float mx = PreferenceData.window_width_actual * .5f;
        float my = PreferenceData.window_height_actual * .5f;

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
        buttons.add(button_characterCreate);


        AMenuButton button_characterConfirm = new AMenuButton(parentModel,(int)(mx - (200 * .5f)), 200, 200, 50) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.parentEnvironmentsModel.setCurrentEnvironment(EnvironmentsModel.EnvironmentType.GAME);
                parentMenuModel.parentEnvironmentsModel.applyEnvironment();

                return true;
            }
        };
        button_characterConfirm.setText("Confirm Character");
        buttons.add(button_characterConfirm);

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
        buttons.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("New Game Model!", 50, 60);
    }

}
