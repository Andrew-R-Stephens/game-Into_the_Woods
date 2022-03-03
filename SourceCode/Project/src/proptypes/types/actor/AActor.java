package proptypes.types.actor;

import props.gameactors.TestActor;
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

    public float[] getCollisions(TestActor a2) {
        float[] side = new float[2];
        if((x+w) > (a2.x)){

            side[0] = x + w - a2.x;

            if((a2.x+a2.w) > (x)){

                side[0] = a2.x + a2.w - x;

                if ((y + h) > (a2.y)) {

                    side[1] = y + h - a2.y;

                    if((a2.y + a2.h) > (y)) {

                        side[1] = a2.y + a2.h - y;

                    }
                }
            }
        }
        return side;
    }
}
