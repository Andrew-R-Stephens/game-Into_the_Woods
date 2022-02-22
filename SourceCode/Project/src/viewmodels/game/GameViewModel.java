package viewmodels.game;

import data.PreferenceData;
import objects.types.pawn.APawn;
import objects.types.pawn.actor.Actor;

import java.awt.*;
import java.util.ArrayList;

public class GameViewModel {

    private PreferenceData preferences;

    private int frameInterpolationRate = PreferenceData.frameRate/PreferenceData.FRAMERATE_DEFAULT;

    private ArrayList<APawn> gameObjects = new ArrayList<>();

    public void init(PreferenceData preferences) {
        this.preferences = preferences;
    }

    public PreferenceData getPreferences() {
        return preferences;
    }

    public void addGameObject(APawn gameObject) {
        gameObjects.add(gameObject);
    }

    public ArrayList<APawn> getGameObjects() {
        return gameObjects;
    }

    public void updateGameObjects(double updateRate) {
        for(APawn gameObject: gameObjects) {
            if(gameObject instanceof Actor) {
                Actor e = (Actor)gameObject;
                e.update(updateRate);
            }
        }
    }

    public void updateGameObjects() {
        for(APawn gameObject: gameObjects) {
            if(gameObject instanceof Actor) {
                Actor e = (Actor)gameObject;
                e.update();
            }
        }
    }

    public void renderGameObjects(Graphics g) {
        for(APawn gameObject: gameObjects) {
            if(gameObject instanceof Actor) {
                Actor e = (Actor)gameObject;
                e.draw(g);
            }
        }
    }

}
