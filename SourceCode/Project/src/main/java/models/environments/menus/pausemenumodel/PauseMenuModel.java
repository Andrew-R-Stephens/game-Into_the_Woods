package models.environments.menus.pausemenumodel;

import props.prototypes.window.environments.menu.AMenu;
import props.prototypes.window.environments.menu.AMenuModel;

import java.awt.*;

public class PauseMenuModel extends AMenuModel {

    public PauseMenuModel() {
        AMenu landingMenu = new AMenu(this) {
            @Override
            public void draw(Graphics g) {
                super.draw(g);
            }

            @Override
            public void update(float delta) {
                super.update(delta);
            }
        };

        initPage(landingMenu);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Pause Menu!", 20, 20);
    }

    @Override
    public void update(float delta) {

    }
}
