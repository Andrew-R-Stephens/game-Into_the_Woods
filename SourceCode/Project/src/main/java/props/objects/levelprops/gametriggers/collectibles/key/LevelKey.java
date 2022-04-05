package props.objects.levelprops.gametriggers.collectibles.key;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import prototypes.actor.AActor;
import prototypes.level.prop.trigger.collectibles.ALevelCollectible;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;

public class LevelKey extends ALevelCollectible {
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
     */
    public LevelKey(GameEnvironment gameModel, float x, float y, float w, float h, float vx, float vy) {
        super(gameModel, x, y, w, h, vx, vy, false, false);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
    }

    @Override
    public void doAction() {
        super.doAction();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        if(!isActive) {
            return;
        }

        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.drawImage(Resources.getImage("key"), (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH, null);
    }

}
