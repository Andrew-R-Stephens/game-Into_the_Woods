package props.gameactors;

import data.PreferenceData;
import proptypes.types.actor.AActor;
import proptypes.types.actor.pawn.character.ACharacter;

import java.awt.*;

public class TestCharacter extends ACharacter {

    protected Color c = Color.BLUE;

    public TestCharacter(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public boolean hasCollision(AActor a) {
        if(super.hasCollision(a)) {
            c = Color.RED;
            return true;
        }
        c = Color.BLUE;
        return false;
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        double sW = PreferenceData.scaledW, sH = PreferenceData.scaledH;

        g.setColor(c);
        g.drawRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)(h*sH));
        g.drawString("TC", (int)(x*sW) + 3, (int)(y*sH) + 12);
    }

}
