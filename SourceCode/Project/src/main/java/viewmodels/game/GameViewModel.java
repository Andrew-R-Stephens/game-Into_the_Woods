package viewmodels.game;

import controls.GameMouseControls;
import data.PreferenceData;
import props.gameactors.TestActor;
import proptypes.types.actor.AActor;
import proptypes.types.actor.pawn.APawn;
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
                for(int i = 0; i < 10; i++) {
                    float s = new Random().nextFloat(10, 100);
                    addGameObject(
                            new TestActor(
                                    gmc.getPos()[0],
                                    gmc.getPos()[1],
                                    s,
                                    s,
                                    new Random().nextFloat(-100, 100),
                                    new Random().nextFloat(-100, 100),
                                    -50, -50, 50, 50,
                                    true, new Random().nextFloat(1, 10)
                            )

                    );
                }
            }
        }

        updateGameObjects(updateRate);
    }

    public void updateGameObjects(double updateRate) {
        for(AActor gameObject: gameObjects) {
            if(gameObject instanceof TestActor) {
                TestActor a = (TestActor)gameObject;
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
