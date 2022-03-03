package viewmodels.game;

import controls.GameMouseControls;
import data.PreferenceData;
import props.gameactors.TestActor;
import props.levelactors.TestLevelProp;
import proptypes.types.actor.AActor;
import proptypes.types.actor.pawn.APawn;
import utils.MouseController;
import viewmodels.controls.ControlsViewModel;

import java.awt.*;
import java.net.StandardSocketOptions;
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
        gameObjects.add(gameObject);
    }

    public synchronized void update(double delta) {
        // Mouse Input (Adding Game Objects)
        MouseController mouseController = controlsViewModel.getMouseController();
        if (mouseController instanceof GameMouseControls) {
            GameMouseControls gmc = (GameMouseControls) mouseController;
            if (gmc.isLeftPressed()) {
                int count = (int)(1/delta);
                if(count < 1) {
                    count = 1;
                }
                for(int i = 0; i < count; i++) {
                    addGameObject(
                        new TestActor(
                                (float)gmc.getPos()[0],
                                (float)gmc.getPos()[1],
                                20,
                                20,
                                new Random().nextFloat(-100, 100),
                                new Random().nextFloat(-100, 100),
                               true,
                               1
                        )
                    );
                }
            }
        }

        // Update the Game Objects
        updateGameObjects(delta);
    }

    public void updateGameObjects(double updateRate) {
        ArrayList<AActor> collisions = new ArrayList<>();
        float[] collisionDirs = new float[2];

        for(AActor gameObject: gameObjects) {
            TestActor a = null;
            if(gameObject instanceof TestActor) {
                a = (TestActor)gameObject;
                a.update(updateRate);
            }

            for(AActor otherGameObject: gameObjects) {
                if(otherGameObject != gameObject &&
                        gameObject instanceof TestActor) {
                    TestActor a2 = (TestActor)otherGameObject;
                    collisionDirs = a2.getCollisions(a);
                }
            }
            if(collisionDirs[0] != 0 || collisionDirs[1] != 0) {
                collisions.add(a);
            }
        }

        for(AActor ca: collisions) {
            ca.reverseVelocity(collisionDirs[0], collisionDirs[1]);
        }

        System.out.println(collisions.size() + " / " + gameObjects.size());
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
