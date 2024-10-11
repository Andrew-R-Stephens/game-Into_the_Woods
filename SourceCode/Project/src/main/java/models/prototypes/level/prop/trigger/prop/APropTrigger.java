package models.prototypes.level.prop.trigger.prop;

import models.actors.player.PlayerAvatar;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.resources.Resources;

import java.awt.*;

/**
 * <p>APropTriggers are used to standardize the functionality of objects which react to collisions.</p>
 * @author Andrew Stephens
 */
public abstract class APropTrigger extends ATrigger {

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param resources The resources of the parent Environment
     * @param environment The parent game environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param MAX_CYCLES The maximum number of times the action can be done
     * @param hasGravity If the object should be effected by gravity.
     * @param canMoveOnCollision If the object can react to the colliding object
     */
    protected APropTrigger(Resources resources, AEnvironment environment, float x, float y, float w, float h,
                           float vx, float vy, int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, environment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public boolean checkCollision(AActor a, float delta) {

        if(!(a instanceof PlayerAvatar)) {
            return false;
        }

        if(MAX_CYCLES != -1 && currentCycles > MAX_CYCLES) {
            return false;
        }

        boolean hasCollision = super.checkCollision(a, delta);

        if(hasCollision) {
            doAction();
        }

        return hasCollision;
    }

    @Override
    public abstract void doAction();

    @Override
    public void drawAsHUD(Graphics2D g) {

    }

}