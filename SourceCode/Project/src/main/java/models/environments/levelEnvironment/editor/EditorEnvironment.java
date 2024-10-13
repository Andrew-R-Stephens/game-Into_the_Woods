package models.environments.levelEnvironment.editor;

import controls.editor.EditorControls;
import controls.editor.EditorKeyControls;
import controls.menu.MenuControls;
import controls.menu.MenuKeyControls;
import models.environments.levelEnvironment.LevelEnvironment;
import models.prototypes.actor.editprop.AEditProp;
import models.actors.editor.EditorAvatar;
import models.camera.Camera;
import models.environments.EnvironmentType;
import models.environments.EnvironmentsHandler;
import models.environments.menus.gamepausemenumodel.EditorPauseMenuEnvironment;
import models.levels.LevelsList;
import models.prototypes.actor.AActor;
import models.prototypes.level.ALevel;
import models.prototypes.level.ChunkProp;
import models.prototypes.level.prop.AProp;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.prototypes.level.propChunk.PropChunk;
import models.utils.config.Config;
import models.textures.meshes.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>GameEnvironment class is an AEnvironment subtype that controls the Game Environment and its contained entities.</p>
 * <p>Controls the PauseMenuEnvironment when the GameEnvironment is paused.</p>
 * <p>Contains the GameControls, Levels, Game HUD, Player Inventory, and all Actors.</p>
 * @author Andrew Stephens
 */
public class EditorEnvironment extends LevelEnvironment {

    private AEditProp aEditProp = null;

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

        super.init(parentEnvironmentsHandler, pauseMenuEnvironment, editorControls, levelsList);
    }

    /**
     * <p>Updates the objects within the Game state. Controls the game objects, does collisions, updates positions, and
     * updates the Overlay.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void doUpdates(float delta) {
        doEditorControls();
        insertQueuedActors(); // Dequeue queued actors and add them to list of actors
        detectViewportCollisions();
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

                float[] viewportPos = Camera.getViewportRelative(0f, 0f);
                viewport.update(
                        (int)Math.floor(viewportPos[0]), (int)Math.floor(viewportPos[1]),
                        (int)Math.ceil(Config.window_width_actual / Config.scaledW),
                        (int)Math.ceil(Config.window_height_actual / Config.scaledH)
                );
            }
        }
    }

    /*
    protected void detectViewportCollisions() {
        if(viewportCollisionThread == null) {
            viewportCollisionThread = new Thread(() -> {
                while(!isPaused) {
                    getLevelsList().getCurrentLevel().setLocalChunks(viewport);
                    for(PropChunk localChunk: getLevelsList().getCurrentLevel().getLocalChunks()) {
                        for (AProp[] pO : localChunk.getAllProps()) {
                            for(AProp p: pO) {
                                if (p == null) continue;
                                p.setCanRender(true);
                            }
                        }
                    }
                    try {  Thread.sleep(100L);  }
                    catch (InterruptedException e) {  throw new RuntimeException(e); }
                }
                viewportCollisionThread = null;
            });
            viewportCollisionThread.start();
        }

    }
    */

    /**
     * <p>Detects the collisions of platforms with actors. Resolves the collisions within the actor and platform
     * objects themselves.</p>
     */
    private void detectMouseSelect() {

        int[] mRel = Camera.getRelativeMousePos(getMouseController().getPos());
        if(getMouseController().isLeftPressed()) {
            if (aEditProp != null) {
                float px = aEditProp.prop.getX() - (Tile.W * .25f), py = aEditProp.prop.getY() - (Tile.W * .25f);
                float pw = aEditProp.prop.getW() + (Tile.W * .5f), ph = aEditProp.prop.getH() + (Tile.H * .5f);
                if (((px < mRel[0]) && ((px + pw) > mRel[0]) &&
                        (py < mRel[1]) && ((py + ph) > mRel[1]))) {
                    return;
                } else {
                    aEditProp = null;
                }
            }
            for(PropChunk localChunk: getLevelsList().getCurrentLevel().getLocalChunks()) {
                for (AProp[] pO : localChunk.getAllProps()) {
                    for (AProp p : pO) {
                        if (!(p instanceof APropTrigger)) {
                            if (p == null) continue;
                            float px = p.getX(), py = p.getY();
                            float pw = p.getW(), ph = p.getH();
                            if (((px < mRel[0]) && ((px + pw) > mRel[0]) &&
                                    (py < mRel[1]) && ((py + ph) > mRel[1]))) {
                                if (aEditProp != null) {
                                    aEditProp = null;
                                }
                                aEditProp = new AEditProp(p, getResources());
                                break;
                            }
                        }
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
    @Override
    protected void detectCollisions(float delta) {
        new Thread(() -> {

            ALevel currentLevel = getLevelsList().getCurrentLevel();
            ArrayList<PropChunk> localChunks = currentLevel.getLocalChunks();
            ArrayList<AProp> allProps = new ArrayList<>();

            for(PropChunk pC: localChunks) {
                for(AProp[] props: pC.getAllProps()) {
                    for(AProp prop: props) {
                        ChunkProp chunkProp = new ChunkProp(prop, pC);
                        allProps.add(chunkProp.prop());
                    }
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

        // Render character
        getAvatar().draw(g2d);

        // Render Game Actors
        for (AActor gameObject : actors) {
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
        controls.reset();
        actors.clear();
        actors.add(getAvatar());
        getAvatar().reset(levelsList.getCurrentLevel().getCharacterOrigin());
        levelsList.reset();
        Camera.moveTo(100, 100);
    }

    @Override
    public void onExit() {
        super.onExit();
        reset();
    }

    @Override
    protected void setAvatar() {
        int[] startPos = levelsList.getCurrentLevel().getCharacterOrigin();
        // Add in the Main Test Character

        controllableCharacter = new EditorAvatar (
                getResources(),
                (EditorControls) controls,
                startPos[0],
                startPos[1],
                0, 0,
                0, 0
        );
    }

    private EditorAvatar getAvatar() {
        return (EditorAvatar) controllableCharacter;
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

    }
}
