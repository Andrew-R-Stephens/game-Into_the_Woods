package props.gameactors;

import data.PreferenceData;
import proptypes.types.actor.pawn.APawn;

import java.awt.*;

public class TestActor extends APawn {

    public TestActor(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public void update(double delta) {
        super.update(delta);

        x += vX / delta;
        y += vY / delta;

    }

    @Override
    public void draw(Graphics g) {
        double sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(Color.RED);
        g.drawRect((int) ((x) - (w*sW/2)), (int) ((y) - (h*sH/2)), (int) (w*sW), (int) (h*sH));
    }
}
