import files.Preferences;
import files.PreferencesXMLParser;
import graphics.ui.GameCanvas;
import graphics.ui.GameWindow;
import viewmodels.GameModel;

/**
 * The type Main.
 */
public class Main {

    private static Preferences preferences;

    private static GameModel gameModel;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Initialize Preferences
        initPreferenceFiles();

        // Initialize Game Models
        initViewModels();

        // Initialize UI
        initWindow();

    }

    /**
     * Init preference files.
     */
    public static void initPreferenceFiles() {
        preferences = new Preferences();
        PreferencesXMLParser preferencesXMLParser = new PreferencesXMLParser(preferences, "Preferences.xml");
        preferencesXMLParser.read();
        preferences.postInit();
    }

    /**
     * Init game objects.
     */
    public static void initViewModels() {
        gameModel = new GameModel();
        gameModel.init(preferences);
    }

    public static void initWindow() {
        GameCanvas gameCanvas = new GameCanvas(gameModel);
        GameWindow gameWindow = new GameWindow(gameCanvas);
        gameWindow.init(preferences);
    }



}
