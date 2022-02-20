package files;

import java.util.HashMap;

public class Preferences {

    private HashMap<String, Integer> values;

    private int window_width = 0;
    private int window_height = 0;
    private WindowType window_type;

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

    public void setWindowType(int type) {
        this.window_type = WindowType.values()[type];
    }

    public WindowType getWindowType() {
        return window_type;
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

    public String toString() {
        return window_width + " " + window_height + " " + window_type;
    }

}
