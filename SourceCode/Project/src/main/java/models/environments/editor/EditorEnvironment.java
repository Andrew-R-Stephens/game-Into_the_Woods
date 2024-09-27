package models.environments.editor;

import controls.editor.EditorControls;
import controls.editor.EditorKeyControls;
import controls.menu.MenuControls;
import controls.menu.MenuKeyControls;
import models.prototypes.actor.editor.AEditProp;
import models.actors.editor.EditorAvatar;
import models.camera.Camera;
import models.environments.EnvironmentType;
import models.environments.EnvironmentsHandler;
import models.environments.menus.gamepausemenumodel.EditorPauseMenuEnvironment;
import models.levels.LevelsList;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>GameEnvironment class is an AEnvironment subtype that controls the Game Environment and its contained entities.</p>
 * <p>Controls the PauseMenuEnvironment when the GameEnvironment is paused.</p>
 * <p>Contains the GameControls, Levels, Game HUD, Player Inventory, and all Actors.</p>
 * @author Andrew Stephens
 */
public class EditorEnvironment extends AEnvironment implements IDrawable, IUpdatable {

    /**<p>The list of actors currently active within the current level.</p>*/
    private final ArrayList<AActor> actors = new ArrayList<>();
    /**<p>The to-add actors that will be added to the list of actors when available.</p>*/
    private final Queue<AActor> actorsQueue = new LinkedList<>();
    /**<p>The Editor Controls for the Editor Environment</p>*/
    private EditorControls editorControls;
    /**<p>The EditorPauseMenuEnvironment that is used for the pause state.</p>*/
    private EditorPauseMenuEnvironment pauseMenuEnvironment;
    /**<p>The list of Levels that the user will navigate between.</p>*/
    private LevelsList levelsList;
    /**<p>The Editor Avatar model</p>*/
    private EditorAvatar editorAvatar;

    private AEditProp aEditProp = null;

    /**<p>If the game is paused</p>*/
    private boolean isPaused = false;

    /**<p>The Robot which keeps the mouse centered in the window in the unpaused game.</p>*/
    private Robot robot;

    /**
     * <p>Initializes the GameEnvironment with references to preconfigured object.</p>
     * @param parentEnvironmentsHandler The encapsulating EnvironmentsHandler. Cannot be null.
     * @param pauseMenuEnvironment The PauseMenuEnvironment. Cannot be null.
     * @param editorControls The GameControls. Cannot be null.
     * @param levelsList The LevelsList. Cannot be null.
     */
    public void init(EnvironmentsHandler parentEnvironmentsHandler,
                     EditorPauseMenuEnvironment pauseMenuEnvironment, EditorControls editorControls,
                     LevelsList levelsList) {

        super.init(parentEnvironmentsHandler, editorControls);

        this.editorControls = editorControls;

        try { robot = new Robot(); } catch (AWTException e) {e.printStackTrace();}

        setPauseMenuEnvironment(pauseMenuEnvironment);
        setLevelsList(levelsList);

        build(editorControls);
    }

