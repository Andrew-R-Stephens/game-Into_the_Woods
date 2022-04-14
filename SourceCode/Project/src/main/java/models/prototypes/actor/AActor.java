package models.prototypes.actor;

import models.camera.Camera;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.physics.APhysics;
import models.utils.updates.IUpdatable;

import java.awt.*;


public abstract class AActor extends APhysics implements IDrawable, IUpdatable {

    protected Facing facing;
    protected enum Facing { LEFT, RIGHT, UP, DOWN }

    protected Color color = new Color(0, 0, 255, 50);


    protected AActor(float x, float y,
                     float w, float h,
                     float vx, float vy,
                     boolean hasGravity) {

        super(x, y, w, h, vx, vy, hasGravity);

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);

        float offsetX = ((x * ConfigData.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * ConfigData.scaledH_zoom) + (Camera.camY));

        float scaledW = w * ConfigData.scaledW_zoom;
        float scaledH = h * ConfigData.scaledH_zoom;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }
}
