package models.prototypes.actor.pawn.character;

import controls.GameControls;
import models.prototypes.actor.pawn.APawn;
import models.sprites.SpriteSheet;
import models.utils.config.Config;
import models.utils.updates.IUpdatable;

import java.util.HashMap;

/**
 * The prime function of the ACharacter class is that it represents an AActor object that can be controlled directly
 * by the player. This includes, but may not be limited to, the PlayerAvatar.
 */
public abstract class ACharacter extends APawn implements IUpdatable {

    protected final GameControls controlsModel;

    protected CharacterType characterType;
    public enum CharacterType {
        TEO,
        MELYNN
    }

    protected ActionType actionState = ActionType.FLOOR_IDLE;
    public enum ActionType {
        FLOOR_IDLE,
        FLOOR_WALKING,
        FLOOR_RUNNING,
        FLOOR_JUMPING,
        WALL_CLIMBING,
        WALL_JUMPING
    }

    protected HashMap<ActionType, SpriteSheet> spriteSheets = new HashMap<>();

    private final int MAX_ALLOWED_JUMP_TIME = 10;
    private final float MAX_ALLOWED_WALLRIDE_TIME = .7f;

    private int time_jump = MAX_ALLOWED_JUMP_TIME;
    private float time_wallride = MAX_ALLOWED_JUMP_TIME;

    private boolean isJumpLocked = false;

    protected ACharacter(Resources resources, GameControls cModel, float x, float y, float w, float h, float vx,
                         float vy,
                         boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
        this.controlsModel = cModel;
    }

    @Override
    public void update(float delta) {
        setActionType();

        super.update(delta);
    }

    public void control(float delta) {
        doAbilities();
        doMovement(delta);
    }

    private void doMovement(float delta) {

        boolean[] directionals = controlsModel.getDirectionals();

        // SET xDir TO ZERO, NEGATIVE, OR POSITIVE BASED ON CONTROL DIRECTION
        float xDir = (directionals[0] ? -1 : 0) + (directionals[1] ? 1 : 0);
        //int yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

        if(actionState == ActionType.FLOOR_JUMPING || actionState == ActionType.WALL_JUMPING) {
            xDir *= .7;
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
            vY -= (vY * time_wallride);
            if (time_wallride > 0) {
                time_wallride -= .05f;
            }
        } else {
            // If jumping, reset the wallride
            if (isJumpLocked) {
                time_wallride = MAX_ALLOWED_WALLRIDE_TIME;
            }
        }

        // Increment by a specific velocity from control input
        vX += xDir / (float) Config.GAME_UPDATE_RATE / delta;
        //vY += yDir / (float)PreferenceData.GAME_UPDATE_RATE;
    }

    private void doAbilities() {
        doJumps();
    }

    private void doJumps() {

        if (time_jump > 0) {
            time_jump--;
        }

        boolean[] abilities = controlsModel.getAbilities();

        if (abilities[0]) {

            if (isJumpLocked) {
                return;
            }

            if (time_jump > 0) {

                lockJumpState(true);

                if (isFloorCollision) {
                    doFloorJump();
                } else {
                    if (isWallCollisionLeft) {
                        doWallJump(5, -5);

                        isWallCollisionLeft = false;
                    } else
                    if (isWallCollisionRight) {
                        doWallJump(-5, -5);

                        isWallCollisionRight = false;
                    }
                }
                isFloorCollision = false;
            }
        } else {
            lockJumpState(false);
        }
    }

    public void doFloorJump() {
        vY = -5;
        actionState = ActionType.FLOOR_JUMPING;
    }

    public void doWallJump(float vX, float vY) {
        this.vX = vX;
        this.vY = vY;

        actionState = ActionType.WALL_JUMPING;
    }

    private void lockJumpState(boolean state) {
        isJumpLocked = state;

        time_jump = MAX_ALLOWED_JUMP_TIME;
    }

    public void setCharacterType(CharacterType type) {
        this.characterType = type;
    }

    private void setActionType() {
        if(isFloorCollision) {
            if (!isUserControlled) {
                if(Math.floor(Math.abs(vX) + Math.abs(vY)) == 0) {
                    actionState = ActionType.FLOOR_IDLE;
                }
            } else {
                actionState = ActionType.FLOOR_RUNNING;
            }
        } else {
            actionState = ActionType.FLOOR_JUMPING;
        }

        if(isWallCollisionRight || isWallCollisionLeft) {
            if(!isFloorCollision) {
                actionState = ActionType.WALL_CLIMBING;
            }
        }
    }

    public SpriteSheet getCurrentSpriteSheet() {
        return spriteSheets.get(actionState);
    }

    public void reset(int[] characterOrigin) {
        setVelocity(0, 0);
        setX(characterOrigin[0]);
        setY(characterOrigin[1]);
    }

}