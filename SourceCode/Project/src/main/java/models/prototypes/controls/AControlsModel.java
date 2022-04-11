package models.prototypes.controls;

import java.util.Arrays;

public abstract class AControlsModel {

    protected AKeyController keyController;
    protected AMouseController mouseController;

    public enum Directionals {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public enum Actions {
        ESCAPE,
        ENTER
    }

    protected boolean[] directionals = new boolean[Directionals.values().length];
    protected boolean[] actions = new boolean[Actions.values().length];

    public void init(AMouseController mouseController, AKeyController keyController) {
        this.mouseController = mouseController;
        this.keyController = keyController;
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

    public boolean getAction(Actions action) {
        return actions[action.ordinal()];
    }

    public void resetAction(Actions action) {
        actions[action.ordinal()] = false;
    }

    public void reset() {
        Arrays.fill(directionals, false);
        Arrays.fill(actions, false);
    }

}
