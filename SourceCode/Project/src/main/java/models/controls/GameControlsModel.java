package models.controls;

import prototypes.controls.AControlsModel;

/**
 * TODO: Add description
 */
public class GameControlsModel extends AControlsModel {

    /**
     * The enum Directionals.
     */
    public enum Directionals {
        /**
         * Left directionals.
         */
        LEFT,
        /**
         * Right directionals.
         */
        RIGHT,
        /**
         * Up directionals.
         */
        UP,
        /**
         * Down directionals.
         */
        DOWN}

    /**
     * The enum Actions.
     */
    public enum Actions {
        /**
         * Escape actions.
         */
        ESCAPE,
        /**
         * Enter actions.
         */
        ENTER}

    /**
     * The enum Abilities.
     */
    public enum Abilities {
        /**
         * Jump abilities.
         */
        JUMP,
        /**
         * Dash abilities.
         */
        DASH}

    /**
     * The Directionals.
     */
    boolean[] directionals = new boolean[Directionals.values().length];
    /**
     * The Actions.
     */
    boolean[] actions = new boolean[Actions.values().length];
    /**
     * The Abilities.
     */
    boolean[] abilities = new boolean[Abilities.values().length];

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

    /**
     * Sets ability.
     *
     * @param type  the type
     * @param state the state
     */
    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    /**
     * Get abilities boolean [ ].
     *
     * @return the boolean [ ]
     */
    public boolean[] getAbilities() {
        return abilities;
    }

}