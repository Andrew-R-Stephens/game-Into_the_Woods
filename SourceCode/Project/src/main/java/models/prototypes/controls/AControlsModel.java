package models.prototypes.controls;

/**
 * The type A controls model.
 */
public abstract class AControlsModel {

    /**
     * The Key controller.
     */
    protected AKeyController keyController;
    /**
     * The Mouse controller.
     */
    protected AMouseController mouseController;

    /**
     * The enum Directionals.
     */
    public enum Directionals {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    /**
     * The enum Actions.
     */
    public enum Actions {
        ESCAPE,
        ENTER
    }

    /**
     * The Directionals.
     */
    protected boolean[] directionals = new boolean[Directionals.values().length];
    /**
     * The Actions.
     */
    protected boolean[] actions = new boolean[Actions.values().length];

    /**
     * Init.
     *
     * @param mouseController the mouse controller
     * @param keyController   the key controller
     */
    public void init(AMouseController mouseController, AKeyController keyController) {
        this.mouseController = mouseController;
        this.keyController = keyController;
    }

    /**
     * Gets key controller.
     *
     * @return the key controller
     */
    public AKeyController getKeyController() {
        return keyController;
    }

    /**
     * Gets mouse controller.
     *
     * @return the mouse controller
     */
    public AMouseController getMouseController() {
        return mouseController;
    }

    /**
     * Sets directional.
     *
     * @param type  the type
     * @param state the state
     */
    public void setDirectional(Directionals type, boolean state) {
        directionals[type.ordinal()] = state;
    }

    /**
     * Get directionals boolean [ ].
     *
     * @return the boolean [ ]
     */
    public boolean[] getDirectionals() {
        return directionals;
    }

    /**
     * Sets action.
     *
     * @param type  the type
     * @param state the state
     */
    public void setAction(Actions type, boolean state) {
        actions[type.ordinal()] = state;
    }

    /**
     * Get actions boolean [ ].
     *
     * @return the boolean [ ]
     */
    public boolean[] getActions() {
        return actions;
    }

    public boolean getAction(Actions action) {
        return actions[action.ordinal()];
    }

    public void resetAction(Actions action) {
        actions[action.ordinal()] = false;
    }


}
