package prototypes.actor.trigger;

import models.camera.Camera;
import models.data.PreferenceData;
import models.environments.game.GameModel;
import prototypes.actor.AActor;
import prototypes.actor.pawn.character.ACharacter;
import prototypes.level.prop.ALevelProp;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class ATrigger extends ALevelProp {

    /**
     * The Game model.
     */
    protected GameModel gameModel;

    /**
     * The Can move on collision.
     */
    protected boolean canMoveOnCollision;

    /**
     * The Max cycles.
     */
    protected int MAX_CYCLES = 1;
    /**
     * The Current cycles.
     */
    protected int currentCycles = 0;

    /**
     * Instantiates a new A trigger.
     *
     * @param gameModel          the game model
     * @param x                  the x
     * @param y                  the y
     * @param w                  the w
     * @param h                  the h
     * @param vx                 the vx
     * @param vy                 the vy
     * @param hasGravity         the has gravity
     * @param canMoveOnCollision the can move on collision
     */
    protected ATrigger(GameModel gameModel, float x, float y, float w, float h, float vx, float vy, boolean hasGravity, boolean canMoveOnCollision) {
        super(x, y, w, h, vx, vy, hasGravity);

        this.gameModel = gameModel;
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

        double offsetX = ((x * PreferenceData.scaledW) + (Camera.x));
        double offsetY = ((y * PreferenceData.scaledH) + (Camera.y));

        double scaledW = w * PreferenceData.scaledW;
        double scaledH = h * PreferenceData.scaledH;

        g.setColor(new Color(0, 255, 0, 50));
        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(new Color(100, 255, 100));
        g.drawRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));
        g.setColor(Color.BLACK);
        g.drawString("Trigger Area", (int) (offsetX) + 3, (int) (offsetY) + 12);
    }
}
