package proptypes.types.actor.pawn.character;

import proptypes.types.actor.pawn.APawn;
import utils.IDrawable;

public class ACharacter extends APawn implements IDrawable {

    protected ACharacter(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }



}
