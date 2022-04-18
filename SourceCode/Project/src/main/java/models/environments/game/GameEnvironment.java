package models.environments.game;

import controls.GameControls;
import controls.MenuControls;
import controls.game.GameKeyControls;
import controls.game.GameMouseControls;
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

public class GameEnvironment extends AEnvironment implements IDrawable, IUpdatable {

    private GameControls gameControls;

    private PauseMenuEnvironment pauseMenuEnvironment;

    private LevelsList levelsList;

    private HUDModel hudModel;
    private PlayerInventory inventory;

    private final ArrayList<AActor> actors = new ArrayList<>();
    private final Queue<AActor> actorsQueue = new LinkedList<>();

    private PlayerAvatar character;

    private boolean isPaused = false;

    private Robot robot;

    public void init(EnvironmentsHandler parentEnvironmentsHandler,
                     PauseMenuEnvironment pauseMenuEnvironment, GameControls gameControls,
                     LevelsList levelsList, HUDModel hudModel, PlayerInventory inventory) {

        super.init(parentEnvironmentsHandler, gameControls);

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

    private void setPlayerInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    private void setHUDModel(HUDModel hudModel) {
        this.hudModel = hudModel;
    }

    private void setPauseMenuEnvironment(PauseMenuEnvironment pauseMenuEnvironment) {
        this.pauseMenuEnvironment = pauseMenuEnvironment;
    }

    public void build(GameControls controlsModel) {
        setPlayerAvatar(controlsModel, levelsList);
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
            gameObject.draw(g2d);
        }

        if(isPaused) {
            //Draw Pause Menu
            pauseMenuEnvironment.draw(g2d);
        } else {
            hudModel.draw(g2d);
        }

    }

    public void doGameUpdates(float delta) {

        doGameControls();
        insertQueuedActors(); // Dequeue queued actors and add them to list of actors
        detectCollisions(delta); // Check Game Object Collisions with Level Props
        updateActors(delta); // Update the Game Objects
        updateLevel(delta); // Update level models.props
        updateHUD(delta); // Update HUD overlay
    }

    public void doPauseMenuUpdates(float delta) {
        doPauseMenuControls();

        pauseMenuEnvironment.update(delta);
    }

    private void doGameControls() {
        robot.mouseMove(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * .5f),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * .5f)
        );

        if(keyController instanceof GameKeyControls kc) {
            if(kc.getControlsModel().getAction(GameControls.Actions.ESCAPE)) {
                kc.getControlsModel().reset();
                setPaused(true);
                getParentEnvironmentsHandler().swapToEnvironment(
                        EnvironmentsHandler.EnvironmentType.GAME_PAUSE_MENU, false).applyEnvironment();
            }
        }
    }

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

    public void setLevelsList(LevelsList levelsList) {
        this.levelsList = levelsList;
    }

    public LevelsList getLevelsList() {
        return levelsList;
    }

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


    private void updateHUD(float delta) {
        hudModel.update(delta);
    }

    private void updateLevel(float delta) {
        levelsList.getCurrentLevel().update(delta);
    }

    private synchronized void testAddingActors(float delta) {
        if (mouseController instanceof GameMouseControls gmc) {

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

    public void updateActors(float delta) {

        // Update all Actors
        for (AActor gameObject : actors) {

            // Update TestActors
            if (gameObject instanceof Particle a) {
                a.update(delta);
            }

            // Update Characters
            if (gameObject instanceof PlayerAvatar tc) {
                tc.control(delta);
                tc.update(delta);
                //System.out.println(tc.actionState);
            }

        }

    }

    private void detectCollisions(float delta) {
        for (AProp p : levelsList.getCurrentLevel().getLevelProps()) {
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

    public void queueActor(AActor a) {
        actorsQueue.add(a);
    }

    public void addActor(AActor actor) {
        actors.add(actor);
    }

    public void setCurrentLevel(int levelIndex) {
        levelsList.setCurrentLevel(levelIndex);
    }

    public ALevel getCurrentLevel() {
        return levelsList.getCurrentLevel();
    }

    public ACharacter getPlayerAvatar() {
        return character;
    }

    @Override
    public void startBackgroundAudio() {
        audioPlayer = getResources().playAudio("game");
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
    }

    public PlayerInventory getPlayerInventory() {
        return inventory;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    @Override
    public void onExit() {
        super.onExit();
        reset();
    }
}
