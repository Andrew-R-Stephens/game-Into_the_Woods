package models.textures.meshes;

import models.camera.Camera;
import models.prototypes.level.prop.AProp;
import models.utils.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

import static models.camera.Camera.*;
import static models.utils.config.Config.scaledH_zoom;
import static models.utils.config.Config.scaledW_zoom;

public abstract class ATile {

    protected BufferedImage image;

    public ATile() {}

    public ATile(Resources resources, String image) {
        createImage(resources.getImage(image));
    }

    public ATile(BufferedImage[] layers) {
        createImage(layers);
    }

    public void createImage(BufferedImage image) {

        if(image != null) {
            Image tempImageIn = image.getScaledInstance(
                    (int)Tile.W,
                    (int)Tile.H,
                    BufferedImage.SCALE_SMOOTH
            );

            BufferedImage tempImageOut = new BufferedImage(
                    (int)Tile.W,
                    (int)Tile.H,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = tempImageOut.createGraphics();

            bGr.drawImage(
                    tempImageIn,
                    0, 0,
                    (int)Tile.W, (int)Tile.H,
                    null
            );

            bGr.dispose();

            this.image = tempImageOut;
        }
    }

    public void createImage(BufferedImage[] images) {

        if(images == null || images.length == 0) return;

        BufferedImage tempImageOut = new BufferedImage(
                (int)Tile.W,
                (int)Tile.H,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = tempImageOut.createGraphics();

        for (BufferedImage bufferedImage : images) {
            Image tempImageIn = bufferedImage.getScaledInstance(
                    (int) Tile.W,
                    (int) Tile.H,
                    BufferedImage.SCALE_SMOOTH
            );
            bGr.drawImage(
                    tempImageIn,
                    0, 0,
                    (int) Tile.W, (int) Tile.H,
                    null
            );
        }

        bGr.dispose();

        this.image = tempImageOut;
    }

    public void draw(Graphics2D g, AProp prop) {
        g.setColor(Color.DARK_GRAY);

        float offsetX = ((prop.getX() * scaledW_zoom) + (camX));
        float offsetY = ((prop.getY() * scaledH_zoom) + (camY));

        g.drawImage(image,
                (int) Math.floor(offsetX) - 1,
                (int) Math.floor(offsetY) - 1,
                (int) Math.ceil(prop.getW() * scaledW_zoom) + 1,
                (int) Math.ceil(prop.getH() * scaledH_zoom) + 1,
                null);
    }

    public void drawAsHUD(Graphics2D g, AProp prop) {

        g.setColor(Color.WHITE);

        float[] offset = Camera.getRelativeOffsetBy(prop.getX(), prop.getY(), SCALE_MINIMAP);
        float[] scale = Camera.getRelativeScaleBy(prop.getW(), prop.getH(), SCALE_MINIMAP);

        g.fillRect((int) offset[0], (int) offset[1], (int) scale[0], (int) scale[1]);

    }

}
