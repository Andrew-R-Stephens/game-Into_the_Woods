package props.prototypes.window.environments.menu;

import java.awt.*;
import java.util.ArrayList;

/**
 * AMenuBundle is a an abstract class which allows for a Menu to contain a list of "pages" (other AMenuBundles or
 * AMenus) for easier flow control.
 */
public abstract class AMenuBundle extends AMenu {

    private ArrayList<AMenu> pages = new ArrayList();
    private int currentPage = 0;

    protected void addPage(AMenu page) {
        pages.add(page);
    }

    protected ArrayList<AMenu> getAllPages() {
        return pages;
    }

    @Override
    public void draw(Graphics g) {
        /**
         * TODO: Draw current page
         */
    }

    @Override
    public void update(float delta) {
        /**
         * TODO: Update current page
         */
    }
}
