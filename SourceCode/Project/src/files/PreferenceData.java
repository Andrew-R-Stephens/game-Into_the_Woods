package files;

import java.util.HashMap;

public class PreferenceData {

    private HashMap<String, Integer> values;

    private int window_width_default = 0;
    private int window_height_default = 0;

    private int window_width = 0;
    private int window_height = 0;
    private WindowType window_type;

    private int frameRate = 60;

    private double scaledW = 1;
    private double scaledH = 1;

    public double getScaledW() {
        return scaledW;
    }

    public double getScaledH() {
        return scaledH;
    }

    public void calcResScale() {
        this.scaledW = (double)window_width/window_width_default;
        this.scaledH = (double)window_height/window_height_default;
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

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public int getFrameRate() {
        return frameRate;
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
