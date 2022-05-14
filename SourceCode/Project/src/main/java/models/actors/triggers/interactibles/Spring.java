package models.actors.triggers.interactibles;

import models.actors.player.PlayerAvatar;
import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.level.prop.trigger.ATrigger;
import models.sprites.SpriteSheet;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.HashMap;

/**
 * The Spring is a trigger that will set the velocity of the player to something new.
 * @author Andrew Stephens
 */
public class Spring extends ATrigger implements IDrawable, IHUDDrawable, IUpdatable {

    /**<p>The current action of the door.</p>*/
    private ActionType actionState = ActionType.IDLE;
    /**<p>The hashmap of spritesheets for the states of the Spring.</p>*/
    protected HashMap<ActionType, SpriteSheet> spriteSheets = new HashMap<>();

    /**
     * <p>Called from the subtypes, this method initializes the object. Also initializes the respective spriteSheet.</p>
     * @param resources The resources of the parent Environment
     * @param gameEnvironment The GameEnvironment that this object resides in.
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
    public Spring(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx,
                  float vy,
                  int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity,
                canMoveOnCollision);

        spriteSheets.put(actionState, resources.getSpriteSheet("spritesheet_spring"));
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        boolean hasCollision = super.hasCollision(a, delta);

        if(MAX_CYCLES != -1) {
            if (currentCycles > MAX_CYCLES) {
                return false;
            }
        }

        if(hasCollision && a.isFalling()) {
            lastActor = a;

            doAction();
            currentCycles++;
        }

        return hasCollision;
    }

    @Override
    public void doAction() {
        lastActor.setVY(-100);
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
        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        spriteSheets.get(actionState).draw(g, (int)offsetX, (int)(offsetY-(scaledH)), (int)scaledW,
                (int)(scaledH));
    }

    @Override
    public void drawAsHUD(Graphics2D g) {
    }

    /**
     * A generic type to satisfy the spriteSheet map enum.
     */
    private enum ActionType {
        IDLE
    }

}
