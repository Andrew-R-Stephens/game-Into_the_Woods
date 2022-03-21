package models.environments.menus.mainmenu.children;

import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;

public class StartScreenPage extends AMenu {

    /**
     * Instantiates a new A menu.
     *
     * @param parentMenuModel the parent menu model
     */
    public StartScreenPage(AMenuModel parentMenuModel) {
        super(parentMenuModel);

        AMenuButton startButton = new AMenuButton(parentMenuModel, 200, 300, 250, 300) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }
                parentMenuModel.push(new MainMenuPage(parentMenuModel));
                return true;
            }
        };
        buttons.add(startButton);
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.green);
        g.drawString("Start screen!", 50, 50);
    }



}
