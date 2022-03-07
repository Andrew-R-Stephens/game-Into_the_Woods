package proptypes.types.actor.pawn.character;

import proptypes.types.actor.pawn.APawn;
import utils.IDrawable;

public class ACharacter extends APawn implements IDrawable {

    protected ACharacter(float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
    }

    public void control(boolean[] directionals, boolean[] abilities) {

        doAbilitiy(abilities);
        control(directionals);

    }

    @Override
    protected void update(double delta) {
        super.update(delta);
    }

    public void control(boolean[] directionals) {

        int xDir = (directionals[0] ? -1 : 0) + (directionals[1] ? 1 : 0);
        int yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

        isUnderControl = directionals[0] || directionals[1] || directionals[2] || directionals[3];

        // If control direction goes against character movement direction, slow velocity down
        if(vX * xDir < 0) {
            vX *= .95;
        }

        vX += xDir;
        vY += yDir;
    }

    protected void doAbilitiy(boolean[] abilities) {

        if(abilities[0]) {

            if (isFloorCollision || isWallCollisionLeft || isWallCollisionRight) {
                vY = -50;

                if(isFloorCollision) {

                    if(!isUnderControl) {
                        vX *= .25;
                    }
                    isFloorCollision = false;

                }
                if (isWallCollisionLeft) {
                    vX = 10;
                    isWallCollisionLeft = false;
                }
                if (isWallCollisionRight) {
                    vX = -10;
                    isWallCollisionRight = false;
                }

            }

        }

    }

}
