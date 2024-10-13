package models.environments.levelEnvironment;

import models.actors.viewport.Viewport;
import models.environments.EnvironmentsHandler;
import models.environments.menus.gamepausemenumodel.GamePauseMenuEnvironment;
import models.levels.LevelsList;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.APawn;
import models.prototypes.controls.AControls;
import models.prototypes.environments.AEnvironment;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class LevelEnvironment extends AEnvironment
        implements IDrawable, IUpdatable, IHUDDrawable {

    /**<p>The list of actors currently active within the current level.</p>*/
    protected final ArrayList<AActor> actors = new ArrayList<>();
    /**<p>The to-add actors that will be added to the list of actors when available.</p>*/
    protected final Queue<AActor> actorsQueue = new LinkedList<>();
    /**<p>The Game Controls for the Game Environment</p>*/
    protected AControls controls;
    /**<p>The PauseMenuEnvironment that is used for the pause state.</p>*/
    protected AMenuEnvironment pauseMenuEnvironment;
    /**<p>The list of Levels that the user will navigate between.</p>*/
    protected LevelsList levelsList;

    /**<p>The Player Avatar model</p>*/
    protected APawn controllableCharacter;
    protected Viewport viewport;

    /**<p>If the game is paused</p>*/
    protected boolean isPaused = false;

    /**<p>The Robot which keeps the mouse centered in the window in the unpaused game.</p>*/
    protected Robot robot;

    protected Thread viewportCollisionThread;

    /**
     * <p>Initializes the GameEnvironment with references to preconfigured object.</p>
     * @param parentEnvironmentsHandler The encapsulating EnvironmentsHandler. Cannot be null.
     * @param pauseMenuEnvironment The PauseMenuEnvironment. Cannot be null.
     * @param controls The GameControls. Cannot be null.
     * @param levelsList The LevelsList. Cannot be null.
     */
    public void init(EnvironmentsHandler parentEnvironmentsHandler,
                     AMenuEnvironment pauseMenuEnvironment, AControls controls,
                     LevelsList levelsList) {

        super.init(parentEnvironmentsHandler, controls);

        setAudioPlayer();

        this.controls = controls;

        try { robot = new Robot(); } catch (AWTException e) {e.printStackTrace();}

        setPauseMenuEnvironment(pauseMenuEnvironment);
        setLevelsList(levelsList);

        build();
    }

    /**
     * <p>Sets the PauseMenuEnvironment for the paused state of the Game Environment.</p>
     * @param pauseMenuEnvironment The PauseMenuEnvironment used for the paused Game state. Cannot be null.
     */
    private void setPauseMenuEnvironment(AMenuEnvironment pauseMenuEnvironment) {
        this.pauseMenuEnvironment = pauseMenuEnvironment;
    }

    /**
     * <p>The LevelsList that contains all of the Levels in the game.</p>
     * @param levelsList The list of levels and any other level data.
     */
    private void setLevelsList(LevelsList levelsList) {
        this.levelsList = levelsList;
    }

    /**
     * <p>Gets the LevelsList object which contains all loaded Levels.</p>
     * @return The list of all created levels.
     */
    public LevelsList getLevelsList() {
        return levelsList;
    }

    /**
     * <p>Updates components the currently active level.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    protected void updateLevel(float delta) {
        levelsList.getCurrentLevel().update(delta);
    }

    /**
     * <p>Adds an actor to the main actors list.</p>
     * @param actor The actor to be added to the list
     */
    public void addActor(AActor actor) {
        actors.add(actor);
    }

    /**
     * <p>Detects the collisions of platforms with actors. Resolves the collisions within the actor and platform
     * objects themselves.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    protected abstract void detectCollisions(float delta);

    protected void detectViewportCollisions() {
        if(viewportCollisionThread == null) {
            viewportCollisionThread = new Thread(() -> {
                while(!isPaused) {
                    getLevelsList().getCurrentLevel().setLocalChunks(viewport);
                    try {  Thread.sleep(100L);  }
                    catch (InterruptedException e) {  throw new RuntimeException(e); }
                }
                viewportCollisionThread = null;
            });
            viewportCollisionThread.start();
        }

    }

    /**
     * <p>Sets the PauseMenuEnvironment for the paused state of the Game Environment.</p>
     * @param pauseMenuEnvironment The PauseMenuEnvironment used for the paused Game state. Cannot be null.
     */
    private void setPauseMenuEnvironment(GamePauseMenuEnvironment pauseMenuEnvironment) {
        this.pauseMenuEnvironment = pauseMenuEnvironment;
    }

    /**
     * <p>Sets the current level by the use of index.</p>
     * @param levelIndex The index of the level. Zero-based.
     */
    public void setCurrentLevel(int levelIndex) {
        levelsList.setCurrentLevel(levelIndex);
    }

    /**
     * <p>Sets the game environment to paused.</p>
     * @param paused If the game should be paused.
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    /**
     * <p>Inserts an actor from the queue into the primary list of actors.</p>
     */
    protected void insertQueuedActors() {
        for(int i = 0; i < 10 && !actorsQueue.isEmpty(); i++) {
            addActor(actorsQueue.remove());
        }
    }

    /**
     * <p>Queues an actor into the queue list.
     * Actors are incrementally shifted to the main list when process is available.</p>
     * @param a Queues an actor to be added to the main list.
     */
    protected void queueActor(AActor a) {
        actorsQueue.add(a);
    }

    /**
     * Removes an actor from the list of active entities
     * @param actor The actor to be removed
     */
    protected void removeActor(AActor actor) {
        actors.remove(actor);
    }

    /**
     * <p>Builds the environment with a new Player Avatar and pairs the controls and Levels List  directly with the
     * Player.</p>
     */
    public void build() {
        setAvatar();

        viewport = new Viewport(0, 0,
                Config.window_width_actual,
                Config.window_height_actual
        );
    }

    protected abstract void setAvatar();

}
