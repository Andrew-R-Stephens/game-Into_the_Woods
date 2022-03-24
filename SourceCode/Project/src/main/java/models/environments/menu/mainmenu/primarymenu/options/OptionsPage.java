package models.environments.menu.mainmenu.primarymenu.options;

import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;

import java.awt.*;

/**
 * The type Options page.
 */
public class OptionsPage extends AMenu {

    /**
     * Instantiates a new Options page.
     *
     * @param parentModel the parent model
     */
    public OptionsPage(AMenuModel parentModel) {
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
        components.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Options Menu Model!", 50, 60);
    }
}
