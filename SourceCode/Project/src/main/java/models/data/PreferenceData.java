package models.data;

/**
 * Preference Data stores the fundamental information of user-defined preferences.
 */
public class PreferenceData {

    public static int DEFAULT_WINDOW_WIDTH = 1920;
    public static int DEFAULT_WINDOW_HEIGHT = 1080;

    public static int window_width_selected = 0;
    public static int window_height_selected = 0;

    public static int window_width_actual = 0;
    public static int window_height_actual = 0;

    public static float scaledW = 1;
    public static float scaledH = 1;

    private static WindowType window_type = WindowType.WINDOWED;

    public static short GAME_UPDATE_RATE = 60;
    public static short FRAME_RATE_DEFAULT = 60;
    public static short frameRate = 60;


    public void calcResScale() {
        scaledW = (float)window_width_actual / (float) DEFAULT_WINDOW_WIDTH;
        scaledH = (float)window_height_actual / (float) DEFAULT_WINDOW_HEIGHT;
    }

    public void setWindowWidthSelected(int width) {
        window_width_selected = width;
    }

    public int getWindowWidthSelected() {
        return window_width_selected;
    }

    public void setWindowHeightSelected(int height) {
        window_height_selected = height;
    }

    public int getWindowHeightSelected() {
        return window_height_selected;
    }

    public void setWindowWidthActual(int width) {
        window_width_actual = width;
    }

    public void setWindowHeightActual(int height) {
        window_height_actual = height;
    }

    public void setWindowWidthDefault(int width) {
        DEFAULT_WINDOW_WIDTH = width;
    }

    public int getWindowWidthDefault() {
        return DEFAULT_WINDOW_WIDTH;
    }

    public void setWindowHeightDefault(int height) {
        DEFAULT_WINDOW_HEIGHT = height;
    }

    public int getWindowHeightDefault() {
        return DEFAULT_WINDOW_HEIGHT;
    }

    public void setWindowType(int type) {
        window_type = WindowType.values()[type];
    }

    public WindowType getWindowType() {
        return window_type;
    }

    public void setGameUpdateRate(short updateRate) {
        GAME_UPDATE_RATE = updateRate;
    }

    public void setFrameRate(short frameRate) {
        this.frameRate = frameRate;
    }

    public short getFrameRate() {
        return frameRate;
    }

    public void setFrameRateDefault(short framerateDefault) {
        FRAME_RATE_DEFAULT = framerateDefault;
    }



    public enum WindowType {
        WINDOWED(0, "Windowed"),
        WINDOWED_BORDERLESS(1, "Windowed Borderless"),
        WINDOWED_FULLSCREEN(2, "Windowed Fullscreen"),
        FULLSCREEN(3, "Fullscreen");

        private int type;
        private String name;

        WindowType(int type, String name){
            this.type = type;
            this.name = name;
        }
    }

    public void post() {
        calcResScale();
    }

    public String toString() {
        return window_width_selected + " " + window_height_selected + " " + window_type;
    }

}
