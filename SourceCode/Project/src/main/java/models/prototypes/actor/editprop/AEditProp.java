package models.prototypes.actor.editprop;

import models.actors.platforms.Platform;
import models.camera.Camera;
import models.prototypes.level.prop.AProp;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.textures.meshes.Tile;

import java.awt.*;
import java.util.ArrayList;

public class AEditProp implements IDrawable {

    public AProp prop;

    public ArrayList<EditPropGrip> grips = new ArrayList<>();
    public EditPropGrip selectedGrip = null;

    public EditPropAttribute attributeToggle;

    public AEditProp(AProp p, Resources resources) {
        this.prop = p;
        this.prop.isHighlighted(true);

        //System.out.println("EditPropGrip: " + GripEdge.values());
        for(GripEdge e: GripEdge.values()) {
            grips.add(new EditPropGrip(this.prop, e));
        }

        attributeToggle = new EditPropAttribute(this.prop, resources);
        attributeToggle.onToggleListener = new EditPropAttribute.OnToggleAttributeListener() {
            @Override
            void onToggle() {
                prop.setGravity(!prop.hasGravity());
                if(!prop.hasGravity()) {
                    prop.setVelocity(0, 0);
                }
            }
        };
    }

    @Override
    public void draw(Graphics2D g) {
        if(prop != null) {

            float offsetX = ((prop.getX() * Config.scaledW_zoom) + (Camera.camX));
            float offsetY = ((prop.getY() * Config.scaledH_zoom) + (Camera.camY));

            Color c = Color.RED;
            g.setColor(c);
            g.drawRect((int) (offsetX), (int) (offsetY),
                    (int) (prop.getW() * Config.scaledW_zoom),
                    (int) (prop.getH() * Config.scaledH_zoom));
            g.drawString(prop.getX() + " " + prop.getY(), (int) (offsetX), (int) (offsetY));

            for(EditPropGrip grip: grips) {
                grip.draw(g);
            }

            if(attributeToggle != null) {
                attributeToggle.draw(g);
            }

            g.drawString(prop.getX() + " " + prop.getY(), (int) (offsetX), (int) (offsetY));

        }
    }

    public void onLeftPressed(int[] pos) {
        int mx = (int)((pos[0] - Camera.camX) / Config.scaledW_zoom);
        int my = (int)((pos[1] - Camera.camY) / Config.scaledH_zoom);
        if(selectedGrip != null) {
            selectedGrip.modify(mx, my);
            prop.setGravity(false);
            prop.setVelocity(0, 0);
        } else {
            for (EditPropGrip g : grips) {
                if (new Rectangle(g.getDrawX(), g.getDrawY(), g.W, g.H).contains(pos[0], pos[1])) {
                    selectedGrip = g;
                }
            }
        }
    }

    public void onLeftClicked(int[] pos) {
        if(attributeToggle != null) {
            if (new Rectangle(
                    attributeToggle.getDrawX(), attributeToggle.getDrawY(),
                    attributeToggle.W, attributeToggle.H).contains(pos[0], pos[1])) {
                attributeToggle.toggleAttribute();
            }
        }
    }

    public void onLeftReleased() {
        selectedGrip = null;
    }

    public enum GripEdge {
        LEFT, TOP, RIGHT, BOTTOM
    }

    public static class EditPropGrip {

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
            float w = prop.getW(), h = prop.getH();

            switch (e) {
                case LEFT -> prop.setX(Platform.roundCoordinate(mx - (Tile.W * .25f)));
                case TOP -> prop.setY(Platform.roundCoordinate(my - (Tile.H * .25f)));
                case RIGHT -> prop.setW(Platform.roundCoordinate(Math.max(Tile.W, mx - prop.getX() + (Tile.W * .5f))));
                case BOTTOM -> prop.setH(Platform.roundCoordinate(Math.max(Tile.H, my - prop.getY() + (Tile.H * .5f))));
            }

            if(w != prop.getW() || h != prop.getH()) {
                prop.calcSubImages();
            }
        }


        public void draw(Graphics2D g) {
            g.setColor(Color.YELLOW);
            g.drawOval(getDrawX(), getDrawY(), W, H);
        }

    }

    public static class EditPropAttribute {

        private final Resources resources;
        private final AProp prop;

        private final int W = 12, H = 12;

        public EditPropAttribute(AProp p, Resources resources) {
            this.prop = p;
            this.resources = resources;
        }

        public void toggleAttribute() {
            onToggleListener.onToggle();
        }

        private int getDrawX() {
            float offsetX = (prop.getX() * Config.scaledW_zoom) + (Camera.camX);
            float width = prop.getW() * Config.scaledW_zoom;
            return (int) (offsetX + width);
        }

        private int getDrawY() {
            float offsetY = ((prop.getY() * Config.scaledH_zoom) + (Camera.camY));
            float height = prop.getH() * Config.scaledH_zoom;

            return (int) (offsetY + height - H);
        }

        public void draw(Graphics2D g) {
            if(prop!=null) {
                if(prop.hasGravity()) {
                    g.setColor(new Color(55, 91, 13));
                } else {
                    g.setColor(new Color(91, 25, 25));
                }
                g.fillRect(getDrawX(),
                        getDrawY(),
                        W, H);
                g.drawImage(
                        resources.getImage("gravityIcon"),
                        getDrawX(),
                        getDrawY(),
                        W, H,
                        null);
            }
        }

        public OnToggleAttributeListener onToggleListener = new OnToggleAttributeListener() {
            void onToggle() {}
        };
        private abstract static class OnToggleAttributeListener {
            abstract void onToggle();
        }
    }
}
