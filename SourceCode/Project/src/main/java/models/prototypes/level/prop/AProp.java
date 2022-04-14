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

        float offsetX = ((x * ConfigData.scaledW_zoom) + (Camera.targX * ConfigData.scaledW_zoom));
        float offsetY = ((y * ConfigData.scaledH_zoom) + (Camera.targY * ConfigData.scaledH_zoom));

        float scaledW = w * ConfigData.scaledW_zoom;
        float scaledH = h * ConfigData.scaledH_zoom;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
