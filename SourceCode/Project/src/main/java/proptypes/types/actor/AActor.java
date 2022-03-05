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
                     boolean hasGravity,
                     float mass) {

        super(x, y, w, h, vx, vy, hasGravity, mass);

    }

    @Override
    protected void update(double delta) {
        super.update(delta);
    }

}
