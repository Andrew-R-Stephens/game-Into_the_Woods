package models.controls;

import prototypes.controls.AControlsModel;

import java.util.Arrays;

/**
 * TODO: Add description
 */
public class MenuControlsModel extends AControlsModel {

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
    boolean[] directionals = new boolean[Directionals.values().length];
    /**
     * The Actions.
     */
    boolean[] actions = new boolean[Actions.values().length];

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

    public boolean isAction(Actions type) {
        return actions[type.ordinal()];
    }

    /**
     * Get actions boolean [ ].
     *
     * @return the boolean [ ]
     */
    public boolean[] getActions() {
        return actions;
    }

    public void resetInput() {
        Arrays.fill(directionals, false);
        Arrays.fill(actions, false);
    }

}
