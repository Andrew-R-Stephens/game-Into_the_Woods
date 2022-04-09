package models.prototypes.level.prop;

import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

public abstract class AProp extends AActor implements IDrawable, IHUDDrawable, IUpdatable {

    protected AProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics g) {
    }

    public void drawAsHUD(Graphics g) {

        g.setColor(Color.WHITE);

        double offsetX = ((x * ConfigData.scaledW) + (Camera.targX * ConfigData.scaledW));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.targY * ConfigData.scaledH));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
