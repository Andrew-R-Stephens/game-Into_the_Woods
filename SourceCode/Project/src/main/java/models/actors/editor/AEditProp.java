package models.actors.editor;

import models.actors.platforms.Platform;
import models.camera.Camera;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class AEditProp implements IDrawable {

    public AProp prop;

    public ArrayList<EditPropGrip> grips = new ArrayList<>();
    public EditPropGrip selectedGrip = null;

    public AEditProp(AProp p) {
        this.prop = p;
        this.prop.isHighlighted(true);

        //System.out.println("EditPropGrip: " + GripEdge.values());
        for(GripEdge e: GripEdge.values()) {
            grips.add(new EditPropGrip(this.prop, e));
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if(prop != null) {

            float offsetX = ((prop.getX() * Config.scaledW_zoom) + (Camera.camX));
            float offsetY = ((prop.getY() * Config.scaledH_zoom) + (Camera.camY));

            //prop.draw(g);

            Color c = Color.RED;
            g.setColor(c);
            g.drawRect((int) (offsetX), (int) (offsetY),
                    (int) (prop.getW() * Config.scaledW_zoom),
                    (int) (prop.getH() * Config.scaledH_zoom));
            g.drawString(prop.getX() + " " + prop.getY(), (int) (offsetX), (int) (offsetY));

            for(EditPropGrip grip: grips) {
                grip.draw(g);
            }

            g.drawString(prop.getX() + " " + prop.getY(), (int) (offsetX), (int) (offsetY));

        }
    }

    public void onLeftPressed(int[] pos) {
        int mx = (int)((pos[0] - Camera.camX) / Config.scaledW_zoom);
        int my = (int)((pos[1] - Camera.camY) / Config.scaledH_zoom);
        if(selectedGrip != null) {
            selectedGrip.modify(mx, my);
        } else {
            for (EditPropGrip g : grips) {
                if (new Rectangle(g.getDrawX(), g.getDrawY(), g.W, g.H).contains(pos[0], pos[1])) {
                    selectedGrip = g;
                }
            }
        }
    }

    public void onLeftReleased() {
        selectedGrip = null;
    }

    public enum GripEdge {
        LEFT, TOP, RIGHT, BOTTOM
    }

    private class EditPropGrip {

        private final AProp prop;
        private final GripEdge e;

        private final int W = 12, H = 12;

        public EditPropGrip(AProp p, GripEdge e) {
            this.prop = p;
            this.e = e;
        }

        private int getDrawX() {
            float offsetX = (prop.getX() * Config.scaledW_zoom) + (Camera.camX);
            float width = prop.getW() * Config.scaledW_zoom;

            return switch (e) {
                case LEFT -> (int) (offsetX - (W * .5));
                case RIGHT -> (int) (offsetX + width - (W * .5));
                case TOP, BOTTOM -> (int) ((offsetX + (width * .5f)) - (W * .5));
            };
        }

        private int getDrawY() {
            float offsetY = ((prop.getY() * Config.scaledH_zoom) + (Camera.camY));
            float height = prop.getH() * Config.scaledH_zoom;

            return switch (e) {
                case TOP -> (int)(offsetY - (H * .5));
                case BOTTOM -> (int) (offsetY + height - (H * .5));
                case LEFT, RIGHT -> (int) ((offsetY + (height * .5f)) - (H * .5));
            };
        }

        public void modify(int mx, int my) {
            float x = prop.getX(), y = prop.getY(), w = prop.getW(), h = prop.getH();

            switch (e) {
                case LEFT -> prop.setX(Platform.roundCoordinate(mx));
                case TOP -> prop.setY(Platform.roundCoordinate(my));
                case RIGHT -> prop.setW(Platform.roundCoordinate(mx - prop.getX()));
                case BOTTOM -> prop.setH(Platform.roundCoordinate(my - prop.getY()));
            }

            if(prop instanceof Platform plat &&
                    (w != prop.getW() || h != prop.getH())) {
                plat.calcSubImages();
            }
        }


        public void draw(Graphics2D g) {
            g.setColor(Color.YELLOW);
            g.drawOval(getDrawX(), getDrawY(), W, H);
        }

    }
}
