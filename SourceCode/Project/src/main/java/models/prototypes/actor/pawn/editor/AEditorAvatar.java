package models.prototypes.actor.pawn.editor;

import models.prototypes.actor.pawn.APawn;
import models.prototypes.controls.AControls;
import models.utils.config.Config;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

/**
 * <p>The prime function of the ACharacter class is that it represents an AActor object that can be controlled directly
 * by the player. This includes, but may not be limited to, the PlayerAvatar.</p>
 * @author Andrew Stephens
 */
public abstract class AEditorAvatar extends APawn implements IUpdatable {

    /**<p>The controls model that this entity will listen to</p>*/
    protected final AControls controlsModel;

    protected boolean[] prevDir = new boolean[4];

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
    protected AEditorAvatar(
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
        float yDir = (directionals[2] ? -1 : 0) + (directionals[3] ? 1 : 0);

        isUserControlled = directionals[0] || directionals[1] || directionals[2] || directionals[3];

        // If control direction goes against character movement direction, slow velocity down
        if (!isUserControlled) {
            vX = 0;
            vY = 0;
            // MULTIPLY BASE MOVEMENT SPEED BY DIRECTION MOVED
            xDir = 0;
            yDir = 0;
        } else {
            // MULTIPLY BASE MOVEMENT SPEED BY DIRECTION MOVED
            xDir *= MAX_VEL_X;
            yDir *= MAX_VEL_Y;
        }


        // Increment by a specific velocity from control input
        vX += xDir / (float) Config.GAME_UPDATE_RATE / delta;
        vY += yDir / (float) Config.GAME_UPDATE_RATE / delta;

        if(prevDir[0] != directionals[0] || prevDir[1] != directionals[1]) {
            vX = 0;
        }
        if(prevDir[2] != directionals[2] || prevDir[3] != directionals[3]) {
            vY = 0;
        }

        System.arraycopy(directionals, 0, prevDir, 0, directionals.length);
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
        super.update(delta);
    }

}