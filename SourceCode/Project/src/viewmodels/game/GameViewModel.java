package viewmodels.game;

import data.PreferenceData;
import game.objects.GameObject;

import java.util.ArrayList;

public class GameViewModel {

    private PreferenceData preferences;

    private ControlsViewModel controlsViewModel;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public void setControlsViewModel(ControlsViewModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public GameObject getGameObject(int i) {
        if(gameObjects == null || gameObjects.size() == 0 || gameObjects.size() < i)
            return null;

        return gameObjects.get(i);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void init(PreferenceData preferences, ControlsViewModel controlsViewModel) {
        this.preferences = preferences;
    }

    public PreferenceData getPreferences() {
        return preferences;
    }


}
