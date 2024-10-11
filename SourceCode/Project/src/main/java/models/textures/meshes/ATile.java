package models.textures.meshes;

import models.camera.Camera;
import models.prototypes.level.prop.AProp;
import models.utils.resources.Resources;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
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

    public ATile(BufferedImage[] layers, boolean[] corners) {
        createImage(layers, corners);
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

    public void createImage(BufferedImage[] images, boolean[] corners) {
        if(images == null || images.length == 0) return;

        BufferedImage tempImageOut = new BufferedImage(
                (int)Tile.W,
                (int)Tile.H,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = tempImageOut.createGraphics();

        RoundRectangle2D origClipShape =
                new RoundRectangle2D.Double(0, 0, Tile.W, Tile.H, 25, 25);
        Area clip = new Area(origClipShape);
        int halfW = (int)(Tile.W * .5f);
        int halfH = (int)(Tile.H * .5f);
        int cornerIndex = 0;
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                if (!corners[cornerIndex]) {
                    if(cornerIndex == 0)
                        clip.add(new Area(new Rectangle(0, 0, halfW, halfH)));
                    if(cornerIndex == 1)
                        clip.add(new Area(new Rectangle(halfW, 0, halfW, halfH)));
                    if(cornerIndex == 2)
                        clip.add(new Area(new Rectangle(0, halfH, halfW, halfW)));
                    if(cornerIndex == 3)
                        clip.add(new Area(new Rectangle(halfW, halfH, halfW, halfH)));
                }
                cornerIndex++;
            }
        }

        bGr.setClip(clip);

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
