import files.Preferences;
import files.PreferencesXMLParser;
import graphics.ui.GameCanvas;
import graphics.ui.GameWindow;

/**
 * The type Main.
 */
public class Main {

    private static Preferences preferences;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Initialize Preferences
        initPreferenceFiles();

        // Initialize UI
        initWindow();


        // Initialize Game Models
        initGameObjects();

    }

    /**
     * Init preference files.
     */
    public static void initPreferenceFiles() {
        preferences = new Preferences();
        PreferencesXMLParser preferencesXMLParser = new PreferencesXMLParser(preferences, "Preferences.xml");
        preferencesXMLParser.read();
    }

    public static void initWindow() {
        GameCanvas gameCanvas = new GameCanvas();
        GameWindow gameWindow = new GameWindow(gameCanvas);
        gameWindow.init(preferences);
        //gameWindow.setVisible(true);
    }

    /**
     * Init game files.
     */
    public static void initGameFiles() {

    }


    /**
     * Init game objects.
     */
    public static void initGameObjects() {

    }

}
