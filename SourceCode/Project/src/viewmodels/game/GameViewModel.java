package viewmodels.game;

import controls.GameMouseControls;
import data.PreferenceData;
import objects.actors.TestActor;
import objects.types.pawn.APawn;
import objects.types.pawn.actor.Actor;
import utils.MouseController;
import viewmodels.controls.ControlsViewModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameViewModel {

    private PreferenceData preferences;
    private ControlsViewModel controlsViewModel;

    private final ArrayList<APawn> gameObjects = new ArrayList<>();

    public void init(PreferenceData preferences, ControlsViewModel controlsViewModel) {
        this.preferences = preferences;
        this.controlsViewModel = controlsViewModel;
    }

    public PreferenceData getPreferences() {
        return preferences;
    }

    public void addGameObject(APawn gameObject) {
        gameObjects.add(gameObject);
    }

    public synchronized void update(double updateRate) {
        MouseController mouseController = controlsViewModel.getMouseController();
        if(mouseController instanceof GameMouseControls) {
            GameMouseControls gmc = (GameMouseControls)mouseController;
            if(gmc.isLeftPressed()) {
                addGameObject(
                    new TestActor(
                            gmc.getPos()[0],
                            gmc.getPos()[1],
                            10,
                            10,
                            new Random().nextFloat(-10, 10),
                            new Random().nextFloat(-10, 10),
                            -10, -10, 10, 10,
                            /*new Random().nextInt(0, 2) == 1*/ true, 1
                    )
                );
            }
        }

        updateGameObjects(updateRate);
    }

    public void updateGameObjects(double updateRate) {
        for(APawn gameObject: gameObjects) {
            if(gameObject instanceof TestActor) {
                TestActor a = (TestActor)gameObject;
                a.update(updateRate);
            }
        }
    }

    public synchronized void renderGameObjects(Graphics g) {
        for(APawn gameObject: gameObjects) {
            if(gameObject instanceof Actor) {
                Actor e = (Actor)gameObject;
                e.draw(g);
            }
        }
    }

}
