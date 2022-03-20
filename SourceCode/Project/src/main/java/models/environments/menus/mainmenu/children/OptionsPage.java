package models.environments.menus.mainmenu.children;

import props.prototypes.window.environments.menu.AMenu;
import props.prototypes.window.environments.menu.AMenuModel;
import props.prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

public class OptionsPage extends AMenu {

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
        buttons.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Options Menu Model!", 50, 60);
    }
}
