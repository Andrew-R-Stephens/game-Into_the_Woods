package props.prototypes.window.environments.menu;

import java.util.ArrayList;

/**
 * AMenuBundle is a class which allows for a Menu to contain a list of "pages" (other AMenuBundles or
 * AMenus) for easier flow control.
 */
public class MenuBundle {

    private ArrayList<AMenu> pages = new ArrayList();

    public void addPage(AMenu page) {
        pages.add(page);
    }

}
