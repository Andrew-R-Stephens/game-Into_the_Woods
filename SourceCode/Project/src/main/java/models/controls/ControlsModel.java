package models.controls;

import models.controls.game.GameKeyControls;
import models.controls.game.GameMouseControls;
import utils.controllers.AKeyController;
import utils.controllers.AMouseController;

/**
 * TODO: Add description
 */
public class ControlsModel {

    private AKeyController keyController;
    private AMouseController mouseController;

    public enum Directionals {LEFT, RIGHT, UP, DOWN}

    public enum Actions {ESCAPE, ENTER}

    public enum Abilities {JUMP, DASH}

    boolean[] directionals = new boolean[Directionals.values().length];
    boolean[] actions = new boolean[Actions.values().length];
    boolean[] abilities = new boolean[Abilities.values().length];

    public void init() {
        mouseController = new GameMouseControls(this);
        keyController = new GameKeyControls(this);
    }

    public AKeyController getKeyController() {
        return keyController;
    }

    public AMouseController getMouseController() {
        return mouseController;
    }

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
