package models.environments.game;

import controls.game.GameControls;
import controls.game.GameKeyControls;
import controls.game.GameMouseControls;
import controls.menu.MenuControls;
import controls.menu.MenuKeyControls;
import models.actors.particles.Particle;
import models.actors.player.PlayerAvatar;
import models.camera.Camera;
import models.environments.EnvironmentsHandler;
import models.environments.game.hud.HUDModel;
import models.environments.game.playerinventory.PlayerInventory;
import models.environments.menus.pausemenumodel.PauseMenuEnvironment;
import models.levels.LevelsList;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * <p>GameEnvironment class is an AEnvironment subtype that controls the Game Environment and its contained entities.</p>
 * <p>Controls the PauseMenuEnvironment when the GameEnvironment is paused.</p>
 * <p>Contains the GameControls, Levels, Game HUD, Player Inventory, and all Actors.</p>
 * @author Andrew Stephens
 */
public class GameEnvironment extends AEnvironment implements IDrawable, IUpdatable {

    /**<p>The list of actors currently active within the current level.</p>*/
    private final ArrayList<AActor> actors = new ArrayList<>();
    /**<p>The to-add actors that will be added to the list of actors when available.</p>*/
    private final Queue<AActor> actorsQueue = new LinkedList<>();
    /**<p>The Game Controls for the Game Environment</p>*/
    private GameControls gameControls;
    /**<p>The PauseMenuEnvironment that is used for the pause state.</p>*/
    private PauseMenuEnvironment pauseMenuEnvironment;
    /**<p>The list of Levels that the user will navigate between.</p>*/
    private LevelsList levelsList;
    /**<p>The heads-up-display containing all overlays.</p>*/
    private HUDModel hudModel;
    /**<p>The player's inventory of collected items.</p>*/
    private PlayerInventory inventory;
    /**<p>The Player Avatar model</p>*/
    private PlayerAvatar character;

    /**<p>If the game is paused</p>*/
    private boolean isPaused = false;
    /**
     * If the game is awaiting reset
     */
    private boolean isAwaitingReset = false;
    /**
     * The max timeout for death
     */
    private final long DEATH_TIMEOUT = 2000L;
    /**
     * The time of death.
     */
    private long deathTimeoutStart = -1L;
    /**<p>The Robot which keeps the mouse centered in the window in the unpaused game.</p>*/
    private Robot robot;

    /**
     * <p>Initializes the GameEnvironment with references to preconfigured object.</p>
     * @param parentEnvironmentsHandler The encapsulating EnvironmentsHandler. Cannot be null.
     * @param pauseMenuEnvironment The PauseMenuEnvironment. Cannot be null.
     * @param gameControls The GameControls. Cannot be null.
     * @param levelsList The LevelsList. Cannot be null.
     * @param hudModel The HUDModel. Cannot be null.
     * @param inventory The PlayerInventory. Cannot be null.
     */
    public void init(EnvironmentsHandler parentEnvironmentsHandler,
                     PauseMenuEnvironment pauseMenuEnvironment, GameControls gameControls,
                     LevelsList levelsList, HUDModel hudModel, PlayerInventory inventory) {

        super.init(parentEnvironmentsHandler, gameControls);

        setAudioPlayer();

        this.gameControls = gameControls;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        setPauseMenuEnvironment(pauseMenuEnvironment);
        setLevelsList(levelsList);
        setHUDModel(hudModel);
        setPlayerInventory(inventory);

        build(gameControls);
    }

    /**
     * <p>Sets the preconfigured HUD for the Game Environment.</p>
     * @param hudModel The HUD Model for the Overlays. Should not be null.
     */
    private void setHUDModel(HUDModel hudModel) {
        this.hudModel = hudModel;
    }

    /**
     * <p>Sets the PauseMenuEnvironment for the paused state of the Game Environment.</p>
     * @param pauseMenuEnvironment The PauseMenuEnvironment used for the paused Game state. Cannot be null.
     */
    private void setPauseMenuEnvironment(PauseMenuEnvironment pauseMenuEnvironment) {
        this.pauseMenuEnvironment = pauseMenuEnvironment;
    }

    /**
     * <p>The LevelsList that contains all of the Levels in the game.</p>
     * @param levelsList The list of levels and any other level data.
     */
    public void setLevelsList(LevelsList levelsList) {
        this.levelsList = levelsList;
    }

