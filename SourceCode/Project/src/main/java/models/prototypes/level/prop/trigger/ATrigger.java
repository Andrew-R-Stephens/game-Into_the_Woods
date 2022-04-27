package models.prototypes.level.prop.trigger;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;

import java.awt.*;

/**
 * <p>ATrigger is a class which dictates the standard behavior of an object that gets triggered on collision.</p>
 * @author Andrew Stephens
 */
public abstract class ATrigger extends AProp implements IDrawable {

    protected GameEnvironment gameEnvironment;

    protected AActor lastActor;

    protected boolean canMoveOnCollision;
    protected int MAX_CYCLES;
    protected int currentCycles = 0;

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param resources The resources of the parent Environment
     * @param gameEnvironment The GameEnvironment that contains this trigger
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
    protected ATrigger(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w, float h,
                       float vx,
                       float vy,
                       int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, x, y, w, h, vx, vy, hasGravity);

        this.gameEnvironment = gameEnvironment;

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
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta, canMoveOnCollision);
    }

    @Override
    public void draw(Graphics2D g) {

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        g.setColor(new Color(0, 255, 0, 50));
        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(new Color(100, 255, 100));
        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(Color.BLACK);
        g.drawString("Trigger Area", (int) (offsetX) + 3, (int) (offsetY) + 12);
    }

}
