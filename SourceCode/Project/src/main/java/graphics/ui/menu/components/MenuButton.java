package graphics.ui.menu.components;

import java.awt.*;

public class MenuButton {

    private int x, y, w, h;

    public void draw(Graphics g) {
        g.drawRect(x, y, w, h);
    }

}
