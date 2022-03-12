package viewmodels.game;

import controls.game.GameMouseControls;
import data.PreferenceData;
import props.gameactors.TestActor;
import props.gameactors.TestCharacter;
import proptypes.actors.levelactors.animated.ALevelProp;
import proptypes.types.actor.AActor;
import utils.AMouseController;
import viewmodels.controls.ControlsModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * TODO: Add description
 */
public class GameModel {

    private ControlsModel controlsViewModel;

    private Camera camera = new Camera();

    private LevelModel levelModel = new LevelModel();

    private final ArrayList<AActor> gameObjects = new ArrayList<>();

    public void init(ControlsModel controlsViewModel, LevelModel levelModel) {

        setControlsViewModel(controlsViewModel);
        setLevelModel(levelModel);

        // Main Test Character
        gameObjects.add(new TestCharacter(
                controlsViewModel,
                200, 50,
                25, 50,
                0, 0,
                true
        ));
    }

    public void setControlsViewModel(ControlsModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    public void setLevelModel(LevelModel levelModel) {
        this.levelModel = levelModel;
    }


    public void addGameObject(AActor gameObject) {
        gameObjects.add(gameObject);
    }


    public synchronized void update(float delta) {

        // Mouse Input (Adding Game Objects)
        AMouseController mouseController = controlsViewModel.getMouseController();
        if (mouseController instanceof GameMouseControls) {

            GameMouseControls gmc = (GameMouseControls) mouseController;

            if (gmc.isLeftPressed()) {
                int count = (int) (10 / delta);
                if (count < 1) {
                    count = 1;
                }
                for (int i = 0; i < count; i++) {
                    addGameObject(
                        new TestActor(
                            (float) ((-Camera.x /PreferenceData.scaledW) + (gmc.getPos()[0]/PreferenceData.scaledW)),
                            (float) ((-Camera.y /PreferenceData.scaledW) + (gmc.getPos()[1]/PreferenceData.scaledH)),
                            50f,
                            50f,
                            new Random().nextFloat(-5, 5),
                            new Random().nextFloat(-5, 5),
                            true
                        )
                    );
                }
            }
        }

        // Check Game Object Collisions with Level Props
        checkCollisions(delta);
        // Update the Game Objects
        updateGameObjects(delta);


    }

    public void updateGameObjects(float delta) {

        // Update all Actors
        for (AActor gameObject : gameObjects) {

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

    private void checkCollisions(float delta) {
        for (ALevelProp p : levelModel.getLevelProps()) {
            for (AActor a : gameObjects) {
                p.hasCollision(a, delta);
            }
        }
    }

    public synchronized void renderGameObjects(Graphics g) {
        for (AActor gameObject : gameObjects) {
            if (gameObject instanceof TestCharacter o) {
                o.draw(g);
            }
            if (gameObject instanceof TestActor o) {
                o.draw(g);
            }
        }

        for (AActor levelProps : levelModel.getLevelProps()) {
            if (levelProps instanceof ALevelProp p) {
                p.draw(g);
            }
        }
    }
}
