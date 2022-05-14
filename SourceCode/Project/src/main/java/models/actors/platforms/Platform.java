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
 * <p>A Platform is a boundary object that acts to create a physical barrier. It is the fundamental piece that allows
 * the other actors to behave as if in a physical world.</p>
 * @author Andrew Stephens
 */
public class Platform extends AProp implements IDrawable, IUpdatable {

    /**
     * The native image for this platform.
     */
    private BufferedImage image;

    /**
     * <p>Called from the subtypes, this method initializes the object.</p>
     * @param resources The resources of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    public Platform(Resources resources, String imageName, float x, float y, float w, float h, float vx, float vy,
                    boolean hasGravity) {
        super(resources, x, y, w, h, vx, vy, hasGravity);

        this.image = resources.getImage(imageName);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        float imgScaledH = scaledH/image.getHeight();

        if(scaledW < scaledH) {
            g.drawImage(image,
                    (int) (offsetX), (int) (offsetY),
                    (int) (scaledW), (int) (scaledH) + 1,
                    null);
            return;
        }

        float imgScaledW = image.getWidth() * imgScaledH;
        float numImgs = scaledW / imgScaledW;
        int i;
        for(i = 0; i < numImgs-1; i++) {
            g.drawImage(image,
                    (int) (offsetX + (i * imgScaledW)), (int) (offsetY),
                    (int) (imgScaledW) + 1, (int) (scaledH) + 1,
                    null);
        }
        float lastImgScale = numImgs-i;
        if(lastImgScale > 0) {
            g.drawImage(image,
                    (int) (offsetX + (i * imgScaledW)), (int) (offsetY),
                    (int) (lastImgScale * imgScaledW)+1, (int) (scaledH) + 1,
                    null);
        }

        //g.setColor(Color.RED);
        //g.drawString(x + " " + y, (int) (offsetX), (int) (offsetY));
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

        g.setColor(Color.WHITE);

        float offsetX = ((x * Config.scaledW) + (Camera.camX / Camera.zoomLevel / Config.scaledW));
        float offsetY = ((y * Config.scaledH) + (Camera.camY / Camera.zoomLevel / Config.scaledH));

        float scaledW = w * Config.scaledW;
        float scaledH = h * Config.scaledH;


        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));

    }

}
