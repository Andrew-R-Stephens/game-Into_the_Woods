package models.environments.menus.mainmenu.children;

import models.data.PreferenceData;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;
import utils.files.Resources;

import java.awt.*;

public class StartScreenPage extends AMenu {

    /**
     * Instantiates a new A menu.
     *
     * @param parentMenuModel the parent menu model
     */
    public StartScreenPage(AMenuModel parentMenuModel) {

        super(parentMenuModel);

        float mx = PreferenceData.window_width_actual * .5f;
        float my = PreferenceData.window_height_actual * .5f;

        AMenuButton startButton = new AMenuButton(
                parentMenuModel,
                (int)(mx - (250 * .5f)),
                (int)(my - (50 * .5f)),
                250,
                50) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }

                parentMenuModel.push(new MainMenuPage(parentMenuModel));
                return true;

            }
        };


        startButton.setText("Start button");
        startButton.setBackgroundImage(Resources.imagesFiles.get("testbutton"));
        startButton.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        buttons.add(startButton);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.green);
        g.drawString("Start screen!", 50, 50);
    }

}
