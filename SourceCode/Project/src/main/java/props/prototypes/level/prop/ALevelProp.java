package props.prototypes.level.prop;

import props.prototypes.actor.AActor;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class ALevelProp extends AActor {

    protected ALevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}
