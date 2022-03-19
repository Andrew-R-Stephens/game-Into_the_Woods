package models.controls.game;

import props.prototypes.controls.AControlsModel;

/**
 * TODO: Add description
 */
public class GameControlsModel extends AControlsModel {

    public enum Directionals {LEFT, RIGHT, UP, DOWN}

    public enum Actions {ESCAPE, ENTER}

    public enum Abilities {JUMP, DASH}

    boolean[] directionals = new boolean[Directionals.values().length];
    boolean[] actions = new boolean[Actions.values().length];
    boolean[] abilities = new boolean[Abilities.values().length];

    public void setDirectional(Directionals type, boolean state) {
        directionals[type.ordinal()] = state;
    }

    public boolean[] getDirectionals() {
        return directionals;
    }

    public void setAction(Actions type, boolean state) {
        actions[type.ordinal()] = state;
    }

    public boolean[] getActions() {
        return actions;
    }

    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    public boolean[] getAbilities() {
        return abilities;
    }

}
