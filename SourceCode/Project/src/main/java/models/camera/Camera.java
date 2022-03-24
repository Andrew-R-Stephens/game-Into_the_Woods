package models.camera;

import models.data.PreferenceData;

/**
 * The type Camera.
 */
public class Camera {

    public static float zoomLevel = 1;
    public static float x = 0, y = 0;

    public static double acceleration = .05;

    /**
     * Instantiates a new Camera.
     */
    public Camera() {
        x = PreferenceData.window_width_actual * .5f;
        y = PreferenceData.window_height_actual * .5f;
    }

    /**
     * Move to.
     *
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public static void moveTo(float x2, float y2) {
        x += (x2 - x) * acceleration;
        y += (y2 - y) * acceleration;
    }

    /**
     * Gets off x.
     *
     * @param ox the ox
     * @param ow the ow
     * @return the off x
     */
    public static double getOffX(double ox, double ow) {
        return ((PreferenceData.window_width_actual * .5) - (ow * PreferenceData.scaledW)) - (ox * PreferenceData.scaledW);
    }

}
