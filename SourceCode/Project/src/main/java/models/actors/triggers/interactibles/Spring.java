package models.actors.triggers.interactibles;

import models.actors.player.PlayerAvatar;
import models.camera.Camera;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.environments.AEnvironment;
import models.prototypes.level.prop.trigger.ATrigger;
import models.sprites.SpriteSheet;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;
import views.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * The Spring is a trigger that will set the velocity of the player to something new.
 * @author Andrew Stephens
 */
public class Spring extends ATrigger implements IDrawable, IHUDDrawable, IUpdatable {

    int cols = Math.max(1, (int)Math.ceil(w / (float) Tile.W));
    int rows = Math.max(1, (int)Math.ceil(h / (float) Tile.H));

    /**<p>The current action of the door.</p>*/
    private final ActionType actionState = ActionType.IDLE;
    /**<p>The hashmap of spritesheets for the states of the Spring.</p>*/
    protected HashMap<ActionType, SpriteSheet> spriteSheets = new HashMap<>();

    /**
     * <p>Called from the subtypes, this method initializes the object. Also initializes the respective spriteSheet.</p>
     * @param resources The resources of the parent Environment
     * @param environment The GameEnvironment that this object resides in.
     * @param spriteSheetName The sprite sheet for this object.
     * @param x The horizontal position, relative to the default dimensions.
     * @param y The y position, relative to the default dimensions.
     * @param w The width, relative to the default dimensions.
     * @param h The height, relative to the default dimensions.
     * @param vx The horizontal velocity.
     * @param vy The vertical velocity.
     * @param MAX_CYCLES The number of times this object can create an action. -1 is infinite.
     * @param hasGravity If the object should be effected by gravity.
     * @param canMoveOnCollision If the object should move if it collides with another object.
     */
    public Spring(Resources resources, AEnvironment environment,
                  String spriteSheetName,
                  float x, float y, float w, float h, float vx, float vy,
                  int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, environment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity,
                canMoveOnCollision);

        spriteSheets.put(actionState, resources.getSpriteSheet(spriteSheetName));
    }

    public Spring(Resources resources, AEnvironment environment,
                  String spriteSheetName,
                  float x, float y, float w, float h,
                  int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, environment, x, y, w, h, 0, 0, MAX_CYCLES, hasGravity, canMoveOnCollision);

        spriteSheets.put(actionState, resources.getSpriteSheet(spriteSheetName));
    }

    public Spring(Resources resources, AEnvironment environment,
                  String spriteSheetName,
                  float x, float y, float w, float h, float vx, float vy) {
        super(resources, environment, x, y, w, h, vx, vy, -1, false, false);

        spriteSheets.put(actionState, resources.getSpriteSheet(spriteSheetName));
    }

    public Spring(Resources resources, AEnvironment environment,
                  String spriteSheetName,
                  float x, float y, float w, float h, int MAX_CYCLES) {
        super(resources, environment, x, y, w, h, 0, 0, MAX_CYCLES, false, false);

        spriteSheets.put(actionState, resources.getSpriteSheet(spriteSheetName));
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        boolean hasCollision = super.hasCollision(a, delta);

        if(MAX_CYCLES != -1) {
            if (currentCycles > MAX_CYCLES) {
                return false;
            }
        }

        if(hasCollision /*&& a.isFalling()*/) {
            lastActor = a;

            doAction();
            currentCycles++;
        }

        return hasCollision;
    }

    @Override
    public void doAction() {
        float VELY_MAX = 9.8f;

        lastActor.setVY(-VELY_MAX);
        if(lastActor instanceof PlayerAvatar p) {
            p.setAction(ACharacter.ActionType.FLOOR_JUMPING);
            p.getCurrentSpriteSheet().reset();
            resources.playAudio("spring");
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        spriteSheets.get(actionState).update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
        float[] offset = Camera.getRelativeOffset(x, y);
        float[] scale = Camera.getRelativeScale(w, h);

        spriteSheets.get(actionState).draw(
                g,
                (int)offset[0],
                (int)(offset[1]-(scale[1] * .2f)),
                (int)scale[0],
                (int)(scale[1] * 1.4f));


        if(Config.DEBUG && isHighlighted) {
            Color c = Color.RED;
            g.setColor(c);
            g.drawRect((int) (offset[0]), (int) (offset[1]),
                    (int) (w * Config.scaledW_zoom),
                    (int) (h * Config.scaledH_zoom));
            g.drawString(x + " " + y, (int) (offset[0]), (int) (offset[1]));
        }
    }

    @Override
    public void drawAsHUD(Graphics2D g) {
        g.setColor(Color.PINK);

        float[] offset = Camera.getRelativeOffsetBy(x, y, Camera.SCALE_MINIMAP);
        float[] scale = Camera.getRelativeScaleBy(w, h, Camera.SCALE_MINIMAP);

        g.fillRect((int) offset[0], (int) offset[1], (int) scale[0], (int) scale[1]);
    }

    /**
     * A generic type to satisfy the spriteSheet map enum.
     */
    public enum ActionType {
        IDLE
    }

}
