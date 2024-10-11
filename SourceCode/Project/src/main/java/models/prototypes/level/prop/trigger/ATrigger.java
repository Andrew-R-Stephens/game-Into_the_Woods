package models.prototypes.level.prop.trigger;

import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.AProp;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;

import java.awt.*;

/**
 * <p>ATrigger is a class which dictates the standard behavior of an object that gets triggered on collision.</p>
 * @author Andrew Stephens
 */
public abstract class ATrigger extends AProp implements IDrawable {

    /**<p>The parent GameEnvironment.</p>*/
    protected AEnvironment environment;

    /**<p>The last actor this trigger affected.</p>*/
    protected AActor lastActor;

    /**<p>If the object can move on collision.</p>*/
    protected boolean canMoveOnCollision;
    /**<p>The maximum number of times this trigger will activate.</p>*/
    protected int MAX_CYCLES;
    /**<p>The current number of times the trigger has activated.</p>*/
    protected int currentCycles = 0;

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param resources The resources of the parent Environment
     * @param environment The GameEnvironment that contains this trigger
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param MAX_CYCLES The maximum number of times this trigger will execute
     * @param hasGravity If the object should be effected by gravity.
     * @param canMoveOnCollision If the object should move on collision.
     */
    protected ATrigger(Resources resources, AEnvironment environment, float x, float y, float w, float h,
                       float vx,
                       float vy,
                       int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, x, y, w, h, vx, vy, hasGravity);

        this.environment = environment;

        this.MAX_CYCLES = MAX_CYCLES;
        this.canMoveOnCollision = canMoveOnCollision;
    }

    protected ATrigger(AEnvironment environment, float x, float y, float w, float h,
                       float vx,
                       float vy,
                       int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(x, y, w, h, vx, vy, hasGravity);

        this.environment = environment;

        this.MAX_CYCLES = MAX_CYCLES;
        this.canMoveOnCollision = canMoveOnCollision;
    }

    protected ATrigger(float x, float y, float w, float h,
                       float vx,
                       float vy,
                       int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(x, y, w, h, vx, vy, hasGravity);

        this.MAX_CYCLES = MAX_CYCLES;
        this.canMoveOnCollision = canMoveOnCollision;
    }

    /**
     * <p>doAction defines an action that should be done. It's called, normally, from within the onCollision method when
     * there is a collision with another physical entity.</p>
     */
    public abstract void doAction();

    /**
     * <p>Resets the current cycle count back to zero.</p>
     */
    public void reset() {
        currentCycles = 0;
    }

    @Override
    public boolean checkCollision(AActor a, float delta) {
        return super.checkCollision(a, delta, canMoveOnCollision);
    }

    @Override
    public void draw(Graphics2D g) { }

}
