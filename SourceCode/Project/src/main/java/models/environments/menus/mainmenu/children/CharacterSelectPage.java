package models.environments.menus.mainmenu.children;

import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.AMenuButton;

/**
 * The type Character create page.
 */
public class CharacterSelectPage extends AMenu {

    private int chosenCharacter = 0;

    /**
     * Instantiates a new Character create page.
     *
     * @param parentModel the parent model
     */
    public CharacterSelectPage(AMenuModel parentModel) {
        super(parentModel);

        AMenuButton character1 = new AMenuButton(parentModel, 20, 20, 50 ,60) {
            @Override
            public boolean onClick(float x, float y) {
                return false;
            }
        };
        character1.setText("CharacterName1");
        buttons.add(character1);


    }



}
