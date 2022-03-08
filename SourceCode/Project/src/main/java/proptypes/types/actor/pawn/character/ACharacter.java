package proptypes.types.actor.pawn.character;

import proptypes.types.actor.pawn.APawn;
import utils.IDrawable;
import viewmodels.controls.ControlsModel;

public class ACharacter extends APawn implements IDrawable {

    private ControlsModel controlsModel;

    private boolean isJumpLocked = false;
    private final int MAX_ALLOWED_JUMP_TIME = 10;
    private int jumpTime = MAX_ALLOWED_JUMP_TIME;

    private final float MAX_ALLOWED_WALLRIDE_TIME = .7f;
    private float wallrideTime = MAX_ALLOWED_JUMP_TIME;

    protected ACharacter(ControlsModel cModel, float x, float y, float w, float h, float vx, float vy, boolean hasGravity, float mass) {
        super(x, y, w, h, vx, vy, hasGravity, mass);
        this.controlsModel = cModel;
    }

    protected void update(double delta) {
        super.update(delta);
    }

    public void control() {
        doAbilitiy();
        doMove();
    }

    public void doMove() {

        boolean[] directionals = controlsModel.getDirectionals();

        int xDir = (directionals[0] ? -1 : 0) + (directionals[1] ? 1 : 0);
        int yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

        isUserControlled = directionals[0] || directionals[1] || directionals[2] || directionals[3];

        // If control direction goes against character movement direction, slow velocity down
        if(vX * xDir < 0) {
            vX *= .85; //.95
        }

        // Handle wall collisions with control input considered
        if((xDir < 0 && isWallCollisionLeft) || (xDir > 0 && isWallCollisionRight)) {
            //Decrement y velocity using time
            vY -= (vY * wallrideTime);
            if(wallrideTime > 0) {
                wallrideTime -= .05f;
            }
        } else {
            // If jumping, reset the wallride
            if(isJumpLocked) {
                wallrideTime = MAX_ALLOWED_WALLRIDE_TIME;
            }
        }

        // Increment by a specific velocity from control input
        vX += xDir;
        vY += yDir;
    }

    protected void doAbilitiy() {

        boolean[] abilities = controlsModel.getAbilities();

        if(jumpTime > 0) {
            jumpTime--;
        }

        if(abilities[0]) {

            if(isJumpLocked) {
                return;
            }

            if (jumpTime > 0 && (isFloorCollision || isWallCollisionLeft || isWallCollisionRight)) {

                lockJumpState(true);

                vY = -50;

                if(isFloorCollision) {

                    if(!isUserControlled) {
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

        } else {

            lockJumpState(false);

        }

    }

    private void lockJumpState(boolean state) {
        isJumpLocked = state;

        jumpTime = MAX_ALLOWED_JUMP_TIME;
    }

}
