package objects.types.actor.pawn.character;

import objects.types.actor.pawn.APawn;
import utils.IDrawable;

public class ACharacter extends APawn implements IDrawable {

    protected ACharacter(float x, float y, float w, float h, float vx, float vy, float MIN_VELX, float MIN_VELY, float MAX_VELX, float MAX_VELY, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, MIN_VELX, MIN_VELY, MAX_VELX, MAX_VELY, hasGravity, mass);
    }



}
