package models.environments.menu.mainmenu.primarymenu.newgame;

import prototypes.actor.pawn.character.ACharacter;
import prototypes.window.environments.menu.AMenu;
import prototypes.window.environments.menu.AMenuModel;
import prototypes.window.environments.menu.components.types.AMenuButton;
import utils.files.Resources;

import java.awt.image.BufferedImage;

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

        backgroundImage = Resources.getImage("menubackground");

        BufferedImage img_button = Resources.getImage("testbutton2");

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
        character1.setBackgroundImage(img_button);
        character1.setImageScaling(AMenuButton.ImageScale.FILL_XY);

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
        character2.setBackgroundImage(img_button);
        character2.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        AMenuButton button_back = new AMenuButton(parentModel, 700, 700) {
            @Override
            public boolean onClick(float x, float y) {
                if(!isInBounds(x, y)){
                    return false;
                }

                parentMenuModel.pop();

                return true;
            }
        };
        button_back.setText("Back");
        button_back.setBackgroundImage(img_button);
        button_back.setImageScaling(AMenuButton.ImageScale.FILL_XY);

        components.add(character1);
        components.add(character2);
        components.add(button_back);
    }



}
