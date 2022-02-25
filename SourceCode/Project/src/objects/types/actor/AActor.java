package objects.types.actor;

import utils.IDrawable;
import utils.math.APhysics;

/**
 *
 */
public abstract class AActor extends APhysics implements IDrawable {

    protected double x, y;
    protected double w, h;

    protected AActor(float x, float y,
                     float w, float h,
                     float vx, float vy,
                     float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY,
                     boolean hasGravity,
                     float mass) {

        super(vx, vy, MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY, hasGravity, mass);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    protected void update(double delta) {
        super.update(delta);

        x += vX;
        y += vY;
    }

}
