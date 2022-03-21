package models.environments.menus;

import prototypes.window.environments.menu.AMenu;

import java.util.ArrayList;

/**
 * AMenuBundle is a class which allows for a Menu to contain a list of "pages" (other AMenuBundles or
 * AMenus) for easier flow control.
 */
public class MenuBundle {

    private final ArrayList<AMenu> pages = new ArrayList<>();

    /**
     * Add page.
     *
     * @param page the page
     */
    public void addPage(AMenu page) {
        pages.add(page);
    }

    public AMenu getPage(int pageNumber) {
        if(pageNumber < pages.size()) {
            return pages.get(pageNumber);
        }

        return null;
    }
}
