package models.actors.triggers.interactibles;

import models.camera.Camera;
import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.sprites.SpriteSheet;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.HashMap;

/**
 * <p>The Door is meant to be the gate by which the user will collide with in order to progress to the next level.</p>
 * <p>Should the Door be locked, the player must collect DoorKeys to unlock it. Once unlocked, the player will
 * walk into a boundary area that triggers the door open. When opened, the door's state will become 'open'. From there,
 * the player may proceed to trigger the door event.</p>
 * @author Andrew Stephens
 */
public class Door extends APropTrigger implements IDrawable, IHUDDrawable, IUpdatable {

    /**<p>The action of the Door.</p>*/
    private final Type type = Type.UNDEFINED;
    /**<p>The Hashmap containing all spritesheets relative to a Door.</p>*/
    protected HashMap<Type, SpriteSheet> spriteSheets = new HashMap<>();
    /**<p>The current state of the door.</p>*/
    private State state = State.LOCKED;

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
    public Door(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx,
                float vy,
                int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);

        spriteSheets.put(Type.UNDEFINED, resources.getSpriteSheet("spritesheet_door").setLoopOnLast(false));
    }

    /**
     * <p>This is called when another ATrigger subtype defines their action to call this action. In other words, the
     * onReceive method is beholden to another trigger.</p>
     * <p>This event will open the door if it is closed.</p>
     */
    public void onReceive() {
        if(state != State.CLOSED) {
            return;
        }
        state = State.OPEN;
    }

    /**
     * <p>Unlocks the door.</p>
     */
    public void unlock() {
        if(state == State.LOCKED) {
            state = State.CLOSED;
        }
    }

    @Override
    public void doAction() {

        if(state != State.OPEN) {
            return;
        }

        if(!gameEnvironment.getLevelsList().navigateNextLevel()) {
            MainMenuEnvironment mainMenuEnvironment = gameEnvironment.getParentEnvironmentsHandler()
                    .swapToEnvironment(EnvironmentsHandler.EnvironmentType.MAIN_MENU, true).getMenuEnvironment();
            mainMenuEnvironment.navigateToLevelSelectPage();
            gameEnvironment.getParentEnvironmentsHandler().applyEnvironment();
        }
        gameEnvironment.reset();
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if(state == State.LOCKED || state == State.CLOSED) {
            return false;
        }

        return super.hasCollision(a, delta);
    }

    @Override
    public void reset() {
        super.reset();

        state = State.LOCKED;
        spriteSheets.get(type).reset();
    }

    @Override
    public void update(float delta) {
        if(state == State.OPEN) {
            spriteSheets.get(type).update(delta);
        }
    }

    @Override
    public void draw(Graphics2D g) {

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaleW = w * Config.scaledW_zoom;
        float scaleH = h * Config.scaledH_zoom;

        spriteSheets.get(type).draw(g, (int)offsetX, (int)offsetY, (int)scaleW, (int)scaleH);
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

    }

    /**
     * The enum State contains the states of the Door.
     * LOCKED - The locked, closed state.
     * CLOSED - The unlocked, closed state.
     * OPEN - The unlocked, ready state.
     */
    private enum State {
        CLOSED, OPEN, LOCKED
    }

    /**
     * A generic type to satisfy the spriteSheet map enum.
     */
    private enum Type {
        UNDEFINED
    }
}
