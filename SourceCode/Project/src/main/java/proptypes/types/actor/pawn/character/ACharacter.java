package proptypes.types.actor.pawn.character;

import proptypes.types.actor.pawn.APawn;
import utils.IDrawable;

public class ACharacter extends APawn implements IDrawable {

    protected ACharacter(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    public void control(boolean[] directionals, boolean[] abilities) {

        doAbilitiy(abilities);
        move(directionals);

    }

    public void move(boolean[] directionals) {

        int xDir = (directionals[0] ? -1 : 0) + (directionals[1] ? 1 : 0);
        int yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

        vX += GRAVITY*xDir;
        vY += GRAVITY*yDir;

    }

    protected void doAbilitiy(boolean[] abilities) {
        if((canPrimaryJump || canWallJump) && abilities[0]) {
            vY -= 50;
        }

    }

}
