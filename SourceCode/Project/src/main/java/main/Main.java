package main;

import controls.GameControls;
import controls.MenuControls;
import controls.game.GameKeyControls;
import controls.game.GameMouseControls;
import controls.menu.MenuKeyControls;
import controls.menu.MenuMouseControls;
import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.environments.game.hud.HUDModel;
import models.environments.game.hud.components.MapOverlay;
import models.environments.game.hud.components.PlayerStatsOverlay;
import models.environments.game.hud.components.TimeKeeperOverlay;
import models.environments.game.playerinventory.PlayerInventory;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.environments.menus.pausemenumodel.PauseMenuEnvironment;
import models.levels.LevelsList;
import models.runnables.RenderRunnable;
import models.runnables.UpdateRunnable;
import models.utils.config.Config;
import models.utils.files.PreferencesParser;
import models.utils.resources.Resources;
import views.canvas.game.GameCanvas;
import views.canvas.menu.MenuCanvas;
import views.window.MainWindow;

/**
 * The launching class.
 *
 * Includes static references to all root-level objects.
 * Handles the initialization of all root-level objects.
 */
public class Main {

    private static Config config;
    private static Resources resources;

    private static EnvironmentsHandler environmentsHandler;

    private static MapOverlay mapOverlay;
    private static PlayerStatsOverlay playerStatsOverlay;
    private static TimeKeeperOverlay timeKeeperOverlay;

    private static HUDModel hudModel;
    private static PlayerInventory inventory;

    private static GameControls gameControlsModel;
    private static MenuControls menuControlsModel;

    private static MainMenuEnvironment mainMenuEnvironment;
    private static GameEnvironment gameEnvironment;

    private static PauseMenuEnvironment pauseMenuModel;

    private static UpdateRunnable gameUpdateRunnable;
    private static RenderRunnable gameRenderRunnable;

    private static UpdateRunnable menuUpdateRunnable;
    private static RenderRunnable menuRenderRunnable;

    private static LevelsList levelsListModel;

    private static MenuCanvas menuCanvas;
    private static GameCanvas gameCanvas;

    private static MainWindow window;

    /**
     * The starting point of the program.<br><br>
     * First loads all assets from file into a resource pool for later use and ease of access.<br>
     * Then Creates all permanent static variables for protection against null pointer exceptions.<br>
     * Then initializes all variables with their necessary reference objects. Assists in MVC cross-object communication.
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
     * Handles the loading of all assets from files into a resource pool for later use and ease of access.
     */
    public static void loadAssets() {

        // Create Preferences
        config = new Config();

        resources = new Resources();
        resources.init();

    }

    /**
     * Creates all permanent static variables for protection against null pointer exceptions.
     */
    public static void create() {


        // Create Environment Handler
        environmentsHandler = new EnvironmentsHandler();

        // Create Control Models
        gameControlsModel = new GameControls();
        menuControlsModel = new MenuControls();

        // Create HUD Model Components
        mapOverlay = new MapOverlay();
        playerStatsOverlay = new PlayerStatsOverlay();
        timeKeeperOverlay = new TimeKeeperOverlay();

        // Create Game Environment Models
        hudModel = new HUDModel();
        inventory = new PlayerInventory();

        // Create Menu Environment
        mainMenuEnvironment = new MainMenuEnvironment();
        // Create Game Environment
        gameEnvironment = new GameEnvironment();

        // Create Pause Menu for Game Environment
        pauseMenuModel = new PauseMenuEnvironment();

        // Create Levels List Model
        levelsListModel = new LevelsList();

        // Create State Canvases
        menuCanvas = new MenuCanvas();
        gameCanvas = new GameCanvas();

        gameUpdateRunnable = new UpdateRunnable();
        gameRenderRunnable = new RenderRunnable();
        menuUpdateRunnable = new UpdateRunnable();
        menuRenderRunnable = new RenderRunnable();

        // Create Window
        window = new MainWindow();

    }

    /**
     * Handles the initializes all variables with their necessary reference objects.<br>
     * Assists in MVC cross-object referencing.
     */
    public static void init() {

        // Reference resources
        mainMenuEnvironment.setResources(resources);
        pauseMenuModel.setResources(resources);
        gameEnvironment.setResources(resources);
        window.setResources(resources);
        // Initialize Preferences
        new PreferencesParser(config, resources.loadTextFile("preferences.xml")).parse();

        // Initialize Window with Preference Data
        window.init(environmentsHandler);

        // Initialize Control Models
        gameControlsModel.init(new GameMouseControls(gameControlsModel), new GameKeyControls(gameControlsModel));
        menuControlsModel.init(new MenuMouseControls(menuControlsModel), new MenuKeyControls(menuControlsModel));

        // Initialize Levels
        int defaultLevel = 0;
        levelsListModel.init(gameEnvironment, defaultLevel);

        // Initialize AEnvironment Model Container
        environmentsHandler.init(window);

        // Initialize Pause Menu
        pauseMenuModel.init(environmentsHandler, menuControlsModel, gameEnvironment);

        hudModel.init(gameEnvironment, inventory, mapOverlay, playerStatsOverlay, timeKeeperOverlay);

        // Initialize AEnvironment Models
        mainMenuEnvironment.init(environmentsHandler, menuControlsModel);
        gameEnvironment.init(
                environmentsHandler,
                pauseMenuModel, gameControlsModel,
                levelsListModel, hudModel, inventory);

        // Initialize Canvases
        menuCanvas.init(mainMenuEnvironment);
        gameCanvas.init(gameEnvironment);

        gameUpdateRunnable.init(gameEnvironment);
        gameRenderRunnable.init(gameCanvas);
        menuUpdateRunnable.init(mainMenuEnvironment);
        menuRenderRunnable.init(menuCanvas);

        // Initialize Environments Model with both Main Menu, Pause Game and Game AEnvironments
        environmentsHandler.addEnvironmentPair(
                mainMenuEnvironment, menuCanvas, menuUpdateRunnable, menuRenderRunnable);
        environmentsHandler.addEnvironmentPair(
                gameEnvironment, gameCanvas, gameUpdateRunnable, gameRenderRunnable);
        environmentsHandler.addEnvironmentPair(
                pauseMenuModel, gameCanvas, gameUpdateRunnable, gameRenderRunnable);
        environmentsHandler.setCurrentEnvironmentType(EnvironmentsHandler.EnvironmentType.MAIN_MENU);
        environmentsHandler.getCurrentEnvironment().onResume();

        // Initialize Window's Environment
        environmentsHandler.applyEnvironment();

        // Confirm and Apply scaling
        config.post();

    }

}
