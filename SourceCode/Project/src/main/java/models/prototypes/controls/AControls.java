package models.prototypes.controls;

import java.util.Arrays;

/**
 * <p>AControls is the parent class of all Environment-specific Controls. Contains the state of Actions and state of
 * movement controls as well as references to the Mouse and Key Controls which make up the Environment's controls.
 * </p>
 */
public abstract class AControls {

    protected AKeyController keyController;
    protected AMouseController mouseController;

    protected boolean[] directionals = new boolean[Directionals.values().length];
    protected boolean[] actions = new boolean[Actions.values().length];

    /**
     * <p>Initializes the AControls model. Assigns AMouseController and AKeyController subtypes.</p>
     * @param mouseController The AMouseController subtype specific to the chosen AEnvironment subtype.
     * @param keyController The AKeyontroller subtype specific to the chosen AEnvironment subtype.
     */
    public void init(AMouseController mouseController, AKeyController keyController) {
        this.mouseController = mouseController;
        this.keyController = keyController;
    }

    /**
     * @return The KeyController that's specific to a subtype of AEnvironment which this object is built for.
     */
    public AKeyController getKeyController() {
        return keyController;
    }

    /**
     * @return The MouseController that's specific to a subtype of AEnvironment which this object is built for.
     */
    public AMouseController getMouseController() {
        return mouseController;
    }

    /**
     * <p>Accepts the Directional type and the state that the Directional is to be set to.</p>
     * @param type The Directional type which is specific to this AEnvironment subtype.
     * @param state The state that this Directional state must accept.
     */
    public void setDirectional(Directionals type, boolean state) {
        directionals[type.ordinal()] = state;
    }

    /**
     * @return The state of all Directionals.
     */
    public boolean[] getDirectionals() {
        return directionals;
    }

    /**
     * <p>Accepts the Action type and the state that the Action is to be set to.</p>
     * @param type The Action type which is specific to this AEnvironment subtype.
     * @param state The state that this Action state must accept.
     */
    public void setAction(Actions type, boolean state) {
        actions[type.ordinal()] = state;
    }

    /**
     * @return The state of all Actions.
     */
    public boolean[] getActions() {
        return actions;
    }

    /**
     * <p>Obtains the state of the accepted Action type.</p>
     * @param action The requested action.
     * @return The state of the requested action.
     */
    public boolean getAction(Actions action) {
        return actions[action.ordinal()];
    }

    /**
     * <p>Resets the specific Action to a disabled state.</p>
     * @param action The Action requested to be reset.
     */
    public void resetAction(Actions action) {
        actions[action.ordinal()] = false;
    }

    /**
     * <p>Sets the state of both Directionals and Actions to disabled.</p>
     */
    public void reset() {
        Arrays.fill(directionals, false);
        Arrays.fill(actions, false);
    }

    /**
     * <p>These are the Directions that dictate the state of basic directional movement.</p>
     */
    public enum Directionals {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    /**
     * <p>These are the Actions which a user has at their disposal to assist movement with enhancements in mobility.</p>
     */
    public enum Actions {
        ESCAPE,
        ENTER
    }

}
