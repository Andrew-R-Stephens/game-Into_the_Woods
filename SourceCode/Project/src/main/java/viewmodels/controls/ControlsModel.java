package viewmodels.controls;

import controls.GameKeyControls;
import controls.GameMouseControls;
import utils.KeyController;
import utils.MouseController;

public class ControlsModel {

    private KeyController keyController;
    private MouseController mouseController;

    public enum Directionals {LEFT, RIGHT, UP, DOWN};
    public enum Abilities {JUMP, DASH};

    boolean[] directionals = new boolean[Directionals.values().length];
    boolean[] abilities = new boolean[Abilities.values().length];

    public void init() {
        mouseController = new GameMouseControls(this);
        keyController = new GameKeyControls(this);
    }

    public KeyController getKeyController() {
        return keyController;
    }

    public MouseController getMouseController() {
        return mouseController;
    }

    public void setDirectional(Directionals type, boolean state) {
        directionals[type.ordinal()] = state;
    }

    public boolean[] getDirectionals() {
        return directionals;
    }

    public void setAbilities(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    public boolean[] getAbilities() {
        return abilities;
    }

    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    public boolean getAbility(Abilities type) {
        return abilities[type.ordinal()];
    }

}
