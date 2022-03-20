package models.environments.menus.mainmenu.children;

import props.prototypes.window.environments.menu.AMenu;
import props.prototypes.window.environments.menu.AMenuModel;
import props.prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

public class NewGamePage extends AMenu {

    public NewGamePage(AMenuModel parentModel) {
        super(parentModel);

        bundle.addPage(new CharacterCreatePage(parentModel));
        AMenuButton button_characterCreate = new AMenuButton(parentModel,500, 200, 200, 50) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                return true;
            }
        };
        button_characterCreate.setText("Confirm Character");
        buttons.add(button_characterCreate);

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
        g.drawString("New Game Model!", 50, 60);
    }

}
