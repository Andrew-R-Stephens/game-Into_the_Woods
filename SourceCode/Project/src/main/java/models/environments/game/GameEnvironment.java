package models.environments.game;

import models.camera.Camera;
import models.controls.GameControlsModel;
import models.controls.game.GameKeyControls;
import models.controls.game.GameMouseControls;
import models.controls.menu.MenuKeyControls;
import models.environments.EnvironmentsHandler;
import models.environments.game.hud.HUDModel;
import models.environments.game.playerinventory.PlayerInventory;
import models.environments.menus.pausemenumodel.PauseMenuModel;
import props.objects.gameactors.PlayerAvatar;
import props.objects.gameactors.TestActor;
import props.objects.levels.LevelsList;
import prototypes.actor.AActor;
import prototypes.actor.pawn.character.ACharacter;
import prototypes.level.ALevel;
import prototypes.level.prop.ALevelProp;
import prototypes.window.environments.AEnvironment;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * The Game Model derives from the AEnvironment class. It's what encapsulates all data that the Game requires to run.
 * Relevant data includes: Levels, Level Objects, Entity Objects, Player HUD
 */
public class GameEnvironment extends AEnvironment {

    private PauseMenuModel pauseMenuModel;

    private LevelsList levelModel;

    private HUDModel hudModel;
    private PlayerInventory inventory;

    private final ArrayList<AActor> actors = new ArrayList<>();
    private final Queue<AActor> actorsQueue = new LinkedList<>();

    private PlayerAvatar character;

    private boolean isPaused = false;

    /**
     * Initializes Game Model
     *  @param parentEnvironmentsHandler - Contains references to the GameModel and MenuModel Environments
     * @param pauseMenuModel
     * @param controlsModel       - The controls for the Game
     * @param levelModel              - Contains a list of all possible levels
     */
    public void init(EnvironmentsHandler parentEnvironmentsHandler,
                     PauseMenuModel pauseMenuModel, GameControlsModel controlsModel,
                     LevelsList levelModel, HUDModel hudModel, PlayerInventory inventory) {

        super.init(parentEnvironmentsHandler, controlsModel.getKeyController(),
                controlsModel.getMouseController());

        setPauseMenuModel(pauseMenuModel);
        setLevelModel(levelModel);
        setHUDModel(hudModel);
        setPlayerInventory(inventory);

        build(controlsModel);
    }

    private void setPlayerInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    private void setHUDModel(HUDModel hudModel) {
        this.hudModel = hudModel;
    }

    private void setPauseMenuModel(PauseMenuModel pauseMenuModel) {
        this.pauseMenuModel = pauseMenuModel;
    }

    public void build(GameControlsModel controlsModel) {
        setPlayerAvatar(controlsModel, levelModel);
    }

    @Override
    public void update(float delta) {

        if(!isPaused) {
            doGameUpdates(delta);
        } else {
            doPauseMenuUpdates(delta);
        }

    }

    /**
     *  Only deals with draw calls of all objects contained within the Game Model
     */
    @Override
    public void draw(Graphics g) {

        // Render Level Props
        levelModel.draw(g);

        // Render Game Actors
        for (AActor gameObject : actors) {
            gameObject.draw(g);
        }

        if(!isPaused) {
            hudModel.draw(g);
        }

        if(isPaused) {
            //Draw Pause Menu
            pauseMenuModel.draw(g);
        }
    }

    public void doGameUpdates(float delta) {

        doGameControls();

        //testAddingActors(delta); // TODO: Delete this (testing purposes only)

        detectCollisions(delta); // Check Game Object Collisions with Level Props

        insertQueuedActors(); // Dequeue queued actors and add them to list of actors

        updateActors(delta); // Update the Game Objects

        updateLevel(delta); // Update level props

        updateHUD(delta); // Update HUD overlay
    }

    public void doPauseMenuUpdates(float delta) {
        doPauseMenuControls();

        pauseMenuModel.update(delta);
    }

