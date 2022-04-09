package models.prototypes.actor.pawn.character;

import models.sprites.SpriteSheet;
import controls.GameControls;
import models.prototypes.actor.pawn.APawn;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.util.HashMap;

public abstract class ACharacter extends APawn implements IUpdatable {

    protected final GameControls controlsModel;

    protected CharacterType characterType;
    public enum CharacterType {
        MALE,
        FEMALE
    }

    public ActionType actionState = ActionType.FLOOR_IDLE;
    public enum ActionType {
        FLOOR_IDLE,
        FLOOR_WALKING,
        FLOOR_RUNNING,
        FLOOR_JUMPING,
        WALL_CLIMBING,
        WALL_JUMPING
    }

    private boolean isJumpLocked = false;
    private final int MAX_ALLOWED_JUMP_TIME = 10;
    private int jumpTime = MAX_ALLOWED_JUMP_TIME;

    private final float MAX_ALLOWED_WALLRIDE_TIME = .7f;
    private float wallrideTime = MAX_ALLOWED_JUMP_TIME;

    public HashMap<ActionType, SpriteSheet> spriteSheets = new HashMap<>();

    protected ACharacter(GameControls cModel, float x, float y, float w, float h, float vx, float vy,
                         boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
        this.controlsModel = cModel;
    }

    @Override
    public void update(float delta) {
        setActionType();

        super.update(delta);
    }

    public void control(float delta) {
        doAbilitiy(delta);
        doMove(delta);
    }

    public void doMove(float delta) {

        boolean[] directionals = controlsModel.getDirectionals();

        // SET xDir TO ZERO, NEGATIVE, OR POSITIVE BASED ON CONTROL DIRECTION
        float xDir = (directionals[0] ? -1 : 0) + (directionals[1] ? 1 : 0);
        //int yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

        if(!(isFloorCollision || isWallCollisionLeft || isWallCollisionRight)) {
            xDir *= .8;
        }

        isUserControlled = directionals[0] || directionals[1] || directionals[2] || directionals[3];

        // If control direction goes against character movement direction, slow velocity down
        if (vX * xDir < 0) {
            vX *= .85; //.95
        }

        // MULTIPLY BASE MOVEMENT SPEED BY DIRECTION MOVED
        xDir *= MAX_VEL_X;

        // Handle wall collisions with control input considered
        if ((xDir < 0 && isWallCollisionLeft) || (xDir > 0 && isWallCollisionRight)) {
            //Decrement y velocity using time
            vY -= (vY * wallrideTime);
            if (wallrideTime > 0) {
                wallrideTime -= .05f;
            }
        } else {
            // If jumping, reset the wallride
            if (isJumpLocked) {
                wallrideTime = MAX_ALLOWED_WALLRIDE_TIME;
            }
        }

        // Increment by a specific velocity from control input
        vX += xDir / (float) ConfigData.GAME_UPDATE_RATE / delta;
        //vY += yDir / (float)PreferenceData.GAME_UPDATE_RATE;
    }

    protected void doAbilitiy(float delta) {

        boolean[] abilities = controlsModel.getAbilities();

        if (jumpTime > 0) {
            jumpTime--;
        }

        if (abilities[0]) {

            if (isJumpLocked) {
                return;
            }

            if (jumpTime > 0) {

                lockJumpState(true);

                if (isFloorCollision) {
                    vY = -5;
                    actionState = ActionType.FLOOR_JUMPING;

                    //System.out.println("Floor Jump");
                } else {
                    if (isWallCollisionLeft) {
                        doWallJump(5, -5);

                        isWallCollisionLeft = false;
                        //System.out.println("Wall Left Jump");
                        actionState = ActionType.WALL_JUMPING;
                    } else
                    if (isWallCollisionRight) {
                        doWallJump(-5, -5);

                        isWallCollisionRight = false;
                        //System.out.println("Wall Right Jump");
                        actionState = ActionType.WALL_JUMPING;
                    }
                }
                isFloorCollision = false;
            }
        } else {
            lockJumpState(false);
        }

    }

    public void doWallJump(float vX, float vY) {
        this.vX = vX;
        this.vY = vY;
    }

    private void lockJumpState(boolean state) {
        isJumpLocked = state;

        jumpTime = MAX_ALLOWED_JUMP_TIME;
    }

    public void setCharacterType(CharacterType type) {
        this.characterType = type;
    }

    private void setActionType() {
        if(isFloorCollision) {
            if (!isUserControlled) {
                if(Math.abs(vX) == 0) {
                    actionState = ActionType.FLOOR_IDLE;
                }
            } else {
                actionState = ActionType.FLOOR_RUNNING;
            }
        }
        if(isWallCollisionRight || isWallCollisionLeft) {
            if(!isFloorCollision) {
                actionState = ActionType.WALL_CLIMBING;
            }
        }
    }

    public void reset(int[] characterOrigin) {
        setVelocity(0, 0);
        x = characterOrigin[0];
        y = characterOrigin[1];
    }

}
