package props.prototypes.types.actor.trigger;

import props.prototypes.types.actor.pawn.APawn;

/**
 * TODO: Add description
 */
public abstract class ATrigger extends APawn {

    protected ATrigger(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void update(float delta) {


    }

}
