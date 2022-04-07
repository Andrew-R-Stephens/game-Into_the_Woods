package models.prototypes.level.prop.trigger.collectibles;

import models.environments.game.GameEnvironment;
import models.actors.gameactors.PlayerAvatar;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.ATrigger;

public abstract class ALevelCollectible extends ATrigger {

    protected boolean isActive = true;

    /**
     * Instantiates a new A trigger.
     *
     * @param gameEnvironment    the game model
     * @param x                  the x
     * @param y                  the y
     * @param w                  the w
     * @param h                  the h
     * @param vx                 the vx
     * @param vy                 the vy
     * @param hasGravity         the has gravity
     * @param canMoveOnCollision the can move on collision
     */
    protected ALevelCollectible(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy,
                                boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, 1, hasGravity, canMoveOnCollision);
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

    public void reset() {
        super.reset();

        isActive = true;
    }
}