    /**
     * <p></p>
     * @param controlsViewModel The ControlsModel that the actor should see.
     * @param levelModel The LevelsList that the actor should see.
     */
    private void setPlayerAvatar(GameControls controlsViewModel, LevelsList levelModel) {
        int[] startPos = levelModel.getCurrentLevel().getCharacterOrigin();
        // Add in the Main Test Character

        character = new PlayerAvatar (
                getResources(),
                controlsViewModel,
                startPos[0],
                startPos[1],
                55, 70,
                0, 0,
                true
        );

    }

    /**
     * <p>Sets the current level by the use of index.</p>
     * @param levelIndex The index of the level. Zero-based.
     */
    public void setCurrentLevel(int levelIndex) {
        levelsList.setCurrentLevel(levelIndex);
    }

    /**
     * <p>Sets a preconfigured player inventory into the Game Environment.</p>
     * @param inventory The inventory of the player. Should not be null.
     */
    private void setPlayerInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * <p>Sets the game environment to paused.</p>
     * @param paused If the game should be paused.
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    /**
     * <p>Builds the environment with a new Player Avatar and pairs the controls and Levels List  directly with the
     * Player.</p>
     * @param controlsModel The controls model that should control the PlayerAvatar.
     */
    public void build(GameControls controlsModel) {
        setPlayerAvatar(controlsModel, levelsList);
    }

