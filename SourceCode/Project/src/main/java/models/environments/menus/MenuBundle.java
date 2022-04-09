package models.environments.menus;

import models.prototypes.window.environments.menu.AMenu;

import java.util.ArrayList;

public class MenuBundle {

    private final ArrayList<AMenu> pages = new ArrayList<>();

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
