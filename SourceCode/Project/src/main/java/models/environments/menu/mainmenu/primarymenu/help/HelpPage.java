package models.environments.menu.mainmenu.primarymenu.help;

import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.files.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Help page.
 */
public class HelpPage extends AMenu {

    /**
     * Instantiates a new Help page.
     *
     * @param parentModel the parent model
     */
    public HelpPage(AMenuModel parentModel) {
        super(parentModel);

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_button = Resources.getImage("testbutton2");

        AMenuButton button_back = new AMenuButton(parentModel,500, 500, 200, 50) {
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
        button_back.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        components.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Help Menu Model!", 50, 60);
    }

}
