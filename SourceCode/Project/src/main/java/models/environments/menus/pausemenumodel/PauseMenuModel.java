package models.environments.menus.pausemenumodel;

import props.prototypes.window.environments.menu.AMenuBundle;

import java.awt.*;

public class PauseMenuModel extends AMenuBundle {

    public PauseMenuModel() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Paused!", 20, 20);
    }

    @Override
    public void update(float delta) {

    }
}
