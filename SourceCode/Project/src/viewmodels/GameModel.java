package viewmodels;

import files.Preferences;

public class GameModel {

    private Preferences preferences;

    public GameModel() {

    }

    public void init(Preferences preferences) {
        this.preferences = preferences;
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
