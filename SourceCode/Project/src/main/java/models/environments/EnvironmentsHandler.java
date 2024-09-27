package models.environments;

import models.environments.editor.EditorEnvironment;
import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.environments.AEnvironment;
import models.prototypes.threading.ARunnable;
import models.prototypes.views.ACanvas;
import models.utils.config.Config;
import models.utils.files.SaveData;
import views.window.MainWindow;

import java.util.ArrayList;

/**
 * <p>Contains references to all AEnvironments, and holds references each of their ACanvases and ARunnables.</p>
 * <br>
 * <p>Works to handle navigation between AEnvironments as needed. Controls threading and AWindow build procedures.</p>
 * @author Andrew Stephens
 */
public class EnvironmentsHandler {

    /**<p>The main window frame.</p>*/
    private MainWindow parentWindow;

    /**<p>The save data with previously saved information and current information.</p>*/
    private SaveData saveData;

    /**<p>The current environment that the user is in</p>*/
    private EnvironmentType currentEnvironment = EnvironmentType.MAIN_MENU;

    /**<p>The list of all render runnables available</p>*/
    private final ArrayList<EnvironmentPair> environmentPairs = new ArrayList<>();

    /**<p>The Updates thread that all environments' update runnables will use.</p>*/
    private Thread updatesThread;
    /**<p>The Render thread that all environments' render runnables will use.</p>*/
    private Thread rendersThread;

    /**
     * <p>Initializes the EnvironmentsHandler.</p>
     * @param parentDisplayWindow the window that is shown to the user
     */
    public void init(MainWindow parentDisplayWindow, SaveData saveData) {
        this.parentWindow = parentDisplayWindow;
        this.saveData = saveData;
    }

    /**
     * <p>Adds a new AEnvironment subtype to the list of AEnvironments.</p>
     * @param model The AEnvironment
     * @param canvas The ACanvas for the AEnvironment
     * @param uRunnable The update ARunnable for the AEnvironment
     * @param rRunnable The render ARunnable for the AEnvironment
     */
    public void addEnvironmentPair(AEnvironment model, ACanvas canvas, ARunnable uRunnable, ARunnable rRunnable) {
        EnvironmentPair environmentPair = new EnvironmentPair(model, canvas, uRunnable, rRunnable);
        environmentPairs.add(environmentPair);
    }

    /**
     * <p>Sets the current EnvironmentType.</p>
     * @param environmentType the chosen EnvironmentType.
     */
    public void setCurrentEnvironmentType(EnvironmentType environmentType) {
        this.currentEnvironment = environmentType;
    }

    /**
     * <p>Handles the procedure of switching from one AEnvironment to another. AEnvironments do no all need to be reset
     * due to some AEnvironments existing at certain points. Therefore, a reset condition is passed.</p>
     * @param environmentType the desired environment to switch to.
     * @param resetEnvironment Whether the environment should be reset.
     * @return this EnvironmentsHandler, used for chaining
     */
    public EnvironmentsHandler swapToEnvironment(EnvironmentType environmentType, boolean resetEnvironment) {
        if(resetEnvironment) {
            AEnvironment e = environmentPairs.get(currentEnvironment.ordinal()).environment;
            e.onExit();
            e.reset();
        }
        pauseThreads();

        EnvironmentType previousEnvironment = currentEnvironment;
        setCurrentEnvironmentType(environmentType);

        AEnvironment pEnvironment = environmentPairs.get(previousEnvironment.ordinal()).environment;
        AEnvironment cEnvironment = environmentPairs.get(currentEnvironment.ordinal()).environment;
        if(resetEnvironment && pEnvironment != cEnvironment) {
            pEnvironment.onExit();
            cEnvironment.onResume();
        }

        return this;
    }

    /**
     * @return the active AEnvironment.
     */
    public AEnvironment getCurrentEnvironment() {
        return environmentPairs.get(currentEnvironment.ordinal()).environment;
    }

    public AEnvironment getEnvironment() {
        return getEnvironment(currentEnvironment);
    }

    public AEnvironment getEnvironment(EnvironmentType environmentType) {
        return switch (environmentType) {
            case GAME -> getGameEnvironment();
            case EDITOR -> getEditorEnvironment();
            default -> getMenuEnvironment();
        };
    }

    /**
     * @return the GameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return (GameEnvironment) environmentPairs.get(EnvironmentType.GAME.ordinal()).environment;
    }

    /**
     * @return the GameEnvironment.
     */
    public EditorEnvironment getEditorEnvironment() {
        return (EditorEnvironment) environmentPairs.get(EnvironmentType.EDITOR.ordinal()).environment;
    }

    /**
     * @return the MainMenuEnvironment.
     */
    public MainMenuEnvironment getMenuEnvironment() {
        return (MainMenuEnvironment) environmentPairs.get(EnvironmentType.MAIN_MENU.ordinal()).environment;
    }

    /**
     * @return the active AEnvironment's canvas.
     */
    public ACanvas getCurrentCanvas() {
        return environmentPairs.get(currentEnvironment.ordinal()).canvas;
    }

    /**
     * @return the active AEnvironment's updates-based ARunnable
     */
    public Runnable getCurrentUpdateRunnable() {
        return environmentPairs.get(currentEnvironment.ordinal()).updateRunnable;
    }

    /**
     * <p>Returns the ARunnable of the current AEnvironment that deals with renders.</p>
     * @return the active AEnvironment's render-based ARunnable
     */
    public Runnable getCurrentRenderRunnable() {
        return environmentPairs.get(currentEnvironment.ordinal()).renderRunnable;
    }

    /**
     * <p>Calls the applyEnvironment method with the reset default set as true.</p>
     */
    public void applyEnvironment() {
        applyEnvironment(true);
    }

    /**
     * <p>Applies the current AEnvironment to the Window. Restarts the current AEnvironment's Threads if necessary.</p>
     * @param resetThreads whether the threads should be rebuilt
     */
    public void applyEnvironment(boolean resetThreads) {
        boolean showCursor =
                !(environmentPairs.get(currentEnvironment.ordinal()).environment instanceof GameEnvironment);
        parentWindow.buildCursor(showCursor);

        parentWindow.build();

        if(resetThreads) {
            initThreads();
        }
    }

    /**
     * <p>Pauses the current AEnvironment's ARunnables.</p>
     */
    public void pauseThreads() {
        environmentPairs.get(currentEnvironment.ordinal()).updateRunnable.setPaused(true);
        environmentPairs.get(currentEnvironment.ordinal()).renderRunnable.setPaused(true);
    }

    /**
     * <p>Destroys active threads and creates new Threads using the current AEnvironment's ARunnables.</p>
     */
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

    public SaveData getSaveData() {
        return saveData;
    }

    /**
     * <p>Rebuilds the window with the appropriate window dimensions and WindowType.</p>
     * <p>Used primarily through the Options menus.</p>
     */
    public void rebuildWindow() {
        parentWindow.dispose();
        parentWindow = new MainWindow();
        parentWindow.setResources(getCurrentEnvironment().getResources());
        parentWindow.init(this);
        applyEnvironment(false);
        Config.calcResolutionScale();
    }


}
