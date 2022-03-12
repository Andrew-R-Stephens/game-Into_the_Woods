package props.objects.gameactors;

import props.prototypes.types.actor.pawn.APawn;

import java.awt.*;

/**
 * TODO: Add description
 */
public class TestActor extends APawn {

    public TestActor(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
