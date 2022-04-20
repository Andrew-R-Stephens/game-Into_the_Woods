package models.prototypes.level.prop.trigger.prop;

import models.actors.player.PlayerAvatar;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.resources.Resources;

import java.awt.*;

/**
 * <p></p>
 */
public abstract class APropTrigger extends ATrigger {

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
    protected APropTrigger(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w, float h,
                           float vx, float vy, int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {

        if(!(a instanceof PlayerAvatar)) {
            return false;
        }

        if(MAX_CYCLES != -1 && currentCycles > MAX_CYCLES) {
            return false;
        }

        boolean hasCollision = super.hasCollision(a, delta);

        if(hasCollision) {
            doAction();
        }

        return hasCollision;
    }

    @Override
    public abstract void doAction();

    public void reset() {
        super.reset();
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

    }

}