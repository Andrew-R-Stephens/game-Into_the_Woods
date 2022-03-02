package proptypes.types.actor;

import utils.IDrawable;
import utils.math.APhysics;

/**
 *
 */
public abstract class AActor extends APhysics implements IDrawable {

    protected AActor(float x, float y,
                     float w, float h,
                     float vx, float vy,
                     float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY,
                     boolean hasGravity,
                     float mass) {

        super(x, y, w, h, vx, vy, MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY, hasGravity, mass);

    }

    @Override
    protected void update(double delta) {
        super.update(delta);

    }

}
