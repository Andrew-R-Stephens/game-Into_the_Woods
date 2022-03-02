package data;

public class PreferenceData {

    private int window_width_default = 0;
    private int window_height_default = 0;

    private int window_width = 0;
    private int window_height = 0;
    private WindowType window_type;

    public static short FRAMERATE_DEFAULT = 60;
    public static short frameRate = 60;

    public static double scaledW = 1;
    public static double scaledH = 1;

    public void calcResScale() {
        scaledW = (double)window_width/window_width_default;
        scaledH = (double)window_height/window_height_default;
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
        this.window_width_default = width;
    }

    public void setWindowHeightDefault(int height) {
        this.window_height_default = height;
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
