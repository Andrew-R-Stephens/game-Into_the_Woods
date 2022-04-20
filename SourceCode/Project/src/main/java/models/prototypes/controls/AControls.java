package models.prototypes.controls;

import java.util.Arrays;

/**
 * <p></p>
 */
public abstract class AControls {

    protected AKeyController keyController;
    protected AMouseController mouseController;

    protected boolean[] directionals = new boolean[Directionals.values().length];
    protected boolean[] actions = new boolean[Actions.values().length];

    /**
     * <p></p>
     * @param mouseController -
     * @param keyController -
     */
    public void init(AMouseController mouseController, AKeyController keyController) {
        this.mouseController = mouseController;
        this.keyController = keyController;
    }

    /**
     * <p></p>
     * @return
     */
    public AKeyController getKeyController() {
        return keyController;
    }

    /**
     * <p></p>
     * @return
     */
    public AMouseController getMouseController() {
        return mouseController;
    }

    /**
     * <p></p>
     * @param type -
     * @param state -
     */
    public void setDirectional(Directionals type, boolean state) {
        directionals[type.ordinal()] = state;
    }

    /**
     * <p></p>
     * @return
     */
    public boolean[] getDirectionals() {
        return directionals;
    }

    /**
     *
     * @param type -
     * @param state -
     */
    public void setAction(Actions type, boolean state) {
        actions[type.ordinal()] = state;
    }

    /**
     *
     * @return
     */
    public boolean[] getActions() {
        return actions;
    }

    /**
     *
     * @param action -
     * @return
     */
    public boolean getAction(Actions action) {
        return actions[action.ordinal()];
    }

    /**
     *
     * @param action -
     */
    public void resetAction(Actions action) {
        actions[action.ordinal()] = false;
    }

    /**
     *
     */
    public void reset() {
        Arrays.fill(directionals, false);
        Arrays.fill(actions, false);
    }

    /**
     * <p></p>
     */
    public enum Directionals {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    /**
     * <p></p>
     */
    public enum Actions {
        ESCAPE,
        ENTER
    }

}
