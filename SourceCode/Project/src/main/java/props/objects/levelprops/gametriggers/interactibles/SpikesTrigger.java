package props.objects.levelprops.gametriggers.interactibles;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import prototypes.actor.AActor;
import prototypes.level.prop.trigger.ATrigger;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;

public class SpikesTrigger extends ATrigger {
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
     * @param MAX_CYCLES
     * @param hasGravity         the has gravity
     * @param canMoveOnCollision the can move on collision
     */
    public SpikesTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy,
                         int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);
    }

    @Override
    public void doAction() {

    }

    @Override
    protected void update(float delta) {
        super.update(delta);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
    }

    @Override
    public void draw(Graphics g) {
        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.drawImage(Resources.getImage("spikes"), (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH, null);
    }
}
