package models.environments.menus.mainmenu.children;

import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

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
        buttons.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Help Menu Model!", 50, 60);
    }

}
