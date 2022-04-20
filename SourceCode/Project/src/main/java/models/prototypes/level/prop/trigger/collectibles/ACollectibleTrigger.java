package models.prototypes.level.prop.trigger.collectibles;

import models.actors.player.PlayerAvatar;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.resources.Resources;

/**
 * <p></p>
 */
public abstract class ACollectibleTrigger extends ATrigger {

    protected boolean isActive = true;

    /**
     * <p></p>
     * @param resources -
     * @param gameEnvironment -
     * @param x -
     * @param y -
     * @param w -
     * @param h -
     * @param vx -
     * @param vy -
     * @param MAX_CYCLES -
     * @param hasGravity -
     * @param canMoveOnCollision -
     */
    protected ACollectibleTrigger(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w,
                                  float h,
                                  float vx, float vy, int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, gameEnvironment, x, y, w, h, vx, vy, 1, hasGravity, canMoveOnCollision);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if(!isActive)
            return false;

        if(!(a instanceof PlayerAvatar)) {
            return false;
        }

        boolean hasCollision = super.hasCollision(a, delta);

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
        gameEnvironment.getPlayerInventory().addCollectible(this);
        isActive = false;
    }

    /**
     * <p></p>
     */
    public void reset() {
        super.reset();

        isActive = true;
    }
}
