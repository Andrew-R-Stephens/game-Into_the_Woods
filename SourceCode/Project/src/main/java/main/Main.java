package main;

import controls.editor.EditorControls;
import controls.editor.EditorKeyControls;
import controls.editor.EditorMouseControls;
import controls.game.GameControls;
import controls.game.GameKeyControls;
import controls.game.GameMouseControls;
import controls.menu.MenuControls;
import controls.menu.MenuKeyControls;
import controls.menu.MenuMouseControls;
import models.environments.EnvironmentType;
import models.environments.EnvironmentsHandler;
import models.environments.levelEnvironment.editor.EditorEnvironment;
import models.environments.levelEnvironment.game.GameEnvironment;
import models.environments.levelEnvironment.game.hud.HUDModel;
import models.environments.levelEnvironment.game.hud.components.MapOverlay;
import models.environments.levelEnvironment.game.hud.components.PlayerStatsOverlay;
import models.environments.levelEnvironment.game.hud.components.TimeKeeperOverlay;
import models.environments.levelEnvironment.game.playerinventory.PlayerInventory;
import models.environments.menus.gamepausemenumodel.EditorPauseMenuEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.environments.menus.gamepausemenumodel.GamePauseMenuEnvironment;
import models.levels.LevelsList;
import models.runnables.RenderRunnable;
import models.runnables.UpdateRunnable;
import models.utils.config.Config;
import models.utils.files.SaveData;
import models.utils.files.io.PreferencesParser;
import models.utils.files.io.SaveFileRW;
import models.utils.resources.Resources;
import views.canvas.EnvironmentCanvas;
import views.window.MainWindow;

import java.io.File;

/**
 * <p>The launching class.</p>
 *
 * <p>Includes static references to all root-level objects.</p>
 * <p>Handles the initialization of all root-level objects.</p>
 * @author Andrew Stephens
 */
public class Main {

    /**<p>The globally used Configurations reference.</p>*/
    private static Config config;
    /**<p>The globally used resource files of the classpath.</p>*/
    private static Resources resources;
    /**<p>The persistently used save data.</p>*/
    private static SaveData saveData;
    /**<p>The SaveFileRW that the SaveData uses persistently.</p>*/
    private static SaveFileRW saveFileRW;

    /**<p>The persistent EnvironmentsHandler.</p>*/
    private static EnvironmentsHandler environmentsHandler;

    /**<p>The persistent MapOverlay.</p>*/
    private static MapOverlay mapOverlay;
    /**<p>The persistent PlayerStatsOverlay.</p>*/
    private static PlayerStatsOverlay playerStatsOverlay;
    /**<p>The persistent TimeKeeperOverlay.</p>*/
    private static TimeKeeperOverlay timeKeeperOverlay;

    /**<p>The persistent HUDModel.</p>*/
    private static HUDModel hudModel;
    /**<p>The persistent PlayerInventory.</p>*/
    private static PlayerInventory inventory;

    /**<p>The persistent MenuControls.</p>*/
    private static MenuControls menuControlsModel;
    /**<p>The persistent GameControls.</p>*/
    private static GameControls gameControlsModel;
    /**<p>The persistent GameControls.</p>*/
    private static EditorControls editorControlsModel;

    /**<p>The persistent MainMenuEnvironment.</p>*/
    private static MainMenuEnvironment mainMenuEnvironment;
    /**<p>The persistent GameEnvironment.</p>*/
    private static GameEnvironment gameEnvironment;
    /**<p>The persistent GameEnvironment.</p>*/
    private static EditorEnvironment editorEnvironment;

    /**<p>The persistent PauseMenuEnvironment.</p>*/
    private static GamePauseMenuEnvironment gamePauseMenuModel;
    /**<p>The persistent PauseMenuEnvironment.</p>*/
    private static EditorPauseMenuEnvironment editorPauseMenuModel;

