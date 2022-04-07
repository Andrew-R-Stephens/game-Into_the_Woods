package models.actors.gameactors.props.platforms;

import models.camera.Camera;
import models.prototypes.level.prop.ALevelProp;
import models.utils.config.ConfigData;
import models.utils.files.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlatformProp extends ALevelProp {

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
    public PlatformProp(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        //super.draw(g);

        g.setColor(Color.DARK_GRAY);

        float offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        float offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        float scaledW = w * ConfigData.scaledW;
        float scaledH = h * ConfigData.scaledH;

        BufferedImage img = Resources.getImage("mockPlatformV2");
        float imgScaledH = scaledH/img.getHeight();

        if(scaledW < scaledH) {
            g.drawImage(img,
                    (int) (offsetX), (int) (offsetY),
                    (int) (scaledW), (int) (scaledH),
                    null);
            return;
        }

        float imgScaledW = img.getWidth() * imgScaledH;
        float numImgs = scaledW / imgScaledW;
        int i;
        for(i = 0; i < numImgs-1; i++) {
            g.drawImage(img,
                    (int) (offsetX + (i * imgScaledW)), (int) (offsetY),
                    (int) (imgScaledW), (int) (scaledH),
                    null);
        }
        float lastImgScale = numImgs-i;
        if(lastImgScale > 0) {
            g.drawImage(img,
                    (int) (offsetX + (i * imgScaledW)), (int) (offsetY),
                    (int) (lastImgScale * imgScaledW), (int) (scaledH),
                    null);
        }
        /*
        g.drawImage(img,
                (int) (offsetX), (int) (offsetY),
                (int) (scaledW), (int) (scaledH),
                null);
        */
    }
}
