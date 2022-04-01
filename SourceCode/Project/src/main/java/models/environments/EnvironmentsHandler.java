package models.environments;

import graphics.window.MainWindow;
import models.environments.game.GameEnvironment;
import prototypes.threading.ARunnable;
import prototypes.window.ACanvas;
import prototypes.window.environments.AEnvironment;

import java.util.ArrayList;

/**
 * The type Environments model.
 */
public class EnvironmentsHandler {

    private MainWindow parentDisplayWindow;

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

    private Thread updatesThread;
    private Thread rendersThread;

    /**
     * Init.
     *
     * @param parentDisplayWindow the parent component
     */
    public void init(MainWindow parentDisplayWindow) {
        this.parentDisplayWindow = parentDisplayWindow;
    }

    /**
     * Add pair.
     *
     * @param model     the model
     * @param canvas    the canvas
     * @param uRunnable the u runnable
     * @param rRunnable the r runnable
     */
    public void addEnvironmentPair(AEnvironment model, ACanvas canvas, ARunnable uRunnable, ARunnable rRunnable) {
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
        pauseThreads();

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
    public GameEnvironment getGameEnvironment() {
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
        parentDisplayWindow.build();
    }

    public void applyEnvironment(EnvironmentType environmentType) {
        setCurrentEnvironment(environmentType);
    }

    public void pauseThreads() {
        updateRunnables.get(currentEnvironment.ordinal()).setPaused(true);
        renderRunnables.get(currentEnvironment.ordinal()).setPaused(true);
    }


    public void initThreads() {

        if(updatesThread != null) {
            updatesThread.interrupt();
            updatesThread = null;
        }
        if(rendersThread != null) {
            rendersThread.interrupt();
            rendersThread = null;
        }

        updatesThread = new Thread(getCurrentUpdateRunnable());
        rendersThread = new Thread(getCurrentRenderRunnable());

        updatesThread.start();
        rendersThread.start();
    }

}
