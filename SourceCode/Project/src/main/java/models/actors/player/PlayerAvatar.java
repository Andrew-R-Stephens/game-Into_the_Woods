package models.actors.player;

import controls.GameControls;
import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class PlayerAvatar extends ACharacter implements IDrawable, IUpdatable {

    public PlayerAvatar(
            Resources resources, GameControls cModel, float x, float y, float w, float h, float vx,
            float vy,
            boolean hasGravity) {
        super(resources, cModel, x - w, y - h, w, h, vx, vy, hasGravity);

        spriteSheets.put(ActionType.FLOOR_IDLE,
                resources.getSpriteSheet("spritesheet_avataridle")
                        .setLoopOnLast(true).setFrameScale(w, h));
        spriteSheets.put(ActionType.FLOOR_WALKING,
                resources.getSpriteSheet("spritesheet_avatarrun2")
                        .setLoopOnLast(true).setFrameScale(w, h));
        spriteSheets.put(ActionType.FLOOR_RUNNING,
                resources.getSpriteSheet("spritesheet_avatarrun2")
                        .setLoopOnLast(true).setFrameScale(w, h));
        spriteSheets.put(ActionType.FLOOR_JUMPING,
                resources.getSpriteSheet("spritesheet_avatarjump")
                        .setLoopOnLast(false).setFrameScale(w, h));
        spriteSheets.put(ActionType.WALL_JUMPING,
                resources.getSpriteSheet("spritesheet_avatarjump")
                        .setLoopOnLast(false).setFrameScale(w, h));
        spriteSheets.put(ActionType.WALL_CLIMBING,
                resources.getSpriteSheet("spritesheet_avatarjump")
                        .setLoopOnLast(true).setFrameScale(w, h));
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        float[] spriteScale = spriteSheets.get(actionState).getCurrentFrameSize();
        int[] largest = spriteSheets.get(actionState).getLargestSize();

        /*
        w = spriteScale[0];
        h = (spriteScale[1] * largest[1]) + spriteSheets.get(actionState).getCurrentFramePos()[1];

        setY(y + (oh - h));
        */

        //System.out.println((int)y + " " + (int)h + " " + largest[1]);
        if(vX < 0) {
            facing = Facing.RIGHT;
        } else if (vX > 0) {
            facing = Facing.LEFT;
        }

        float tx =
                (float)(((Config.window_width_actual * .5) - (w * Config.scaledW_zoom)) - (x * Config.scaledW_zoom));
        float ty =
                (float)(((Config.window_height_actual * .5) - (h * Config.scaledH_zoom)) - (y * Config.scaledH_zoom));

        Camera.moveTo(tx, ty);

        updateSpriteAnimation(delta);
    }

    @Override
    public void draw(Graphics2D g) {

        // Scaled size
        float scaleW = w * Config.scaledW_zoom;
        float scaleH = h * Config.scaledH_zoom;

        //Half window width
        float centerX = (x * Config.scaledW_zoom) + (Camera.camX);
        float centerY = (y * Config.scaledH_zoom) + (Camera.camY);

        if (facing == Facing.RIGHT) {
            centerX += scaleW;
            scaleW *= -1;
        }

        spriteSheets.get(actionState).draw(g,
                (int) centerX,
                (int) centerY,
                (int) scaleW,
                (int) scaleH);

        g.setColor(Color.CYAN);

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
    }

    private void updateSpriteAnimation(float delta) {
        float tickRate = delta;

        switch(actionState) {
            case FLOOR_RUNNING -> {
                spriteSheets.get(ActionType.WALL_CLIMBING).reset();
                spriteSheets.get(ActionType.FLOOR_JUMPING).reset();
                spriteSheets.get(ActionType.WALL_JUMPING).reset();
                spriteSheets.get(ActionType.FLOOR_IDLE).reset();
                if(vX != 0) {
                    tickRate *= ((MAX_VEL_X*.5f)/(Math.abs(vX) + (MAX_VEL_X * .5)));
                }
            }
            case WALL_CLIMBING -> {
                spriteSheets.get(ActionType.FLOOR_RUNNING).reset();
                spriteSheets.get(ActionType.FLOOR_JUMPING).reset();
                spriteSheets.get(ActionType.WALL_JUMPING).reset();
                spriteSheets.get(ActionType.FLOOR_IDLE).reset();
                if(vX != 0) {
                    tickRate *= ((MAX_VEL_X*.5f)/(Math.abs(vX) + (MAX_VEL_X * .5)));
                }
            }
            case FLOOR_JUMPING -> {
                spriteSheets.get(ActionType.WALL_JUMPING).reset();
                spriteSheets.get(ActionType.FLOOR_RUNNING).reset();
                spriteSheets.get(ActionType.WALL_CLIMBING).reset();
                spriteSheets.get(ActionType.FLOOR_IDLE).reset();
                tickRate *= (-vY);
            }
            case WALL_JUMPING -> {
                spriteSheets.get(ActionType.FLOOR_JUMPING).reset();
                spriteSheets.get(ActionType.FLOOR_RUNNING).reset();
                spriteSheets.get(ActionType.WALL_CLIMBING).reset();
                spriteSheets.get(ActionType.FLOOR_IDLE).reset();
                tickRate *= (-vY);
            }
            case FLOOR_IDLE -> {
                spriteSheets.get(ActionType.FLOOR_JUMPING).reset();
                spriteSheets.get(ActionType.WALL_JUMPING).reset();
                spriteSheets.get(ActionType.FLOOR_RUNNING).reset();
                spriteSheets.get(ActionType.WALL_CLIMBING).reset();
            }
        }

        spriteSheets.get(actionState).update(tickRate);
    }

    public String toString() {
        return "TC:  VX= " + (int)vX + ", VY= " + (int)vY;
    }

    public void setAction(ActionType actionState) {
        this.actionState = actionState;
    }

}