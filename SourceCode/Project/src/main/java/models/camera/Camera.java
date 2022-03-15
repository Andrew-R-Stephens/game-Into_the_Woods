package models.camera;

import models.data.PreferenceData;

public class Camera {

    public static float zoomLevel = 1;
    public static float x = 0, y = 0;

    public static double acceleration = .05;

    public Camera() {
        x = PreferenceData.window_width_actual * .5f;
        y = PreferenceData.window_height_actual * .5f;
    }

    public static void moveTo(float x2, float y2) {
        x += (x2 - x) * acceleration;
        y += (y2 - y) * acceleration;
    }

    public static double getOffX(double ox, double ow) {
        return ((PreferenceData.window_width_actual * .5) - (ow * PreferenceData.scaledW)) - (ox * PreferenceData.scaledW);
    }

}
