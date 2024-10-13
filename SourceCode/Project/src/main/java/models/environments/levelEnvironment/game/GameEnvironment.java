package models.environments.levelEnvironment.game;

import controls.game.GameControls;
import controls.game.GameKeyControls;
import controls.game.GameMouseControls;
import controls.menu.MenuControls;
import controls.menu.MenuKeyControls;
import models.actors.particles.Particle;
import models.actors.player.PlayerAvatar;
import models.actors.viewport.Viewport;
import models.camera.Camera;
import models.environments.EnvironmentType;
import models.environments.EnvironmentsHandler;
import models.environments.levelEnvironment.LevelEnvironment;
import models.environments.levelEnvironment.game.hud.HUDModel;
import models.environments.levelEnvironment.game.playerinventory.PlayerInventory;
import models.environments.menus.gamepausemenumodel.GamePauseMenuEnvironment;
import models.levels.LevelsList;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.propChunk.PropChunk;
import models.utils.config.Config;
import models.textures.meshes.Tile;

import java.awt.*;
import java.util.*;

/**
 * <p>GameEnvironment class is an AEnvironment subtype that controls the Game Environment and its contained entities.</p>
 * <p>Controls the PauseMenuEnvironment when the GameEnvironment is paused.</p>
 * <p>Contains the GameControls, Levels, Game HUD, Player Inventory, and all Actors.</p>
 * @author Andrew Stephens
 */
public class GameEnvironment extends LevelEnvironment {

    /**<p>The heads-up-display containing all overlays.</p>*/
    private HUDModel hudModel;
    /**<p>The player's inventory of collected items.</p>*/
    private PlayerInventory inventory;

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
                     GamePauseMenuEnvironment pauseMenuEnvironment, GameControls gameControls,
                     LevelsList levelsList, HUDModel hudModel, PlayerInventory inventory) {

        super.init(parentEnvironmentsHandler, pauseMenuEnvironment, gameControls, levelsList);

        setHUDModel(hudModel);
        setPlayerInventory(inventory);
    }

    /**
     * <p>Sets the preconfigured HUD for the Game Environment.</p>
     * @param hudModel The HUD Model for the Overlays. Should not be null.
     */
    private void setHUDModel(HUDModel hudModel) {
        this.hudModel = hudModel;
    }

    @Override
    protected void setAvatar() {
        int[] startPos = levelsList.getCurrentLevel().getCharacterOrigin();
        // Add in the Main Test Character

        float h = Tile.H * .9f;
        controllableCharacter = new PlayerAvatar (
                getResources(),
                (GameControls) controls,
                AActor.roundCoordinate(startPos[0]),
                AActor.roundCoordinate(startPos[1]),
                h * .714f, h,
                0, 0,
                true
        );
    }

    /**
     * <p>Sets a preconfigured player inventory into the Game Environment.</p>
     * @param inventory The inventory of the player. Should not be null.
     */
    private void setPlayerInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * <p>Updates the objects within the Game state. Controls the game objects, does collisions, updates positions, and
     * updates the Overlay.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void doUpdates(float delta) {

        doGameControls();
        insertQueuedActors(); // Dequeue queued actors and add them to list of actors
        detectViewportCollisions();
        detectCollisions(delta); // Check Actor collisions with Level Props
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
                        EnvironmentType.GAME_PAUSE_MENU, false).applyEnvironment();
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
                        EnvironmentType.GAME, false).applyEnvironment();
            }
        }
    }

    /**
     * <p>Updates components within the in-game HUD overlay.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void updateHUD(float delta) {
        hudModel.update(delta);
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
                    //tc.control(delta);
                    tc.update(delta);

                    float[] viewportPos = Camera.getViewportRelative(0f, 0f);
                    viewport.update(
                            viewportPos[0], viewportPos[1],
                            Config.window_width_actual / Config.scaledW,
                            Config.window_height_actual / Config.scaledH
                    );
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
    @Override
    protected void detectCollisions(float delta) {
        new Thread(() -> {

            ArrayList<AProp> allProps = new ArrayList<>();
            for(PropChunk pC: getLevelsList().getCurrentLevel().getLocalChunks()) {
                for(AProp[] props: pC.getAllProps()) {
                    allProps.addAll(Arrays.asList(props));
                }
            }

            for (AActor a : actors) {
                if(a == null) continue;
                for(AProp p: allProps) {
                    if(p == null) continue;
                    if(p.checkCollision(a, delta, true));
                }
            }

            for (AProp p1 : allProps) {
                if(p1 == null) continue;
                if(p1.hasGravity()) {
                    for (AProp p2 : allProps) {
                        if (p2 == null || p1 == p2) continue;
                        if (p1.getY() < p2.getY()) {
                            p2.checkCollision(p1, delta, true);
                        }
                    }
                }
            }

        }).start();

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
    public ACharacter getAvatar() {
        return (ACharacter) controllableCharacter;
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
                    new Random().nextFloat((int) getAvatar().getX(),
                            (int)(getAvatar().getX()+ getAvatar().getW())),
                    new Random().nextFloat((int) getAvatar().getY(),
                            (int)(getAvatar().getY()+ getAvatar().getH())),
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
            doUpdates(delta);
        } else {
            doPauseMenuUpdates(delta);
        }

    }

    @Override
    public void draw(Graphics2D g2d) {

        // Render Level Props
        levelsList.draw(g2d);

        // Render Game Actors
        try {
            for (AActor gameObject : actors) {
                if ((gameObject instanceof PlayerAvatar) && !isAwaitingReset) {
                    gameObject.draw(g2d);
                }
                if (!(gameObject instanceof PlayerAvatar)) {
                    gameObject.draw(g2d);
                }
            }
        } catch (ConcurrentModificationException e) { e.printStackTrace(); }

        if(isPaused) {
            //Draw Pause Menu
            pauseMenuEnvironment.draw(g2d);
        } else {
            hudModel.draw(g2d);
        }

    }

    @Override
    public void drawAsHUD(Graphics2D g) {
        getCurrentLevel().drawAsHUD(g);
        getAvatar().drawAsHUD(g);
        viewport.drawAsHUD(g);
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
        controls.reset();
        hudModel.reset();
        inventory.reset();
        actors.clear();
        actors.add(getAvatar());
        getAvatar().reset(levelsList.getCurrentLevel().getCharacterOrigin());
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
