package models.environments.menus;

import models.prototypes.environments.menu.AMenu;

import java.util.ArrayList;

/**
 * <p></p>
 */
public class MenuBundle {

    private final ArrayList<AMenu> pages = new ArrayList<>();

    /**
     * <p></p>
     * @param page
     */
    public void addPage(AMenu page) {
        pages.add(page);
    }

    /**
     * <p></p>
     * @param pageNumber
     * @return
     */
    public AMenu getPage(int pageNumber) {
        if(pageNumber < pages.size()) {
            return pages.get(pageNumber);
        }

        return null;
    }
}
