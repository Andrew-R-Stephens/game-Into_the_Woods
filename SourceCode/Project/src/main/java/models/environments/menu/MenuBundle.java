package models.environments.menu;

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

    /**
     * Gets page.
     *
     * @param pageNumber the page number
     * @return the page
     */
    public AMenu getPage(int pageNumber) {
        if(pageNumber < pages.size()) {
            return pages.get(pageNumber);
        }

        return null;
    }
}