    /**
     * <p>Updates the objects within the Game state. Controls the game objects, does collisions, updates positions, and
     * updates the Overlay.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void doGameUpdates(float delta) {

        doGameControls();
        insertQueuedActors(); // Dequeue queued actors and add them to list of actors
        detectCollisions(delta); // Check Game Object Collisions with Level Props
        updateActors(delta); // Update the Game Objects
        updateLevel(delta); // Update level models.props
        updateHUD(delta); // Update HUD overlay

        if(isAwaitingReset) {
            if(deathTimeoutStart < System.currentTimeMillis() - DEATH_TIMEOUT) {
                reset();
            }
        }
    }

    /**
     * <p>Controls the pause state of the Environment. Switches to when game is paused.
     * Allows for use and visuals of pause menu controls.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void doPauseMenuUpdates(float delta) {
        doPauseMenuControls();

        pauseMenuEnvironment.update(delta);
    }

    /**
     * <p>Listens to Game Controller and produces the requested actions from Key inputs for the game state.</p>
     */
    private void doGameControls() {
        robot.mouseMove(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * .5f),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * .5f)
        );

        if(getKeyController() instanceof GameKeyControls kc) {
            if(kc.getControlsModel().getAction(GameControls.Actions.ESCAPE)) {
                kc.getControlsModel().reset();
                setPaused(true);
                getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME_PAUSE_MENU, false).applyEnvironment();
            }
        }
    }

    /**
     * <p>Listens to Menu Controller and produces the requested actions from Key inputs for the pause state.</p>
     */
    public void doPauseMenuControls() {
        if(pauseMenuEnvironment.getKeyController() instanceof MenuKeyControls kc) {
            if(kc.getControlsModel().getAction(MenuControls.Actions.ESCAPE)) {
                kc.getControlsModel().reset();
                setPaused(false);
                pauseMenuEnvironment.onExit();
                getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME, false).applyEnvironment();
            }
        }
    }

    /**
     * <p>Gets the LevelsList object which contains all loaded Levels.</p>
     * @return The list of all created levels.
     */
    public LevelsList getLevelsList() {
        return levelsList;
    }

    /**
     * <p>Updates components within the in-game HUD overlay.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void updateHUD(float delta) {
        hudModel.update(delta);
    }

    /**
     * <p>Updates components the currently active level.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void updateLevel(float delta) {
        levelsList.getCurrentLevel().update(delta);
    }

    /**
     * <p>Adds test actors into the level. This is solely used for performance and behavior testing.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private synchronized void testAddingActors(float delta) {
        if (getMouseController() instanceof GameMouseControls gmc) {

            if (gmc.isLeftPressed()) {
                int count = (int) (10 / delta);
                if (count < 1) {
                    count = 1;
                }
                for (int i = 0; i < count; i++) {
                    queueActor(
                            new Particle(
                                    getResources(),
                                    (-Camera.camX / Config.scaledW_zoom) + (gmc.getPos()[0]/ Config.scaledW_zoom),
                                    (-Camera.camY / Config.scaledW_zoom) + (gmc.getPos()[1]/ Config.scaledH_zoom),
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
     * <p>Updates all actor objects within the current level.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void updateActors(float delta) {

        // Update all Actors
        for (AActor gameObject : actors) {

            // Update TestActors
            if (gameObject instanceof Particle a) {
                a.update(delta);
            }

            // Update Characters
            if (gameObject instanceof PlayerAvatar tc) {
                if(!isAwaitingReset) {
                    tc.control(delta);
                    tc.update(delta);
                }
                //System.out.println(tc.actionState);
            }
        }
    }

    /**
     * <p>Detects the collisions of platforms with actors. Resolves the collisions within the actor and platform
     * objects themselves.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void detectCollisions(float delta) {
        for (AProp p : levelsList.getCurrentLevel().getLevelProps()) {
            for (AActor a : actors) {
                p.hasCollision(a, delta);
            }
        }
    }

    /**
     * <p>Inserts an actor from the queue into the primary list of actors.</p>
     */
    private void insertQueuedActors() {
        for(int i = 0; i < 10 && actorsQueue.size() >= 1; i++) {
            addActor(actorsQueue.remove());
        }
    }

    /**
     * <p>Queues an actor into the queue list.
     * Actors are incrementally shifted to the main list when process is available.</p>
     * @param a Queues an actor to be added to the main list.
     */
    public void queueActor(AActor a) {
        actorsQueue.add(a);
    }

    /**
     * <p>Adds an actor to the main actors list.</p>
     * @param actor The actor to be added to the list
     */
    public void addActor(AActor actor) {
        actors.add(actor);
    }

    /**
     * Removes an actor from the list of active entities
     * @param actor The actor to be removed
     */
    public void removeActor(AActor actor) {
        actors.remove(actor);
    }

    /**
     * <p>Gets the current level that's active.</p>
     * @return the current level object
     */
    public ALevel getCurrentLevel() {
        return levelsList.getCurrentLevel();
    }

    /**
     * <p>Gets the PlayerAvatar object.</p>
     * @return the player
     */
    public ACharacter getPlayerAvatar() {
        return character;
    }

    /**
     * <p>Gets the player inventory. Inventory contains references to collectibles, etc.</p>
     * @return the player's inventory.
     */
    public PlayerInventory getPlayerInventory() {
        return inventory;
    }

    /**
     * The Procedure for a player death
     */
    public void doPlayerDeath() {
        if(isAwaitingReset) {
            return;
        }

        isAwaitingReset = true;
        deathTimeoutStart = System.currentTimeMillis();
        for(int i = 0; i < 20; i++) {
            Particle p = new Particle(
                    getResources(),
                    new Random().nextFloat((int)getPlayerAvatar().getX(),
                            (int)(getPlayerAvatar().getX()+ getPlayerAvatar().getW())),
                    new Random().nextFloat((int)getPlayerAvatar().getY(),
                            (int)(getPlayerAvatar().getY()+ getPlayerAvatar().getH())),
                    new Random().nextFloat(2, 15),
                    new Random().nextFloat(2, 15),
                    new Random().nextFloat(-5, 5),
                    new Random().nextFloat(-5, 5),
                    true
            ){
                @Override
                public void doAction() {
                    removeActor(this);
                }
            };
            System.out.println(p);
            queueActor(p);
        }
        getResources().playAudio("death_" + ((int) (Math.random() * 3) + 1));
    }

    @Override
    public void update(float delta) {

        if(!isPaused) {
            doGameUpdates(delta);
        } else {
            doPauseMenuUpdates(delta);
        }

    }

    @Override
    public void draw(Graphics2D g2d) {

        // Render Level Props
        levelsList.draw(g2d);

        // Render Game Actors
        for (AActor gameObject : actors) {
            if((gameObject instanceof PlayerAvatar) && !isAwaitingReset) {
                gameObject.draw(g2d);
            }
            if(!(gameObject instanceof PlayerAvatar)) {
                gameObject.draw(g2d);
            }
        }

        if(isPaused) {
            //Draw Pause Menu
            pauseMenuEnvironment.draw(g2d);
        } else {
            hudModel.draw(g2d);
        }

    }

    @Override
    public void startBackgroundAudio() {
        Thread audioInitThread = new Thread(() -> {
            if(!audioPlayer.isPlaying()) {
                audioPlayer.play();
            } else {
                System.out.println("Currently playing");
            }
        });
        audioInitThread.start();
    }

    @Override
    public void setAudioPlayer() {
        audioPlayer = getResources().getAudioPlayer("game");
    }

    @Override
    public void reset() {
        gameControls.reset();
        hudModel.reset();
        inventory.reset();
        actors.clear();
        actors.add(character);
        character.reset(levelsList.getCurrentLevel().getCharacterOrigin());
        levelsList.reset();
        Camera.reset();
        isAwaitingReset = false;
    }

    @Override
    public void onExit() {
        super.onExit();
        reset();
    }

}
