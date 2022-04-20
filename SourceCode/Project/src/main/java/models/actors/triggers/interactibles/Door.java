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
 *
 */
public class Door extends APropTrigger implements IDrawable, IHUDDrawable, IUpdatable {

    private State state = State.LOCKED;
    private final Type type = Type.UNDEFINED;

    protected HashMap<Type, SpriteSheet> spriteSheets = new HashMap<>();

    /**
     * <p></p>
     * @param resources -
     * @param gameEnvironment -
     * @param x -
     * @param y -
     * @param w -
     * @param h -
     * @param vx -
     * @param vy -
     * @param MAX_CYCLES -
     * @param hasGravity -
     * @param canMoveOnCollision -
     */
    public Door(Resources resources, GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx,
                float vy,
                int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(resources, gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);

        spriteSheets.put(Type.UNDEFINED, resources.getSpriteSheet("spritesheet_door").setLoopOnLast(false));
    }

    /**
     * <p></p>
     */
    public void onReceive() {
        if(state != State.CLOSED) {
            return;
        }
        state = State.OPEN;
    }

    /**
     * <p></p>
     */
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

    /**
     * <p></p>
     */
    public void unlock() {
        if(state == State.LOCKED) {
            state = State.CLOSED;
        }
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
     *
     */
    private enum State {
        CLOSED, OPEN, LOCKED
    }

    /**
     *
     */
    private enum Type {
        UNDEFINED
    }
}
