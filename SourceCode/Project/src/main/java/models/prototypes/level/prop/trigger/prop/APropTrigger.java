package models.prototypes.level.prop.trigger.prop;

import models.actors.gameactors.props.player.PlayerAvatar;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.ATrigger;

import java.awt.*;

public abstract class APropTrigger extends ATrigger {

    protected APropTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h,
                           float vx, float vy, int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);
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

    public void drawAsHUD(Graphics g) {

    }
}