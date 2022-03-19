package models.controls.menu;

import props.prototypes.controls.AControlsModel;

/**
 * TODO: Add description
 */
public class MenuControlsModel extends AControlsModel {

    public boolean isAwaitingRelease = false;

    public enum Directionals {LEFT, RIGHT, UP, DOWN}
    public enum Actions {ESCAPE, ENTER}

    boolean[] directionals = new boolean[Directionals.values().length];
    boolean[] actions = new boolean[Actions.values().length];

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

}
