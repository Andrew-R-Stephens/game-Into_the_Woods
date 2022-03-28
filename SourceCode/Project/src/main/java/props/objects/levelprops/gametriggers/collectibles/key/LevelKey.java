package props.objects.levelprops.gametriggers.collectibles.key;

import models.environments.game.GameEnvironment;
import props.objects.levelprops.gametriggers.collectibles.ALevelCollectible;

public class LevelKey extends ALevelCollectible {
    /**
     * Instantiates a new A trigger.
     *
     * @param gameModel          the game model
     * @param x                  the x
     * @param y                  the y
     * @param w                  the w
     * @param h                  the h
     * @param vx                 the vx
     * @param vy                 the vy
     * @param hasGravity         the has gravity
     * @param canMoveOnCollision the can move on collision
     */
    protected LevelKey(GameEnvironment gameModel, float x, float y, float w, float h, float vx, float vy,
                       boolean hasGravity, boolean canMoveOnCollision) {
        super(gameModel, x, y, w, h, vx, vy, hasGravity, canMoveOnCollision);
    }

}
