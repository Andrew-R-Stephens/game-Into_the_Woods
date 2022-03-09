package proptypes.types.actor.trigger;

import proptypes.types.actor.pawn.APawn;

/**
 * TODO: Add description
 */
public abstract class ATrigger extends APawn {

    protected ATrigger(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public void update(double delta) {


    }

}
