package models.environments;

import graphics.window.MainWindow;
import models.environments.game.GameEnvironment;
import prototypes.threading.ARenderRunnable;
import prototypes.threading.ARunnable;
import prototypes.threading.AUpdateRunnable;
import prototypes.window.ACanvas;
import prototypes.window.environments.AEnvironment;

import java.util.ArrayList;

/**
 * The type Environments model.
 */
public class EnvironmentsHandler {

    private MainWindow parentComponent;

    /**
     * The enum Environment type.
     */
    public enum EnvironmentType {
        MAIN_MENU,
        GAME
    }
    private EnvironmentType currentEnvironment = EnvironmentType.MAIN_MENU;

    private final ArrayList<AEnvironment> environments = new ArrayList<>();
    private final ArrayList<ACanvas> canvases = new ArrayList<>();
    private final ArrayList<ARunnable> updateRunnables = new ArrayList<>();
    private final ArrayList<ARunnable> renderRunnables = new ArrayList<>();

    /**
     * Init.
     *
     * @param parentComponent the parent component
     */
    public void init(MainWindow parentComponent) {
        this.parentComponent = parentComponent;
    }

    /**
     * Add pair.
     *
     * @param model     the model
     * @param canvas    the canvas
     * @param uRunnable the u runnable
     * @param rRunnable the r runnable
     */
    public void addPair(AEnvironment model, ACanvas canvas, AUpdateRunnable uRunnable, ARenderRunnable rRunnable) {
        environments.add(model);
        canvases.add(canvas);
        updateRunnables.add(uRunnable);
        renderRunnables.add(rRunnable);
    }

    /**
     * Sets current environment.
     *
     * @param environmentType the environment type
     */
    public void setCurrentEnvironment(EnvironmentType environmentType) {
        pauseCurrentRunnables();

        this.currentEnvironment = environmentType;
    }

    public void swapToEnvironment(EnvironmentType environmentType) {
        environments.get(currentEnvironment.ordinal()).reset();

        setCurrentEnvironment(environmentType);
    }

    /**
     * Gets current environment.
     *
     * @return the current environment
     */
    public AEnvironment getCurrentEnvironment() {
        return environments.get(currentEnvironment.ordinal());
    }

    /**
     * Gets game model.
     *
     * @return the game model
     */
    public GameEnvironment getGameModel() {
        return (GameEnvironment) environments.get(EnvironmentType.GAME.ordinal());
    }

    /**
     * Gets current canvas.
     *
     * @return the current canvas
     */
    public ACanvas getCurrentCanvas() {
        return canvases.get(currentEnvironment.ordinal());
    }

    /**
     * Gets current update runnable.
     *
     * @return the current update runnable
     */
    public Runnable getCurrentUpdateRunnable() {
        return updateRunnables.get(currentEnvironment.ordinal());
    }

    /**
     * Gets current render runnable.
     *
     * @return the current render runnable
     */
    public Runnable getCurrentRenderRunnable() {
        return renderRunnables.get(currentEnvironment.ordinal());
    }

    /**
     * Apply environment.
     */
    public void applyEnvironment() {
        parentComponent.applyEnvironmentAndCanvas();
    }

    public void pauseCurrentRunnables() {
        updateRunnables.get(currentEnvironment.ordinal()).setPaused(true);
        renderRunnables.get(currentEnvironment.ordinal()).setPaused(true);
    }

}
