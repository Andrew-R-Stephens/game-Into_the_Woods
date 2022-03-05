package viewmodels.game;

import controls.GameMouseControls;
import props.gameactors.TestActor;
import props.gameactors.TestCharacter;
import props.levelactors.TestLevelPropStatic;
import proptypes.actors.levelactors.animated.ALevelProp;
import proptypes.types.actor.AActor;
import utils.MouseController;
import viewmodels.controls.ControlsModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameModel {

    private ControlsModel controlsViewModel;

    private final ArrayList<AActor> gameObjects = new ArrayList<>();
    private final ArrayList<ALevelProp> levelProps = new ArrayList<>();

    public void init(ControlsModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;

        // Main Test Character
        gameObjects.add(new TestCharacter(
                200,200,
                100,100,
                0,0,
                true,
                1f
        ));

        // Wall
        levelProps.add(new TestLevelPropStatic(0 , 0, 100, 1080, 0, 0, false, 0));
        // Floor
        levelProps.add(new TestLevelPropStatic(0 , 980, 10000, 100, 0, 0, false, 0));

        // Other Props
        levelProps.add(new TestLevelPropStatic(1800 , 100, 500, 100, 0, 0, false, 0));
        levelProps.add(new TestLevelPropStatic(70 , 800, 500, 100, 0, 0, false, 0));
        levelProps.add(new TestLevelPropStatic(500 , 700, 500, 100, 0, 0, false, 0));
        levelProps.add(new TestLevelPropStatic(1100 , 600, 500, 100, 0, 0, false, 0));
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
                                50f,
                                50f,
                                new Random().nextFloat(-100, 100),
                                new Random().nextFloat(-100, 100),
                               true,
                               1f
                        )
                    );
                    addGameObject(
                            new TestCharacter(
                                    (float)gmc.getPos()[0],
                                    (float)gmc.getPos()[1],
                                    50f,
                                    50f,
                                    new Random().nextFloat(-10, 10),
                                    new Random().nextFloat(-10, 10),
                                    true,
                                    1f
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
                tc.control(controlsViewModel.getDirectionals(), controlsViewModel.getAbilities());
                tc.update(updateRate);
            }

        }

    }

    private void checkCollisions() {
        for(ALevelProp p: levelProps) {
            for(AActor a: gameObjects) {
                if(a.isInFrameBounds()) {
                    p.hasCollision(a);
                }
            }
        }
    }

    public synchronized void renderGameObjects(Graphics g) {
        for(AActor gameObject: gameObjects) {
            if(gameObject instanceof TestCharacter o) {
                if(o.isInFrameBounds()) {
                    o.draw(g);
                }
            }
            if(gameObject instanceof TestActor o) {
                if(o.isInFrameBounds()) {
                    o.draw(g);
                }
            }
        }

        for(AActor levelProps : levelProps) {
            if(levelProps instanceof ALevelProp p) {
                if(p.isInFrameBounds()) {
                    p.draw(g);
                }
            }
        }
    }

}