    /**<p>The persistent UpdateRunnable.</p>*/
    private static UpdateRunnable gameUpdateRunnable;
    /**<p>The persistent RenderRunnable.</p>*/
    private static RenderRunnable gameRenderRunnable;

    /**<p>The persistent UpdateRunnable.</p>*/
    private static UpdateRunnable menuUpdateRunnable;
    /**<p>The persistent RenderRunnable.</p>*/
    private static RenderRunnable menuRenderRunnable;

    /**<p>The persistent UpdateRunnable.</p>*/
    private static UpdateRunnable editorUpdateRunnable;
    /**<p>The persistent RenderRunnable.</p>*/
    private static RenderRunnable editorRenderRunnable;

    /**<p>The persistent LevelsList.</p>*/
    private static LevelsList levelsListModel;

    /**<p>The persistent EnvironmentCanvas for the MainMenuEnvironment.</p>*/
    private static EnvironmentCanvas<MainMenuEnvironment> menuCanvas;
    /**<p>The persistent EnvironmentCanvas for the GameEnvironment.</p>*/
    private static EnvironmentCanvas<GameEnvironment> gameCanvas;
    /**<p>The persistent EnvironmentCanvas for the GameEnvironment.</p>*/
    private static EnvironmentCanvas<EditorEnvironment> editorCanvas;

    /**<p>The persistent MainWindow reference.</p>*/
    private static MainWindow window;

    /**
     * <p>The starting point of the program.</p>
     * <p>First loads all assets from file into a resource pool for later use and ease of access.</p>
     * <p>Then Creates all permanent static variables for protection against null pointer exceptions.</p>
     * <p>Then initializes all variables with their necessary reference objects. Assists in MVC cross-object
     * communication.</p>
     */
    public static void main(String[] args) {

        // Load in Images, Files, etc into a resource pool.
        loadAssets();

        // Create all permanent Objects
        create();

        // Initialize all permanent Objects
        init();

    }

    /**
     * <p>Handles the loading of all assets from files into a resource pool for later use and ease of access.</p>
     */
    public static void loadAssets() {

        // Create Preferences
        config = new Config();
        Config.registerSystemInfo();

        resources = new Resources();
        resources.init();

    }

    /**
     * <p>Creates all permanent static variables for protection against null pointer exceptions.</p>
     */
    public static void create() {
        saveData = new SaveData();
        saveFileRW = new SaveFileRW();

        // Create Environment Handler
        environmentsHandler = new EnvironmentsHandler();

        // Create Control Models
        menuControlsModel = new MenuControls();
        gameControlsModel = new GameControls();
        editorControlsModel = new EditorControls();

        // Create all Environments
        mainMenuEnvironment = new MainMenuEnvironment();
        gameEnvironment = new GameEnvironment();
        gamePauseMenuModel = new GamePauseMenuEnvironment();
        editorEnvironment = new EditorEnvironment();
        editorPauseMenuModel = new EditorPauseMenuEnvironment();

        // Create Levels List Model
        levelsListModel = new LevelsList();

        // Create HUD Model Components
        mapOverlay = new MapOverlay();
        playerStatsOverlay = new PlayerStatsOverlay();
        timeKeeperOverlay = new TimeKeeperOverlay();

        // Create Game Environment Models
        hudModel = new HUDModel();
        inventory = new PlayerInventory();

        // Create State Canvases
        //menuCanvas = new MenuCanvas();
        menuCanvas = new EnvironmentCanvas<>();
        gameCanvas = new EnvironmentCanvas<>();
        editorCanvas = new EnvironmentCanvas<>();

        // Create Runnables
        gameUpdateRunnable = new UpdateRunnable();
        gameRenderRunnable = new RenderRunnable();
        editorUpdateRunnable = new UpdateRunnable();
        editorRenderRunnable = new RenderRunnable();
        menuUpdateRunnable = new UpdateRunnable();
        menuRenderRunnable = new RenderRunnable();

        // Create Window
        window = new MainWindow();

    }

