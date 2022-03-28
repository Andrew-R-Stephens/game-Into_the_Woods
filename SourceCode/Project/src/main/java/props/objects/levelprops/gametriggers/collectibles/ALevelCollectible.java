package props.objects.levelprops.gametriggers.collectibles;

import models.environments.game.GameEnvironment;
import prototypes.level.prop.trigger.ATrigger;

public abstract class ALevelCollectible extends ATrigger {
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
    public void doAction() {
        gameEnvironment.getPlayerInventory().addCollectible(this);
    }
}