    private void doGameControls() {
        if(keyController instanceof GameKeyControls kc) {
            if(kc.getControlsModel().getAction(GameControlsModel.Actions.ESCAPE)) {
                kc.getControlsModel().resetAction(GameControlsModel.Actions.ESCAPE);
                isPaused = true;
                parentEnvironmentsModel.swapToEnvironmentType(
                        EnvironmentsHandler.EnvironmentType.GAME_PAUSE_MENU, false).applyEnvironment();
            }
        }
    }

    public void doPauseMenuControls() {
        if(pauseMenuModel.getKeyController() instanceof MenuKeyControls kc) {
            if(kc.getControlsModel().getAction(GameControlsModel.Actions.ESCAPE)) {
                kc.getControlsModel().resetAction(GameControlsModel.Actions.ESCAPE);
                isPaused = false;
                parentEnvironmentsModel.swapToEnvironmentType(
                        EnvironmentsHandler.EnvironmentType.GAME, false).applyEnvironment();
            }
        }
    }

    /**
     * Sets the List of Levels into this local scope.
     *
     * @param levelModel - The Level Model that contains all levels
     */
    public void setLevelModel(LevelsList levelModel) {
        this.levelModel = levelModel;
    }

    /**
     * setPlayerAvatar
     *
     * Obtains the appropriate start location for the player based on the current Level.
     *
     * @param controlsViewModel
     * @param levelModel
     */
    private void setPlayerAvatar(GameControlsModel controlsViewModel, LevelsList levelModel) {
        int[] startPos = levelModel.getCurrentLevel().getCharacterOrigin();
        // Add in the Main Test Character

        character = new PlayerAvatar (
                controlsViewModel,
                startPos[0],
                startPos[1],
                55, 70,
                0, 0,
                true
        );

    }


    /**
     * Updates the Player HUD to reflect specific information contained within Game Model
     * @param delta
     */
    private void updateHUD(float delta) {
        hudModel.update(delta);
    }

    private void updateLevel(float delta) {
        levelModel.getCurrentLevel().update(delta);
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
                    queueActor(
                            new TestActor(
                                    (-Camera.x / ConfigData.scaledW) + (gmc.getPos()[0]/ ConfigData.scaledW),
                                    (-Camera.y / ConfigData.scaledW) + (gmc.getPos()[1]/ ConfigData.scaledH),
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
    public void updateActors(float delta) {

        // Update all Actors
        for (AActor gameObject : actors) {

            // Update TestActors
            if (gameObject instanceof TestActor a) {
                a.update(delta);
            }

            // Update Characters
            if (gameObject instanceof PlayerAvatar tc) {
                tc.control(delta);
                tc.update(delta);
                System.out.println(tc.actionState);
            }

        }

    }

    /**
     * Check Level Prop object collisions against all AActor objects
     * @param delta - The ratio of current framerate against standard update frequency
     */
    private void detectCollisions(float delta) {
        for (ALevelProp p : levelModel.getCurrentLevel().getLevelProps()) {
            for (AActor a : actors) {
                p.hasCollision(a, delta);
            }
        }
    }

    private void insertQueuedActors() {
        for(int i = 0; i < 10 && actorsQueue.size() >= 1; i++) {
            addActor(actorsQueue.remove());
        }
    }

    /**
     * Used to temporarily store new game objects into a Linked List Queue.
     *
     * @param a the AActor to add
     */
    public void queueActor(AActor a) {
        actorsQueue.add(a);
    }

    /**
     * Add game object.
     *
     * @param actor - The actor added to the list of current Game Objects
     */
    public void addActor(AActor actor) {
        actors.add(actor);
    }

    /**
     * Sets current level.
     *
     * @param levelIndex the level index
     */
    public void setCurrentLevel(int levelIndex) {
        levelModel.setCurrentLevel(levelIndex);
    }

    public ALevel getCurrentLevel() {
        return levelModel.getCurrentLevel();
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public ACharacter getPlayerAvatar() {
        return character;
    }

    @Override
    public void startBackgroundAudio() {
        audioPlayer = Resources.playAudio_Player("game");
    }

    @Override
    public void reset() {
        hudModel.reset();
        inventory.reset();
        actors.clear();
        actors.add(character);
        character.reset(levelModel.getCurrentLevel().getCharacterOrigin());
        levelModel.reset();
    }

    public PlayerInventory getPlayerInventory() {
        return inventory;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
