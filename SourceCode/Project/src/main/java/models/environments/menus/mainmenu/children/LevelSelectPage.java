package models.environments.menus.mainmenu.children;

import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

/**
 * The type Level select page.
 */
public class LevelSelectPage extends AMenu {

    /**
     * Instantiates a new Level select page.
     *
     * @param parentModel the parent model
     */
    public LevelSelectPage(AMenuModel parentModel) {
        super(parentModel);

        AMenuButton button_char1 = new AMenuButton(parentModel,500, 500, 200, 50) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.pop();

                return true;
            }
        };
        button_char1.setText("Character 1");

        AMenuButton button_char2 = new AMenuButton(parentModel,500, 500, 200, 50) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)) {
                    return false;
                }

                parentMenuModel.pop();

                return true;
            }
        };
        button_char2.setText("Character 2");

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

        buttons.add(button_char1);
        buttons.add(button_char2);

        buttons.add(button_back);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.red);
        g.drawString("Level Select Model!", 50, 60);
    }
}
