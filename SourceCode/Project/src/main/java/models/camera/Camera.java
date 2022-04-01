package models.camera;

import utils.config.ConfigData;

/**
 * The type Camera.
 */
public class Camera {

    public static float zoomLevel = 1.0f;
    public static float x = 0, y = 0;

    public static double acceleration = .05;

    /**
     * Instantiates a new Camera.
     */
    public Camera() {
        x = ConfigData.window_width_actual * .5f;
        y = ConfigData.window_height_actual * .5f;
    }

    /**
     * Move to.
     *
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public static void moveTo(float x2, float y2) {
        // Casting these to int rounds the translation, which helps mitigate render misalignment issues
        x += ((x2 - x) * acceleration);
        y += ((y2 - y) * acceleration);

        //x += (x2 - x) * Math.abs(((x2 - x) / x2));
        //y += (y2 - y) * Math.abs(((y2 - y) / y2));
    }

    /**
     * Gets off x.
     *
     * @param ox the ox
     * @param ow the ow
     * @return the off x
     */
    public static double getOffX(double ox, double ow) {
        return ((ConfigData.window_width_actual * .5) - (ow * ConfigData.scaledW)) - (ox * ConfigData.scaledW);
    }

}
