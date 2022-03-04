package viewmodels.game;

import controls.GameMouseControls;
import data.PreferenceData;
import props.gameactors.TestActor;
import props.gameactors.TestCharacter;
import props.levelactors.TestLevelPropStatic;
import proptypes.actors.levelactors.animated.ALevelProp;
import proptypes.types.actor.AActor;
import proptypes.types.actor.pawn.APawn;
import utils.MouseController;
import viewmodels.controls.ControlsViewModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameModel {

    private ControlsViewModel controlsViewModel;

    private final ArrayList<AActor> gameObjects = new ArrayList<>();
    private final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    public void init(ControlsViewModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;

        levelProps.add(new TestLevelPropStatic(300 , 200, 300, 100, 0, 0, false, 0));
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
                    /*addGameObject(
                        new TestActor(
                                (float)gmc.getPos()[0],
                                (float)gmc.getPos()[1],
                                10,
                                10,
                                new Random().nextFloat(-100, 100),
                                new Random().nextFloat(-100, 100),
                               true,
                               1
                        )
                    );*/
                    addGameObject(
                            new TestCharacter(
                                    (float)gmc.getPos()[0],
                                    (float)gmc.getPos()[1],
                                    10,
                                    10,
                                    /*new Random().nextFloat(-10, 10),
                                    new Random().nextFloat(-50, 10),*/
                                    0, 0,
                                    true,
                                    1
                            )
                    );
                }
            }
        }

        // Update the Game Objects
        updateGameObjects(delta);

        // Check Game Object Collisions with Level Props
        checkCollisions();
    }

    public void updateGameObjects(double updateRate) {

        // Update all Actors
        for(AActor gameObject: gameObjects) {

            // Update TestActors
            if(gameObject instanceof TestActor a) {
                a.update(updateRate);
            }

            // Update Characters
            if(gameObject instanceof TestCharacter tc) {
                tc.move(controlsViewModel.getDirectionals());
                tc.update(updateRate);
            }

        }

    }

    private void checkCollisions() {
        for(ALevelProp p: levelProps) {
            for(AActor a: gameObjects) {
                if(a.isInBounds()) {
                    float[] c = a.getCollisions(p);
                    System.out.println(c[0] + " " + c[1] + " " + c[2] + " " + c[3]);
                    a.doCollision(c);
                }
            }
        }
    }

    public synchronized void renderGameObjects(Graphics g) {
        for(AActor gameObject: gameObjects) {
            if(gameObject instanceof APawn) {
                APawn e = (APawn)gameObject;
                if(e.isInBounds()) {
                    e.draw(g);
                }
            }
        }

        for(AActor levelProps : levelProps) {
            if(levelProps instanceof ALevelProp p) {
                if(p.isInBounds()) {
                    p.draw(g);
                }
            }
        }
    }

}
