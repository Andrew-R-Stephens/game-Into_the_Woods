package models.prototypes.level.prop;

import models.sprites.SpriteSheet;
import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.utils.config.ConfigData;
import models.utils.drawables.IHUDDrawable;
import models.utils.files.Resources;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class ALevelProp extends AActor implements IHUDDrawable {

    /**
     * Instantiates a new A level prop.
     *
     * @param x          the x
     * @param y          the y
     * @param w          the w
     * @param h          the h
     * @param vx         the vx
     * @param vy         the vy
     * @param hasGravity the has gravity
     */
    protected ALevelProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics g) {
    }

    public void drawAsHUD(Graphics g) {

        g.setColor(Color.WHITE);

        double offsetX = ((x * ConfigData.scaledW * .75f) + (Camera.x * .75f));
        double offsetY = ((y * ConfigData.scaledH * .75f) + (Camera.y * .75f));

        double scaledW = w * ConfigData.scaledW * .75f;
        double scaledH = h * ConfigData.scaledH * .75f;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));

    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
