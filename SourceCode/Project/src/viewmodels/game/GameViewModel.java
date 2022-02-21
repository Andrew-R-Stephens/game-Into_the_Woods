package viewmodels.game;

import files.PreferenceData;

public class GameViewModel {

    private PreferenceData preferences;

    public GameViewModel() {

    }

    public void init(PreferenceData preferences) {
        this.preferences = preferences;
    }

    public PreferenceData getPreferences() {
        return preferences;
    }
}
