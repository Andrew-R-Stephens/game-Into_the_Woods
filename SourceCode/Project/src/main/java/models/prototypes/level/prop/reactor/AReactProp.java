package models.prototypes.level.prop.reactor;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;

import java.awt.*;

/**
 * <p>The ReactProp ensures that a prop will contain a reaction method as well as the standard Trigger methods.</p>
 * @author Andrew Stephens
 */
public abstract class AReactProp extends AProp implements IDrawable {

    protected GameEnvironment gameEnvironment;

    protected boolean canMoveOnCollision;
    protected int currentCycles = 0;

    /**
     * <p>Called from the subtypes, this method initializes the object with position and size relative to the
     * default dimensions.</p>
     * @param resources The resources of the parent Environment
     * @param gameEnvironment The parent game environment
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
    protected AReactProp(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w, float h,
                         float vx,
                         float vy,
                         int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, x, y, w, h, vx, vy, hasGravity);

        this.gameEnvironment = gameEnvironment;
        this.canMoveOnCollision = canMoveOnCollision;
    }

    /**
     * Defined by the inherited class.
     */
    public abstract void onReact();

    /**
     * Resets the cycles.
     */
    public void reset() {
        currentCycles = 0;
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return false;
    }

    @Override
    public void draw(Graphics2D g) {

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        g.setColor(color);
        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(new Color(100, 255, 100));
        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(Color.BLACK);
        g.drawString("React Area", (int) (offsetX) + 3, (int) (offsetY) + 12);
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

    }
}
