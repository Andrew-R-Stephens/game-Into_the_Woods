package models.actors.platforms;

import models.camera.Camera;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p></p>
 */
public class Platform extends AProp implements IDrawable, IUpdatable {

    /**
     * <p></p>
     * @param resources -
     * @param x -
     * @param y -
     * @param w -
     * @param h -
     * @param vx -
     * @param vy -
     * @param hasGravity -
     */
    public Platform(Resources resources, float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        BufferedImage img = resources.getImage("mockPlatformV2");
        float imgScaledH = scaledH/img.getHeight();

        if(scaledW < scaledH) {
            g.drawImage(img,
                    (int) (offsetX), (int) (offsetY),
                    (int) (scaledW), (int) (scaledH) + 1,
                    null);
            return;
        }

        float imgScaledW = img.getWidth() * imgScaledH;
        float numImgs = scaledW / imgScaledW;
        int i;
        for(i = 0; i < numImgs-1; i++) {
            g.drawImage(img,
                    (int) (offsetX + (i * imgScaledW)), (int) (offsetY),
                    (int) (imgScaledW) + 1, (int) (scaledH) + 1,
                    null);
        }
        float lastImgScale = numImgs-i;
        if(lastImgScale > 0) {
            g.drawImage(img,
                    (int) (offsetX + (i * imgScaledW)), (int) (offsetY),
                    (int) (lastImgScale * imgScaledW)+1, (int) (scaledH) + 1,
                    null);
        }
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

        g.setColor(Color.WHITE);

        float offsetX = ((x * Config.scaledW) + (Camera.mapX / Config.scaledW));
        float offsetY = ((y * Config.scaledH) + (Camera.mapY / Config.scaledH));

        float scaledW = w * Config.scaledW;
        float scaledH = h * Config.scaledH;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));

    }

}
