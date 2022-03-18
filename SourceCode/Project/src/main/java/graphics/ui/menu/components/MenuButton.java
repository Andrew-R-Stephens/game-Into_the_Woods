package graphics.ui.menu.components;

import java.awt.*;

public class MenuButton {

    private int x, y, w, h;

    public MenuButton(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(x, y, w, h);
    }

}
