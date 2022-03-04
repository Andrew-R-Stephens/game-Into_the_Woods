package props.gameactors;

import proptypes.types.actor.pawn.character.ACharacter;

import java.awt.*;

public class TestCharacter extends ACharacter {

    public TestCharacter(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    @Override
    public void update(double delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.BLUE);
        g.drawRect((int)x, (int)y, (int)w, (int)h);
    }

}
