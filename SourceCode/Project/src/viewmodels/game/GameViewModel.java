package viewmodels.game;

import data.PreferenceData;
import game.objects.GameObject;
import game.objects.types.Entity;

import java.awt.*;
import java.util.ArrayList;

public class GameViewModel {

    private PreferenceData preferences;

    private int frameInterpolationRate = PreferenceData.frameRate/PreferenceData.FRAMERATE_DEFAULT;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public void init(PreferenceData preferences) {
        this.preferences = preferences;
    }

    public PreferenceData getPreferences() {
        return preferences;
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void updateGameObjects(double updateRate) {
        for(GameObject gameObject: gameObjects) {
            if(gameObject instanceof Entity) {
                Entity e = (Entity)gameObject;
                e.update(updateRate);
            }
        }
    }

    public void updateGameObjects() {
        for(GameObject gameObject: gameObjects) {
            if(gameObject instanceof Entity) {
                Entity e = (Entity)gameObject;
                e.update();
            }
        }
    }

    public void renderGameObjects(Graphics g) {
        for(GameObject gameObject: gameObjects) {
            if(gameObject instanceof Entity) {
                Entity e = (Entity)gameObject;
                e.draw(g);
            }
        }
    }

}
