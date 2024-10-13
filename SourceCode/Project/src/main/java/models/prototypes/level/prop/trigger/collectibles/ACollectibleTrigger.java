package models.prototypes.level.prop.trigger.collectibles;

import models.actors.player.PlayerAvatar;
import models.environments.levelEnvironment.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.resources.Resources;

/**
 * <p>ACollectibleTrigger is a class built for objects that will be collected upon trigger.</p>
 * @author Andrew Stephens
 */
public abstract class ACollectibleTrigger extends ATrigger {

    /**<p>If the trigger can still be activated.</p>*/
    protected boolean isActive = true;

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
    protected ACollectibleTrigger(Resources resources, AEnvironment environment,
                                  float x, float y,
                                  float w, float h,
                                  float vx, float vy,
                                  int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, environment, x, y, w, h, vx, vy, 1, hasGravity, canMoveOnCollision);
    }

    @Override
    public void reset() {
        super.reset();

        isActive = true;
    }

    @Override
    public boolean checkCollision(AActor a, float delta) {
        if(!isActive)
            return false;

        if(!(a instanceof PlayerAvatar)) {
            return false;
        }

        boolean hasCollision = super.checkCollision(a, delta);

        if(MAX_CYCLES != -1 && currentCycles > MAX_CYCLES) {
            return false;
        }

        if(hasCollision) {
            doAction();
        }

        return hasCollision;
    }

    @Override
    public void doAction() {
        if(environment instanceof GameEnvironment ge) {
            ge.getPlayerInventory().addCollectible(this);
            isActive = false;
        }
    }


}
