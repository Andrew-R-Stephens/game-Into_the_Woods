package prototypes.actor.pawn.character;

import models.controls.GameControlsModel;
import prototypes.actor.pawn.APawn;
import utils.config.ConfigData;
import utils.drawables.IDrawable;

import java.awt.*;

/**
 * This is an abstract class for a controllable Actor object. Allows for direct control from User Controls.
 */
public abstract class ACharacter extends APawn implements IDrawable {

    protected final GameControlsModel controlsModel;

    /* Dictates whether or not the character has attempted to jump or not. This resets if the character collides with
     * an ALevelProp or presses the Jump button.
     */

    /**
     * The Character type.
     */
    protected Type characterType;

    /**
     * The enum Type.
     */
    public enum Type {
        CHAR1,
        CHAR2}

    /**
     * The C.
     */
    protected Color c = Color.BLUE;

    private boolean isJumpLocked = false;
    private final int MAX_ALLOWED_JUMP_TIME = 10;
    private int jumpTime = MAX_ALLOWED_JUMP_TIME;

    private final float MAX_ALLOWED_WALLRIDE_TIME = .7f;
    private float wallrideTime = MAX_ALLOWED_JUMP_TIME;

    /**
     * Instantiates a new A character.
     *
     * @param cModel     the c model
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
    protected ACharacter(GameControlsModel cModel, float x, float y, float w, float h, float vx, float vy,
                         boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
        this.controlsModel = cModel;
    }

    /**
     * The overloaded call to the parent class update method
     *
     * @param delta - The ratio of current update rate vs targetted framerate
     */
    protected void update(float delta) {
        super.update(delta);
    }

    /**
     * The direct call to movement and ability handlers
     *
     * @param delta the delta
     */
    public void control(float delta) {
        doAbilitiy(delta);
        doMove(delta);
    }

    /**
     * This handles most conditions directly pertaining to Character movement based on User Input
     *
     * @param delta the delta
     */
    public void doMove(float delta) {

        boolean[] directionals = controlsModel.getDirectionals();

        // SET xDir TO ZERO, NEGATIVE, OR POSITIVE BASED ON CONTROL DIRECTION
        int xDir = (directionals[0] ? -1 : 0) + (directionals[1] ? 1 : 0);
        //int yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

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

    /**
     * This handles most conditions pertaining to Character Abilities based on User Input
     *
     * @param delta the delta
     */
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

                    System.out.println("Floor Jump");
                } else {
                    if (isWallCollisionLeft) {
                        doWallJump(5, -5);

                        isWallCollisionLeft = false;
                        System.out.println("Wall Left Jump");
                    } else
                    if (isWallCollisionRight) {
                        doWallJump(-5, -5);

                        isWallCollisionRight = false;
                        System.out.println("Wall Right Jump");
                    }
                }
                isFloorCollision = false;
            }
        } else {
            lockJumpState(false);
        }

    }

    /**
     * Do wall jump.
     *
     * @param vX the v x
     * @param vY the v y
     */
    public void doWallJump(float vX, float vY) {
        this.vX = vX;
        this.vY = vY;
    }

    /**
     * Resets the jump window and sets the jump state.
     *
     * @param state - if the user is jumping or not
     */
    private void lockJumpState(boolean state) {
        isJumpLocked = state;

        jumpTime = MAX_ALLOWED_JUMP_TIME;
    }

    /**
     * Sets character type.
     *
     * @param type the type
     */
    public void setCharacterType(Type type) {
        this.characterType = type;

        if(type == Type.CHAR1) {
            c = Color.BLUE;
        } else {
            c = Color.PINK;
        }
    }

    public void reset(int[] characterOrigin) {
        setVelocity(0, 0);
        x = characterOrigin[0];
        y = characterOrigin[1];
    }

}
