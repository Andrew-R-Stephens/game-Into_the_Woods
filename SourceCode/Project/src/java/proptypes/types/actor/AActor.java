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

    public float[] getCollisions(AActor o) {
        float[] c = new float[4];

        c[0] = o.vX - vX;
        c[1] = o.vY - vY;

        if(o.right() > left() && o.left() < right()) {
            c[2] = left() - o.right();
        } else {
            if(o.right() < left() && o.left() > right()) {
                c[2] = o.left() - right();
            }
        }

        if(o.bottom() > top() && o.top() < bottom()) {
            c[3] = top() - o.bottom();
        } else {
            if(o.bottom() < top() && o.top() > bottom()) {
                c[3] = o.top() - bottom();
            }
        }

        return c;
    }
}
