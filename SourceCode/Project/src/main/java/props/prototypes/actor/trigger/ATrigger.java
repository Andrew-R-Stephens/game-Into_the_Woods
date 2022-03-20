package props.prototypes.actor.trigger;

import models.camera.Camera;
import models.data.PreferenceData;
import models.environments.game.GameModel;
import props.prototypes.actor.AActor;
import props.prototypes.actor.pawn.character.ACharacter;
import props.prototypes.level.prop.ALevelProp;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class ATrigger extends ALevelProp {

    protected GameModel gameModel;

    protected boolean canMoveOnCollision;

    protected int MAX_CYCLES = 1;
    protected int currentCycles = 0;

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
