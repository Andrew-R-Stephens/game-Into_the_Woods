package models.prototypes.level.prop.trigger;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.ALevelProp;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;

import java.awt.*;

public abstract class ATrigger extends ALevelProp implements IDrawable {

    protected GameEnvironment gameEnvironment;

    protected boolean canMoveOnCollision;

    protected int MAX_CYCLES = -1;
    protected int currentCycles = 0;

    protected boolean isActivated = false;

    protected ATrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy, int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(x, y, w, h, vx, vy, hasGravity);

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

        double offsetX = ((x * ConfigData.scaledW) + (Camera.camX));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.camY));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.setColor(new Color(0, 255, 0, 50));
        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(new Color(100, 255, 100));
        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(Color.BLACK);
        g.drawString("Trigger Area", (int) (offsetX) + 3, (int) (offsetY) + 12);
    }

}
