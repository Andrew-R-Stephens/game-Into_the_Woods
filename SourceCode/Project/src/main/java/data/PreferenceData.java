package data;

public class PreferenceData {

    public static int DEFAULT_WINDOW_WIDTH = 0;
    public static int DEFAULT_WINDOW_HEIGHT = 0;

    public static int window_width = 0;
    public static int window_height = 0;
    private WindowType window_type;

    public static short FRAMERATE_DEFAULT = 60;
    public static short frameRate = 60;

    public static double scaledW = 1;
    public static double scaledH = 1;

    public void calcResScale() {
        scaledW = (double)window_width/ DEFAULT_WINDOW_WIDTH;
        scaledH = (double)window_height/ DEFAULT_WINDOW_HEIGHT;
    }

    public void setWindowWidth(int width) {
        this.window_width = width;
    }

    public int getWindowWidth() {
        return window_width;
    }

    public void setWindowHeight(int height) {
        this.window_height = height;
    }

    public int getWindowHeight() {
        return window_height;
    }

    public void setWindowWidthDefault(int width) {
        this.DEFAULT_WINDOW_WIDTH = width;
    }

    public void setWindowHeightDefault(int height) {
        this.DEFAULT_WINDOW_HEIGHT = height;
    }

    public void setWindowType(int type) {
        this.window_type = WindowType.values()[type];
    }

    public WindowType getWindowType() {
        return window_type;
    }

    public void setFrameRate(short frameRate) {
        this.frameRate = frameRate;
    }

    public short getFrameRate() {
        return frameRate;
    }

    public void setFrameRateDefault(short framerateDefault) {
        FRAMERATE_DEFAULT = framerateDefault;
    }

    public enum WindowType {
        WINDOWED(0, "Windowed"), WINDOWED_FULLSCREEN(1, "Windowed Fullscreen"), FULLSCREEN(2, "Fullscreen");

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
        return window_width + " " + window_height + " " + window_type;
    }

}
