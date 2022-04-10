package models.actors.gameactors.props.triggers.interactibles;

import models.camera.Camera;
import models.environments.EnvironmentsHandler;
import models.environments.game.GameEnvironment;
import models.environments.menus.mainmenu.MainMenuEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.prop.APropTrigger;
import models.sprites.SpriteSheet;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.HashMap;

public class DoorTrigger extends APropTrigger implements IDrawable, IHUDDrawable, IUpdatable {

    public State state = State.LOCKED;
    public enum State {
        CLOSED, OPEN, LOCKED
    }

    public Type type = Type.UNDEFINED;
    public enum Type {
        UNDEFINED
    }

    protected HashMap<Type, SpriteSheet> spriteSheets = new HashMap<>();

    public DoorTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy,
                         int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, hasGravity, canMoveOnCollision);

        spriteSheets.put(Type.UNDEFINED, Resources.getSpriteSheet("door_spritesheet").setLoopOnLast(false));
    }

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

    public void onReact() {
        if(state != State.CLOSED) {
            return;
        }

        state = State.OPEN;
        spriteSheets.get(type).setCurrentFrame(state.ordinal());
    }

    public void doAction() {

        if(state != State.OPEN) {
            return;
        }

        gameEnvironment.reset();
        if(!gameEnvironment.getLevelModel().navigateNextLevel()) {
            gameEnvironment.reset();
            MainMenuEnvironment mainMenuEnvironment = gameEnvironment.parentEnvironmentsModel
                    .swapToEnvironmentType(EnvironmentsHandler.EnvironmentType.MAIN_MENU, true).getMenuEnvironment();
            mainMenuEnvironment.navigateToLevelSelectPage();
            gameEnvironment.parentEnvironmentsModel.applyEnvironment();
        }

    }

    @Override
    public void update(float delta) {
        //super.update(delta);
    }

    @Override
    public void draw(Graphics g) {

        double offsetX = ((x * ConfigData.scaledW) + (Camera.camX));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.camY));

        double scaleW = w * ConfigData.scaledW;
        double scaleH = h * ConfigData.scaledH;

        spriteSheets.get(type).draw(g, (int)offsetX, (int)offsetY, (int)scaleW, (int)scaleH);

        /*
        g.setColor(new Color(150, 50, 150, 50));
        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(new Color(100, 255, 100));
        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(Color.BLACK);
        g.drawString("Door Trigger", (int) (offsetX) + 3, (int) (offsetY) + 12);
        */
    }
}
