package models.prototypes.actor.pawn.character;

import controls.game.GameControls;
import models.prototypes.actor.pawn.APawn;
import models.prototypes.controls.AControls;
import models.sprites.SpriteSheet;
import models.utils.config.Config;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.util.HashMap;

/**
 * <p>The prime function of the ACharacter class is that it represents an AActor object that can be controlled directly
 * by the player. This includes, but may not be limited to, the PlayerAvatar.</p>
 * @author Andrew Stephens
 */
public abstract class ACharacter extends APawn implements IUpdatable {

    /**<p>The controls model that this entity will listen to</p>*/
    protected final AControls controlsModel;

    /**<p>The user-defined chosen character</p>*/
    protected CharacterType characterType = CharacterType.TEO;
    /**<p>The current state of the player.</p>*/
    protected ActionType actionState = ActionType.FLOOR_IDLE;

    /**<p>The hashmap containing all spritesheets particular for specific user actions.</p>*/
    protected HashMap<ActionType, SpriteSheet> spriteSheets = new HashMap<>();

    /**<p>The amount of ticks that a player has to hit the ground or wall after pressing jump before the jump
     * button is no longer recognized as pressed.</p>*/
    private final int MAX_ALLOWED_JUMP_TIME = 10;
    /**<p>The length of time that a player may stick to the wall.</p>*/
    private final float MAX_ALLOWED_WALLRIDE_TIME = .7f;

    /**<p>The current number of ticks that the player has not hit the ground or wall after pressing jump.</p>*/
    private int time_jump = MAX_ALLOWED_JUMP_TIME;
    /**<p>The amount of time that the player has wall ridden for.</p>*/
    private float time_wallride = MAX_ALLOWED_JUMP_TIME;

    /**<p>If the jump is still executing.</p>*/
    private boolean isJumpLocked = false;

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param resources The resources of the parent Environment
     * @param cModel The controls particular to the Game Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    protected ACharacter(
            Resources resources, AControls cModel,
            float x, float y, float w, float h, float vx,
            float vy,
            boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
        this.controlsModel = cModel;
    }

    /**
     * <p>Called to register the controls to manipulate the entity. Updates for the abilities and movement.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    public void control(float delta) {
        if(controlsModel instanceof GameControls) {
            doAbilities();
        }
        doMovement(delta);
    }

    /**
     * <p>Uses the controls to manipulate the positional information.</p>
     * <p>Registers directional and ability states.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
    private void doMovement(float delta) {

        boolean[] directionals = controlsModel.getDirectionals();

        // SET xDir TO ZERO, NEGATIVE, OR POSITIVE BASED ON CONTROL DIRECTION
        float xDir = (directionals[0] ? -1 : 0) + (directionals[1] ? 1 : 0);
        //int yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

        if(actionState == ActionType.FLOOR_JUMPING || actionState == ActionType.WALL_JUMPING) {
            xDir *= .7f;
        }

        isUserControlled = directionals[0] || directionals[1] || directionals[2] || directionals[3];

        // If control direction goes against character movement direction, slow velocity down
        if (vX * xDir < 0) {
            vX *= .85f; //.95
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

    /**
     * <p>Calls the process for any programmed abilities.</p>
     */
    private void doAbilities() {
        doJumps();
    }

    /**
     * <p>Does the jump procedure. Follows logic to execute floor jumps and wall jumps.</p>
     */
    private void doJumps() {

        if (time_jump > 0) {
            time_jump--;
        }

        boolean[] abilities = ((GameControls)controlsModel).getAbilities();

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

    /**
     * <p>Executes the procedure for a jump from the floor.</p>
     */
    public void doFloorJump() {
        vY = -5;
        actionState = ActionType.FLOOR_JUMPING;

        doJumpAudio();
    }

    /**
     * Executes jump audio
     */
    private void doJumpAudio() {
        resources.playAudio("jump");
    }

    /**
     * <p>Does the procedure for a wall jump.</p>
     * @param vX The new horizontal velocity.
     * @param vY The new vertical velocity.
     */
    public void doWallJump(float vX, float vY) {
        this.vX = vX;
        this.vY = vY;

        actionState = ActionType.WALL_JUMPING;

        doJumpAudio();
    }

    /**
     * <p>Locks or Unlocks the character into being in a jump state. This is updated to be unlocked after a period, or
     * if the player touches a boundary object.</p>
     * @param state -
     */
    private void lockJumpState(boolean state) {
        isJumpLocked = state;

        time_jump = MAX_ALLOWED_JUMP_TIME;
    }

    /**
     * <p>Sets the type of character, being the male or female character Teo or Melynn.</p>
     * @param type The Character type chosen
     */
    public void setCharacterType(CharacterType type) {
        this.characterType = type;
    }

    /**
     * Retrieves the enum of the character type
     * @return
     */
    public CharacterType getCharacterType() {
        return characterType;
    }

    /**
     * <p>Checks conditions of the character state to determine the action type of the character.</p>
     * <p>This has an effect on the sprite animation of the character.</p>
     */
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

    /**
     * <p>Retrieves the current sprite sheet used, based on the action state.</p>
     * @return the current sprite sheet.
     */
    public SpriteSheet getCurrentSpriteSheet() {
        return spriteSheets.get(actionState);
    }

    /**
     * <p>Resets the data of the character. This involves defaulting the velocity and position.</p>
     * @param characterOrigin The new position for the character.
     */
    public void reset(int[] characterOrigin) {
        setVelocity(0, 0);
        setX(characterOrigin[0]);
        setY(characterOrigin[1]);
    }

    @Override
    public void update(float delta) {
        setActionType();

        super.update(delta);
    }

    /**
     * <p>The enumerated Character type. This is used for determining the sprite style. Based on Teo or Melynn.</p>
     */
    public enum CharacterType {
        TEO,
        MELYNN
    }

    /**
     * <p>The enumerate Action type. This is used for determining the sprite animation for the action type.</p>
     */
    public enum ActionType {
        FLOOR_IDLE,
        FLOOR_WALKING,
        FLOOR_RUNNING,
        FLOOR_JUMPING,
        WALL_CLIMBING,
        WALL_JUMPING
    }
}