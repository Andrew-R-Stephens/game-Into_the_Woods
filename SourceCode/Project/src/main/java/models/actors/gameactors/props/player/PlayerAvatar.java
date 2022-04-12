package models.actors.gameactors.props.player;

import controls.GameControls;
import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class PlayerAvatar extends ACharacter implements IDrawable, IUpdatable {

    public PlayerAvatar(GameControls cModel, float x, float y, float w, float h, float vx, float vy,
                        boolean hasGravity) {
        super(cModel, x - w, y - h, w, h, vx, vy, hasGravity);

        spriteSheets.put(ActionType.FLOOR_IDLE,
                Resources.getSpriteSheet("avataridle_spritesheet").setLoopOnLast(true));
        spriteSheets.put(ActionType.FLOOR_WALKING,
                Resources.getSpriteSheet("avatarrun_spritesheet").setLoopOnLast(true));
        spriteSheets.put(ActionType.FLOOR_RUNNING,
                Resources.getSpriteSheet("avatarrun_spritesheet2").setLoopOnLast(true));
        spriteSheets.put(ActionType.FLOOR_JUMPING,
                Resources.getSpriteSheet("avatarjump_spritesheet").setLoopOnLast(false));
        spriteSheets.put(ActionType.WALL_JUMPING,
                Resources.getSpriteSheet("avatarjump_spritesheet").setLoopOnLast(false));
        spriteSheets.put(ActionType.WALL_CLIMBING,
                Resources.getSpriteSheet("avatarjump_spritesheet").setLoopOnLast(true));
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if(vX < 0) {
            facing = Facing.RIGHT;
        } else if (vX > 0) {
            facing = Facing.LEFT;
        }

        float tx =
                (float)(((ConfigData.window_width_actual * .5) - (w * ConfigData.scaledW)) - (x * ConfigData.scaledW));
        float ty =
                (float)(((ConfigData.window_height_actual * .5) - (h * ConfigData.scaledH)) - (y * ConfigData.scaledH));

        Camera.moveTo(tx, ty);

        updateSpriteAnimation(delta);

        //System.out.println(actionState);
    }

    @Override
    public void draw(Graphics g) {

        // Scaled size
        float scaleW = w * ConfigData.scaledW;
        float scaleH = h * ConfigData.scaledH;

        //Half window width
        float centerX = (x * ConfigData.scaledW) + (Camera.camX) + scaleW;
        float centerY = (y * ConfigData.scaledH) + (Camera.camY) + scaleH;

        centerX -= scaleW;
        centerY -= scaleH;

        if (facing == Facing.RIGHT) {
            centerX += scaleW;
            scaleW *= -1;
        }

        if(spriteSheets.get(actionState).isTrimmed()) {
            //x = (x - spriteSheets.get(actionState).getWidth());
            spriteSheets.get(actionState).draw(g, (int) centerX, (int) centerY, (int) scaleW, (int) scaleH);
        } else {
            spriteSheets.get(actionState).draw(g, (int) centerX, (int) centerY, (int) scaleW, (int) scaleH);
        }

        //g.drawString("TC", (int) (centerX) + 3, (int) (centerY) + 12);

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

}
