package prototypes.level.prop;

import graphics.views.SpriteSheet;
import models.camera.Camera;
import prototypes.actor.AActor;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class ALevelProp extends AActor {

    protected SpriteSheet spriteSheet;

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
        g.setColor(Color.DARK_GRAY);

        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        //g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.drawImage(Resources.getImage("mockPlatformV2"), (int) ((offsetX)), (int) (offsetY), (int) (scaledW),
                (int) (scaledH),
                null);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
