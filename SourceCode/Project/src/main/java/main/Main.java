package main;

import data.PreferenceData;
import graphics.ui.GameCanvas;
import graphics.ui.GameWindow;
import utils.PreferencesXMLParser;
import viewmodels.controls.ControlsViewModel;
import viewmodels.game.GameModel;

/**
 * The type main.Main.
 */
public class Main {

    private static PreferenceData preferences;

    private static ControlsViewModel controlsViewModel;
    private static GameModel gameViewModel;

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
        controlsViewModel = new ControlsViewModel();
        gameViewModel = new GameModel();

        preferences = new PreferenceData();

        gameCanvas = new GameCanvas();
        gameWindow = new GameWindow();
    }

    /**
     * Init game objects.
     */
    public static void init() {
        gameViewModel.init(controlsViewModel);
        controlsViewModel.init();

        PreferencesXMLParser preferencesXMLParser = new PreferencesXMLParser(preferences, "files/Preferences.xml");
        preferencesXMLParser.read();
        preferences.post();

        gameCanvas.init(gameViewModel);
        gameWindow.init(preferences, gameCanvas, controlsViewModel);
    }

}
