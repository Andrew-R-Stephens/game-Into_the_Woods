package utils.math;

/**
 * TODO: Add description
 */
public class Vector {

    public double x, y;

    public double getDistance(Vector v) {
        return Math.sqrt(Math.pow(v.x - x, 2) + Math.pow(v.y + y, 2));
    }

}
