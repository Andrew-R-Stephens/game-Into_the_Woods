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

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        /*double sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(Color.RED);
        g.drawRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)(h*sH));*/
    }
}
