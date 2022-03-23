package models.environments.game;

import models.environments.EnvironmentsModel;
import models.camera.Camera;
import models.controls.GameControlsModel;
import models.controls.game.GameMouseControls;
import models.data.PreferenceData;
import models.environments.game.hud.HUDModel;
import props.objects.gameactors.TestActor;
import props.objects.gameactors.TestCharacter;
import props.objects.levels.LevelList;
import prototypes.actor.AActor;
import prototypes.actor.pawn.character.ACharacter;
import prototypes.level.prop.ALevelProp;
import prototypes.window.environments.AEnvironment;

import java.awt.*;
import java.util.*;

/**
 * The Game Model derives from the AEnvironment class. It's what encapsulates all data that the Game requires to run.
 * Relevant data includes: Levels, Level Objects, Entity Objects, Player HUD
 */
public class GameModel extends AEnvironment {

    private HUDModel hudModel = new HUDModel(this);
    private boolean isPaused = false;

    private LevelList levelModel;

    private final ArrayList<AActor> actors = new ArrayList<>();
    private Queue<AActor> actorsQueue = new LinkedList<>();

    private boolean isGc = false;

    /**
     * Initializes Game Model
     *
     * @param parentEnvironmentsModel - Contains references to the GameModel and MenuModel Environments
     * @param controlsViewModel       - The controls for the Game
     * @param levelModel              - Contains a list of all possible levels
     */
    public void init(EnvironmentsModel parentEnvironmentsModel, GameControlsModel controlsViewModel, LevelList levelModel) {

        super.init(parentEnvironmentsModel, controlsViewModel.getKeyController(), controlsViewModel.getMouseController());


        setLevelModel(levelModel);

        // Add in the Main Test Character
        actors.add(new TestCharacter(
                controlsViewModel,
                200, 50,
                55, 70,
                0, 0,
                true
        ));
    }


    @Override
    public void update(float delta) {

        // ======
        // Performance testing by adding Numerous Objects via Mouse Input
        testAddingActors(delta);
        // ======

        // Check Game Object Collisions with Level Props
        checkCollisions(delta);
        // Update the Game Objects
        updateGameObjects(delta);

        // Update HUD overlay
        updateHUD(delta);

        while(!actorsQueue.isEmpty()) {
            addGameObject(actorsQueue.remove());
        }
    }

    /**
     *  Only deals with draw calls of all objects contained within the Game Model
     */
    @Override
    public void draw(Graphics g) {
        // Render Game Actors
        for (AActor gameObject : actors) {
            if (gameObject instanceof TestCharacter o) {
                o.draw(g);
            }

            if (gameObject instanceof TestActor o) {
                o.draw(g);
            }
        }

        // Render Level Props
        levelModel.draw(g);

        if(!isPaused) {
            hudModel.draw(g);
        }

    }


    /**
     * Sets the List of Levels into this local scope.
     *
     * @param levelModel - The Level Model that contains all levels
     */
    public void setLevelModel(LevelList levelModel) {
        this.levelModel = levelModel;
    }

    /**
     * Add game object.
     *
     * @param actor - The actor added to the list of current Game Objects
     */
    public void addGameObject(AActor actor) {
        actors.add(actor);
    }

    /**
     * Updates the Player HUD to reflect specific information contained within Game Model
     * @param delta
     */
    private void updateHUD(float delta) {
        hudModel.update(delta);
    }

    /**
     * Adds new TestActor objects to a holding Queue.
     * The holding Queue will inject these objects at the next available juncture.
     *
     * @param delta - The ratio of current framerate against standard update frequency
     */
    private synchronized void testAddingActors(float delta) {
        if (mouseController instanceof GameMouseControls) {

            GameMouseControls gmc = (GameMouseControls) mouseController;

            if (gmc.isLeftPressed()) {
                int count = (int) (10 / delta);
                if (count < 1) {
                    count = 1;
                }
                for (int i = 0; i < count; i++) {
                    queueAddGameObject(
                            new TestActor(
                                    (-Camera.x /PreferenceData.scaledW) + (gmc.getPos()[0]/PreferenceData.scaledW),
                                    (-Camera.y /PreferenceData.scaledW) + (gmc.getPos()[1]/PreferenceData.scaledH),
                                    10f,
                                    10f,
                                    new Random().nextFloat(-5, 5),
                                    new Random().nextFloat(-5, 5),
                                    true
                            )
                    );
                }
            }
        }
    }

    /**
     * Updates all game objects.
     *
     * @param delta - The ratio of current framerate against standard update frequency
     */
    public void updateGameObjects(float delta) {

        // Update all Actors
        for (AActor gameObject : actors) {

            // Update TestActors
            if (gameObject instanceof TestActor a) {
                a.update(delta);
            }

            // Update Characters
            if (gameObject instanceof TestCharacter tc) {
                tc.control(delta);
                tc.update(delta);
            }

        }

    }

    /**
     * Check Level Prop object collisions against all AActor objects
     * @param delta - The ratio of current framerate against standard update frequency
     */
    private void checkCollisions(float delta) {
        for (ALevelProp p : levelModel.getLevelProps()) {
            for (AActor a : actors) {
                p.hasCollision(a, delta);
            }
        }
    }

    /**
     * Attempt at Garbage collection
     * Should be used to destroy objects that are old or aren't being utilized.
     * Might remove later.
     */
    public void gc() {
        /*
        if(!isGc) {
            isGc = true;
            for (int i = 0; i < 1000; i++) {
                AActor a = actors.get(i);
                if (!(a instanceof ALevelProp) && !(a instanceof ACharacter)) {
                    actors.remove(a);
                }
            }
            isGc = false;
        }
        */
    }

    /**
     * Used to temporarily store new game objects into a Linked List Queue.
     *
     * @param a the AActor to add
     */
    public void queueAddGameObject(AActor a) {
        actorsQueue.add(a);
    }

    public void setCurrentLevel(int levelIndex) {
        levelModel.setCurrentLevel(levelIndex);
    }
}
