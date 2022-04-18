package models.prototypes.actor;

import models.camera.Camera;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.physics.APhysics;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;


public abstract class AActor extends APhysics implements IDrawable, IUpdatable {

    protected Resources resources;

    protected Facing facing;

    protected enum Facing { LEFT, RIGHT, UP, DOWN }

    protected Color color = new Color(0, 0, 255, 50);

    protected AActor(
            Resources resources,
            float x, float y,
            float w, float h,
            float vx, float vy,
            boolean hasGravity) {

        super(x, y, w, h, vx, vy, hasGravity);

        this.resources = resources;

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.CYAN);

        float offsetX = ((x * Config.scaledW) + (Camera.targX));
        float offsetY = ((y * Config.scaledH) + (Camera.targY));

        float scaledW = w * Config.scaledW;
        float scaledH = h * Config.scaledH;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }
}
