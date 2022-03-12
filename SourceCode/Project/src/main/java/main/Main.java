package main;

import graphics.ui.game.GameCanvas;
import graphics.ui.game.GameWindow;
import graphics.ui.menu.MainMenuCanvas;
import utils.files.PreferencesXMLParser;
import viewmodels.controls.ControlsModel;
import viewmodels.data.PreferenceData;
import viewmodels.states.game.GameModel;
import viewmodels.states.game.LevelModel;
import viewmodels.states.mainmenu.MainMenuModel;
import viewmodels.states.pausemenumodel.PauseMenuModel;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static ControlsModel controlsModel;

    private static MainMenuModel menuModel;
    private static PauseMenuModel pauseModel;
    private static GameModel gameModel;

    private static LevelModel levelModel;

    private static MainMenuCanvas mainMenuCanvas;
    private static GameCanvas gameCanvas;

    private static GameWindow gameWindow;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Create Objects
        create();

        // Initialize Objects
        init();

    }

    public static void create() {

        // Create Preferences
        preferences = new PreferenceData();

        // Create Models
        controlsModel = new ControlsModel();

        menuModel = new MainMenuModel();
        gameModel = new GameModel();

        levelModel = new LevelModel();

        // Create State Canvases
        mainMenuCanvas = new MainMenuCanvas();
        gameCanvas = new GameCanvas();

        // Create Window
        gameWindow = new GameWindow();

    }

    /**
     * Init game objects.
     */
    public static void init() {

        gameModel.init(controlsModel, levelModel);

        controlsModel.init();

        PreferencesXMLParser preferencesXMLParser =
                new PreferencesXMLParser(preferences, "files/", "Preferences", ".xml");
        preferencesXMLParser.read();

        gameCanvas.init(gameModel);
        gameWindow.init(preferences, gameCanvas, controlsModel);

        preferences.post();

    }

}
