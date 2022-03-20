package main;

import graphics.ui.game.GameCanvas;
import graphics.ui.menu.MenuCanvas;
import models.controls.game.GameControlsModel;
import models.controls.game.GameKeyControls;
import models.controls.game.GameMouseControls;
import models.controls.menu.MenuControlsModel;
import models.controls.menu.MenuKeyControls;
import models.controls.menu.MenuMouseControls;
import models.data.PreferenceData;
import models.environments.game.GameModel;
import models.environments.menus.mainmenu.MainMenuModel;
import props.objects.levels.LevelList;
import props.threads.gameloop.GameRenderRunnable;
import props.threads.gameloop.GameUpdateRunnable;
import props.threads.menuloop.MenuRenderRunnable;
import props.threads.menuloop.MenuUpdateRunnable;
import utils.files.PreferencesXMLParser;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static EnvironmentsModel environmentsModel;

    private static GameControlsModel gameControlsModel;
    private static MenuControlsModel menuControlsModel;

    private static MainMenuModel mainMenuModel;
    private static GameModel gameModel;

    private static GameUpdateRunnable gameUpdateRunnable;
    private static GameRenderRunnable gameRenderRunnable;

    private static MenuUpdateRunnable menuUpdateRunnable;
    private static MenuRenderRunnable menuRenderRunnable;

    private static LevelList levelsListModel;

    private static MenuCanvas mainMenuCanvas;
    private static GameCanvas gameCanvas;

    private static MainWindow window;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Load Images, Files, etc
        loadAssets();

        // Create Objects
        create();

        // Initialize Objects
        init();

    }

    public static void loadAssets() {
        
    }

    public static void create() {

        // Create Preferences
        preferences = new PreferenceData();

        // Create AEnvironment Model Container
        environmentsModel = new EnvironmentsModel();

        // Create AEnvironment Models
        gameControlsModel = new GameControlsModel();
        menuControlsModel = new MenuControlsModel();

        // Create Menu Models
        mainMenuModel = new MainMenuModel();
        //menusListModel = new MenusListModel();

        // Create Game Models
        levelsListModel = new LevelList();
        gameModel = new GameModel();

        // Create State Canvases
        mainMenuCanvas = new MenuCanvas();
        gameCanvas = new GameCanvas();

        gameUpdateRunnable = new GameUpdateRunnable();
        gameRenderRunnable = new GameRenderRunnable();
        menuUpdateRunnable = new MenuUpdateRunnable();
        menuRenderRunnable = new MenuRenderRunnable();

        // Create Window
        window = new MainWindow();

    }

    /**
     * Init game objects.
     */
    public static void init() {

        // Initialize Preferences
        PreferencesXMLParser preferencesParser =
                new PreferencesXMLParser(preferences, "files/", "Preferences", ".xml");
        preferencesParser.read();

        // Initialize Control Models
        gameControlsModel.init(new GameMouseControls(gameControlsModel), new GameKeyControls(gameControlsModel));
        menuControlsModel.init(new MenuMouseControls(menuControlsModel), new MenuKeyControls(menuControlsModel));

        // Initialize Levels
        levelsListModel.init(gameModel);

        // Initialize AEnvironment Model Container
        environmentsModel.init(window);

        // Initialize AEnvironment Models
        mainMenuModel.init(environmentsModel, menuControlsModel);
        gameModel.init(environmentsModel, gameControlsModel, levelsListModel);

        // Initialize Canvases
        mainMenuCanvas.init(mainMenuModel);
        gameCanvas.init(gameModel);

        gameUpdateRunnable.init(gameModel);
        gameRenderRunnable.init(gameCanvas);
        menuUpdateRunnable.init(mainMenuModel);
        menuRenderRunnable.init(mainMenuCanvas);

        // Initialize Environments Model with Main Menu and Game AEnvironments
        environmentsModel.addPair(mainMenuModel, mainMenuCanvas, menuUpdateRunnable, menuRenderRunnable);
        environmentsModel.addPair(gameModel, gameCanvas, gameUpdateRunnable, gameRenderRunnable);

        // Initialize Window with Preference Data
        window.init(preferences);

        // Initialize Window's Environment
        window.initEnvironmentsModel(environmentsModel);
        window.applyEnvironmentAndCanvas(EnvironmentsModel.EnvironmentType.MAIN_MENU);

        // Confirm and Apply scaling
        preferences.post();

    }

}
