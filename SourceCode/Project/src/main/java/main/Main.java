package main;

import graphics.ui.game.GameCanvas;
import graphics.ui.menu.MenuCanvas;
import models.controls.ControlsModel;
import models.data.PreferenceData;
import models.environments.game.GameModel;
import props.objects.levels.LevelList;
import models.environments.menus.MenusModel;
import utils.files.PreferencesXMLParser;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static ControlsModel controlsModel;

    private static MenusModel menuModel;
    private static GameModel gameModel;

    private static LevelList levelsModel;

    private static MenuCanvas menuCanvas;
    private static GameCanvas gameCanvas;

    private static MainWindow window;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

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

        // Create Models
        controlsModel = new ControlsModel();

        menuModel = new MenusModel();
        gameModel = new GameModel();

        levelsModel = new LevelList();

        // Create State Canvases
        menuCanvas = new MenuCanvas();
        gameCanvas = new GameCanvas();

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

        // Initialize Models
        controlsModel.init();
        gameModel.init(controlsModel, levelsModel);

        // Initialize Canvases
        gameCanvas.init(gameModel);
        menuCanvas.init(menuModel);

        // Initialize Window
        window.init(preferences, controlsModel);
        window.addEnvironmentWithCanvas(gameModel, gameCanvas);
        window.addEnvironmentWithCanvas(menuModel, menuCanvas);
        window.initEnvironmentAndCanvas(1);

        // Confirm and Apply scaling
        preferences.post();

    }

}
