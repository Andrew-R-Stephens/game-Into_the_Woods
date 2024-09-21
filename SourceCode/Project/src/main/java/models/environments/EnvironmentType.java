package models.environments;

    /**
     * <p>The EnvironmentType is responsible for simply giving enumeration to the five types of environments, which
     * allows a more verbose representation of desired requests when switching environments.</p>
     */
    public enum EnvironmentType {
        MAIN_MENU,
        GAME,
        GAME_PAUSE_MENU,
        EDITOR,
        EDITOR_PAUSE_MENU
    }