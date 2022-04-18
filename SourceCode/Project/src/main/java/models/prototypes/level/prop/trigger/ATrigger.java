package models.prototypes.level.prop.trigger;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;

import java.awt.*;

public abstract class ATrigger extends AProp implements IDrawable {

    protected GameEnvironment gameEnvironment;

    protected AActor lastActor;

    protected boolean canMoveOnCollision;
    protected int MAX_CYCLES;
    protected int currentCycles = 0;

    protected ATrigger(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w, float h,
                       float vx,
                       float vy,
                       int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, x, y, w, h, vx, vy, hasGravity);

        this.gameEnvironment = gameEnvironment;

        this.MAX_CYCLES = MAX_CYCLES;
        this.canMoveOnCollision = canMoveOnCollision;
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta, canMoveOnCollision);
    }

    public abstract void doAction();

    public void reset() {
        currentCycles = 0;
    }

    @Override
    public void draw(Graphics g) {

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
