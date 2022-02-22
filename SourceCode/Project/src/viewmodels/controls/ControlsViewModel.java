package viewmodels.controls;

import controls.GameKeyControls;
import controls.GameMouseControls;
import utils.KeyController;
import utils.MouseController;
import data.PreferenceData;
import viewmodels.game.GameViewModel;

public class ControlsViewModel {

    private PreferenceData preferenceData;
    private GameViewModel gameViewModel;

    private KeyController keyController;
    private MouseController mouseController;

    public void init(PreferenceData preferences, GameViewModel gameViewModel) {
        this.preferenceData = preferences;
        this.gameViewModel = gameViewModel;

        mouseController = new GameMouseControls(gameViewModel);
        keyController = new GameKeyControls(gameViewModel);
    }

    public enum Directionals {LEFT, RIGHT, UP, DOWN};
    enum Abilities {JUMP, DASH};

    boolean[] directionals = new boolean[Directionals.values().length];
    boolean[] abilities = new boolean[Abilities.values().length];

    public KeyController getKeyController() {
        return keyController;
    }

    public MouseController getMouseController() {
        return mouseController;
    }

    public void setDirectional(Directionals type, boolean state) {
        directionals[type.ordinal()] = state;
    }

    public boolean getDirectional(Directionals type) {
        return directionals[type.ordinal()];
    }

    public void setAbility(Abilities type, boolean state) {
        abilities[type.ordinal()] = state;
    }

    public boolean getAbility(Abilities type) {
        return abilities[type.ordinal()];
    }

}
