package prototypes.level.prop.trigger;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import prototypes.actor.AActor;
import prototypes.actor.pawn.character.ACharacter;
import prototypes.level.prop.ALevelProp;
import utils.config.ConfigData;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class ATrigger extends ALevelProp {

    /**
     * The Game model.
     */
    protected GameEnvironment gameEnvironment;

    /**
     * The Can move on collision.
     */
    protected boolean canMoveOnCollision;

    /**
     * The Max cycles.
     */
    protected int MAX_CYCLES = -1;
    /**
     * The Current cycles.
     */
    protected int currentCycles = 0;

    /**
     * Instantiates a new A trigger.
     *
     * @param gameEnvironment    the game model
     * @param x                  the x
     * @param y                  the y
     * @param w                  the w
     * @param h                  the h
     * @param vx                 the vx
     * @param vy                 the vy
     * @param hasGravity         the has gravity
     * @param canMoveOnCollision the can move on collision
     */
    protected ATrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy, int MAX_CYCLES, boolean hasGravity, boolean canMoveOnCollision) {
        super(x, y, w, h, vx, vy, hasGravity);

        this.gameEnvironment = gameEnvironment;

        this.MAX_CYCLES = MAX_CYCLES;
        this.canMoveOnCollision = canMoveOnCollision;
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if(!(a instanceof ACharacter)) {
            return false;
        }

        return super.hasCollision(a, delta, canMoveOnCollision);
    }

    /**
     * Do action.
     */
    public abstract void doAction();

    @Override
    public void draw(Graphics g) {

        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.setColor(new Color(0, 255, 0, 50));
        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(new Color(100, 255, 100));
        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(Color.BLACK);
        g.drawString("Trigger Area", (int) (offsetX) + 3, (int) (offsetY) + 12);
    }
}