    /**
     * <p>Handles the initializes all variables with their necessary reference objects.</p>
     * <p>Assists in MVC cross-object referencing.</p>
     */
    public static void init() {

        // Initialize Preferences
        // Uses two versions of Preferences.xml due to strange behavior
        File file = resources.loadTextFile("preferences.xml");
        if(file == null) {
            file = resources.loadTextFile("Preferences.xml");
        }
        new PreferencesParser(config, file).parse();

        // Overwrite certain preferences from save data
        saveData.init(saveFileRW);

        // Reference resources
        mainMenuEnvironment.setResources(resources);
        gamePauseMenuModel.setResources(resources);
        gameEnvironment.setResources(resources);
        editorPauseMenuModel.setResources(resources);
        editorEnvironment.setResources(resources);
        window.setResources(resources);

        // Initialize Window with Preference Data
        window.init(environmentsHandler);

        // Initialize Control Models
        gameControlsModel.init(new GameMouseControls(gameControlsModel), new GameKeyControls(gameControlsModel));
        editorControlsModel.init(new EditorMouseControls(editorControlsModel), new EditorKeyControls(editorControlsModel));
        menuControlsModel.init(new MenuMouseControls(menuControlsModel), new MenuKeyControls(menuControlsModel));

        // Initialize Levels
        int defaultLevel = 0;
        levelsListModel.init(gameEnvironment, defaultLevel);

        // Initialize AEnvironment Model Container
        environmentsHandler.init(window, saveData);

        // Initialize Pause Menu
        gamePauseMenuModel.init(environmentsHandler, menuControlsModel, gameEnvironment);
        // Initialize Pause Menu
        editorPauseMenuModel.init(environmentsHandler, menuControlsModel, editorEnvironment);

        hudModel.init(gameEnvironment, mapOverlay, playerStatsOverlay, timeKeeperOverlay);

        // Initialize AEnvironment Models
        mainMenuEnvironment.init(environmentsHandler, menuControlsModel);
        gameEnvironment.init(
                environmentsHandler,
                gamePauseMenuModel, gameControlsModel,
                levelsListModel, hudModel, inventory);
        editorEnvironment.init(
                environmentsHandler,
                editorPauseMenuModel, editorControlsModel,
                levelsListModel);

        // Initialize Canvases
        menuCanvas.init(mainMenuEnvironment);
        gameCanvas.init(gameEnvironment);
        editorCanvas.init(editorEnvironment);

        gameUpdateRunnable.init(gameEnvironment);
        gameRenderRunnable.init(gameCanvas);

        editorUpdateRunnable.init(editorEnvironment);
        editorRenderRunnable.init(editorCanvas);

        menuUpdateRunnable.init(mainMenuEnvironment);
        menuRenderRunnable.init(menuCanvas);

        // Initialize Environments Model with both Main Menu, Pause Game and Game AEnvironments
        environmentsHandler.addEnvironmentPair(
                mainMenuEnvironment, menuCanvas, menuUpdateRunnable, menuRenderRunnable);
        environmentsHandler.addEnvironmentPair(
                gameEnvironment, gameCanvas, gameUpdateRunnable, gameRenderRunnable);
        environmentsHandler.addEnvironmentPair(
                gamePauseMenuModel, gameCanvas, gameUpdateRunnable, gameRenderRunnable);
        environmentsHandler.addEnvironmentPair(
                editorEnvironment, editorCanvas, editorUpdateRunnable, editorRenderRunnable);
        environmentsHandler.addEnvironmentPair(
                editorPauseMenuModel, editorCanvas, editorUpdateRunnable, editorRenderRunnable);

        environmentsHandler.setCurrentEnvironmentType(EnvironmentType.MAIN_MENU);
        environmentsHandler.getCurrentEnvironment().onResume();

        // Initialize Window's Environment
        environmentsHandler.applyEnvironment();

        // Confirm and Apply scaling
        config.post();

    }

}
