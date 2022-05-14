package models.actors.player;

import controls.game.GameControls;
import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * <p>The PlayerAvatar is one of the most complex actors. It takes direct control from the GameControls and will
 * react to the controls. Its position controls the Camera's viewport. The PlayerAvatar is the primary
 * actor subtype that all actors check collisions with. The PlayerAvatar will have direct impact on the game levels,
 * the other actors within a level.</p>
 * @author Andrew Stephens
 */
public class PlayerAvatar extends ACharacter implements IDrawable, IUpdatable {

    /**
     * <p>Called from the subtypes, this method initializes the object.</p>
     * @param resources The resources of the parent Environment
     * @param cModel The GameControls of the parent Environment
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param hasGravity If the object should be effected by gravity.
     */
    public PlayerAvatar(
            Resources resources, GameControls cModel, float x, float y, float w, float h, float vx,
            float vy,
            boolean hasGravity) {
        super(resources, cModel, x - w, y - h, w, h, vx, vy, hasGravity);

        setSpriteSheets();
    }

    /**
     * <p>Controls which spritesheets are used. Determined first by the character, the further controlled by the
     * action state of the avatar.</p>
     */
    private void setSpriteSheets() {

        switch(characterType) {
            case MELYNN -> {
                spriteSheets.replace(ActionType.FLOOR_IDLE,
                        resources.getSpriteSheet("spritesheet_melynn_idle")
                                .setLoopOnLast(true).setFrameScale(w, h));
                spriteSheets.put(ActionType.FLOOR_WALKING,
                        resources.getSpriteSheet("spritesheet_melynn_run")
                                .setLoopOnLast(true).setFrameScale(w, h));
                spriteSheets.put(ActionType.FLOOR_RUNNING,
                        resources.getSpriteSheet("spritesheet_melynn_run")
                                .setLoopOnLast(true).setFrameScale(w, h));
                spriteSheets.put(ActionType.FLOOR_JUMPING,
                        resources.getSpriteSheet("spritesheet_melynn_jump")
                                .setLoopOnLast(false).setFrameScale(w, h));
                spriteSheets.put(ActionType.WALL_JUMPING,
                        resources.getSpriteSheet("spritesheet_melynn_jump")
                                .setLoopOnLast(false).setFrameScale(w, h));
                spriteSheets.put(ActionType.WALL_CLIMBING,
                        resources.getSpriteSheet("spritesheet_melynn_jump")
                                .setLoopOnLast(true).setFrameScale(w, h));
            }
            case TEO -> {
                spriteSheets.put(ActionType.FLOOR_IDLE,
                        resources.getSpriteSheet("spritesheet_teo_idle")
                                .setLoopOnLast(true).setFrameScale(w, h));
                spriteSheets.put(ActionType.FLOOR_WALKING,
                        resources.getSpriteSheet("spritesheet_teo_run")
                                .setLoopOnLast(true).setFrameScale(w, h));
                spriteSheets.put(ActionType.FLOOR_RUNNING,
                        resources.getSpriteSheet("spritesheet_teo_run")
                                .setLoopOnLast(true).setFrameScale(w, h));
                spriteSheets.put(ActionType.FLOOR_JUMPING,
                        resources.getSpriteSheet("spritesheet_teo_jump")
                                .setLoopOnLast(false).setFrameScale(w, h));
                spriteSheets.put(ActionType.WALL_JUMPING,
                        resources.getSpriteSheet("spritesheet_teo_jump")
                                .setLoopOnLast(false).setFrameScale(w, h));
                spriteSheets.put(ActionType.WALL_CLIMBING,
                        resources.getSpriteSheet("spritesheet_teo_jump")
                                .setLoopOnLast(true).setFrameScale(w, h));
            }
        }
    }

    /**
     * <p>Updates the image used for the PlayerAvatar. This is obtained through the Spritesheet.</p>
     * <p>The frame of the spritesheet is updated via the delta of the update loop. Some actions use values within
     * to either speed up or slow down the rate at which a sprite changes, or the direction that it changes.</p>
     * <p>The logic uses the action state that the player is currently in and obtains the image from the spritesheet
     * which is correlated with that action.</p>
     * @param delta The ratio of actual/target update rate for the game ticks.
     */
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

    /**
     * <p>Sets the current action, based on external states and collisions.</p>
     * @param actionState The current action that the player is doing.
     */
    public void setAction(ActionType actionState) {
        this.actionState = actionState;
    }

    @Override
    public void reset(int[] characterOrigin) {
        setSpriteSheets();
        super.reset(characterOrigin);
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

        if (actionState == ActionType.FLOOR_RUNNING) {
            if (getCurrentSpriteSheet().isLastFrame() && getCurrentSpriteSheet().isNewCycle()) {
                resources.playAudio("run_" + ((int) (Math.random() * 5) + 1));
            }
        }
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

    }

}