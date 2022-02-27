package viewmodels.game;

import controls.GameMouseControls;
import data.PreferenceData;
import objects.actors.gameactors.TestGameActor;
import objects.types.actor.AActor;
import objects.types.actor.pawn.APawn;
import utils.MouseController;
import viewmodels.controls.ControlsViewModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameViewModel {

    private PreferenceData preferences;
    private ControlsViewModel controlsViewModel;

    private final ArrayList<AActor> gameObjects = new ArrayList<>();

    public void init(PreferenceData preferences, ControlsViewModel controlsViewModel) {
        this.preferences = preferences;
        this.controlsViewModel = controlsViewModel;
    }

    public PreferenceData getPreferences() {
        return preferences;
    }

    public void addGameObject(AActor gameObject) {
        /*
        if(gameObjects.size() - 10000 > 0) {
            gameObjects.subList(0, 10).clear();
            System.out.println("Removing");
        }
        */
        gameObjects.add(gameObject);
        //System.out.println(gameObjects.size());

    }

    public synchronized void update(double updateRate) {
        MouseController mouseController = controlsViewModel.getMouseController();
        if (mouseController instanceof GameMouseControls) {
            GameMouseControls gmc = (GameMouseControls) mouseController;
            if (gmc.isLeftPressed()) {
                for (int i = 0; i < 10; i++) {
                    addGameObject(
                            new TestGameActor(
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
        }

        updateGameObjects(updateRate);
    }

    public void updateGameObjects(double updateRate) {
        for(AActor gameObject: gameObjects) {
            if(gameObject instanceof TestGameActor) {
                TestGameActor a = (TestGameActor)gameObject;
                a.update(updateRate);
            }
        }
    }

    public synchronized void renderGameObjects(Graphics g) {
        for(AActor gameObject: gameObjects) {
            if(gameObject instanceof APawn) {
                APawn e = (APawn)gameObject;
                e.draw(g);
            }
        }
    }

}