    /**
     * <p>Sets the PauseMenuEnvironment for the paused state of the Game Environment.</p>
     * @param pauseMenuEnvironment The PauseMenuEnvironment used for the paused Game state. Cannot be null.
     */
    private void setPauseMenuEnvironment(EditorPauseMenuEnvironment pauseMenuEnvironment) {
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
    private void setEditorAvatar(EditorControls controlsViewModel, LevelsList levelModel) {
        int[] startPos = levelModel.getCurrentLevel().getCharacterOrigin();
        // Add in the Main Test Character

        editorAvatar = new EditorAvatar (
                getResources(),
                controlsViewModel,
                startPos[0],
                startPos[1],
                0, 0,
                0, 0
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
    public void build(EditorControls controlsModel) {
        setEditorAvatar(controlsModel, levelsList);
    }

    /**
     * <p>Updates the objects within the Game state. Controls the game objects, does collisions, updates positions, and
     * updates the Overlay.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void doEditorUpdates(float delta) {
        doEditorControls();
        insertQueuedActors(); // Dequeue queued actors and add them to list of actors
        detectMouseSelect(); // Check Game Object Collisions with Level Props
        detectCollisions(delta);
        updateActors(delta); // Update the Game Objects
        updateLevel(delta); // Update level models.props
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
    private void doEditorControls() {

        if(getKeyController() instanceof EditorKeyControls ekc) {
            if(ekc.getControlsModel().getAction(EditorControls.Actions.ESCAPE)) {
                ekc.getControlsModel().reset();
                setPaused(true);
                getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentType.EDITOR_PAUSE_MENU, false).applyEnvironment();
            }
        }

        if(aEditProp != null) {
            if(getMouseController().isLeftPressed()) {
                aEditProp.onLeftPressed(getMouseController().getPos());
            } else if(getMouseController().isLeftClicked()) {
                aEditProp.onLeftClicked(getMouseController().getPos());
            } else { aEditProp.onLeftReleased(); }
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
                        EnvironmentType.EDITOR, false).applyEnvironment();
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
     * <p>Updates components the currently active level.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void updateLevel(float delta) {
        levelsList.getCurrentLevel().update(delta);
    }

    /**
     * <p>Updates all actor objects within the current level.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void updateActors(float delta) {

        // Update all Actors
        for (AActor gameObject : actors) {

            // Update TestActors
            /*if (gameObject instanceof Particle a) {
                a.update(delta);
            }*/
            if (gameObject instanceof AProp a) {
                a.update(delta);
            }

            // Update Characters
            else if (gameObject instanceof EditorAvatar tc) {
                tc.control(delta);
                tc.update(delta);
                //System.out.println(tc.actionState);
            }
        }
    }

    /**
     * <p>Detects the collisions of platforms with actors. Resolves the collisions within the actor and platform
     * objects themselves.</p>
     */
    private void detectMouseSelect() {

        int[] mRel = Camera.getRelativeMousePos(getMouseController().getPos());

        if(getMouseController().isLeftPressed()) {
            if (aEditProp != null) {
                float px = aEditProp.prop.getX() - 12, py = aEditProp.prop.getY() - 12;
                float pw = aEditProp.prop.getW() + 24, ph = aEditProp.prop.getH() + 24;
                if (((px < mRel[0]) && ((px + pw) > mRel[0]) &&
                        (py < mRel[1]) && ((py + ph) > mRel[1]))) {
                    return;
                } else {
                    aEditProp = null;
                }
            }
            for (AProp p : levelsList.getCurrentLevel().getLevelProps()) {
                if(!(p instanceof APropTrigger)) {
                    float px = p.getX(), py = p.getY();
                    float pw = p.getW(), ph = p.getH();
                    if (((px < mRel[0]) && ((px + pw) > mRel[0]) &&
                            (py < mRel[1]) && ((py + ph) > mRel[1]))) {
                        if (aEditProp != null) { aEditProp = null; }
                        aEditProp = new AEditProp(p);
                        break;
                    }
                }
            }
        }
    }

    /**
     * <p>Detects the collisions of platforms with actors. Resolves the collisions within the actor and platform
     * objects themselves.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void detectCollisions(float delta) {
        new Thread(() -> {
            for (AProp p : levelsList.getCurrentLevel().getLevelProps()) {
                for (AProp op : levelsList.getCurrentLevel().getLevelProps()) {
                    if(p != op && (p.hasGravity() || op.hasGravity())) {
                        p.hasCollision(op, delta);
                    }
                }
            }
        }).start();
    }

    /**
     * <p>Inserts an actor from the queue into the primary list of actors.</p>
     */
    private void insertQueuedActors() {
        for(int i = 0; i < 10 && !actorsQueue.isEmpty(); i++) {
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

    @Override
    public void update(float delta) {

        if(!isPaused) {
            doEditorUpdates(delta);
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
            if((gameObject instanceof EditorAvatar)) {
                gameObject.draw(g2d);
            }
            if(!(gameObject instanceof EditorAvatar)) {
                gameObject.draw(g2d);
            }
        }

        if(isPaused) {
            //Draw Pause Menu
            pauseMenuEnvironment.draw(g2d);
        }

        if(aEditProp != null) {
            aEditProp.draw(g2d);
        }
    }

    @Override
    public void startBackgroundAudio() {}

    @Override
    public void setAudioPlayer() {}

    @Override
    public void reset() {
        editorControls.reset();
        actors.clear();
        actors.add(editorAvatar);
        editorAvatar.reset(levelsList.getCurrentLevel().getCharacterOrigin());
        levelsList.reset();
        Camera.moveTo(100, 100);
    }

    @Override
    public void onExit() {
        super.onExit();
        reset();
    }

}
