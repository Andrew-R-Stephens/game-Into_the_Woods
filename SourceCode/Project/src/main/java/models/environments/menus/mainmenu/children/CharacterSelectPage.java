package models.environments.menus.mainmenu.children;

import prototypes.actor.pawn.character.ACharacter;
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

        AMenuButton character1 = new AMenuButton(parentModel, 500, 500) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }

                parentMenuModel.parentEnvironmentsModel.
                        getGameModel().getCharacter().setCharacterType(ACharacter.Type.CHAR1);

                return true;
            }
        };
        character1.setText("Blue Guy");

        AMenuButton character2 = new AMenuButton(parentModel, 900, 500) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }

                parentMenuModel.parentEnvironmentsModel.
                        getGameModel().getCharacter().setCharacterType(ACharacter.Type.CHAR2);

                return true;
            }
        };
        character2.setText("Pink Girl");

        AMenuButton back = new AMenuButton(parentModel, 700, 700) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }

                parentMenuModel.pop();

                return true;
            }
        };
        back.setText("Back");

        buttons.add(character1);
        buttons.add(character2);
        buttons.add(back);
    }



}
