package models.actors.gameactors.props.triggers.collectibles.key;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.collectibles.ACollectibleTrigger;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class LevelKey extends ACollectibleTrigger implements IDrawable, IUpdatable {
    public LevelKey(GameEnvironment gameModel, float x, float y, float w, float h, float vx, float vy) {
        super(gameModel, x, y, w, h, vx, vy, 1,false, false);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        return super.hasCollision(a, delta);
    }

    @Override
    public void doAction() {
        super.doAction();

        if(gameEnvironment.getPlayerInventory().getKeyCount() >= gameEnvironment.getCurrentLevel().getKeyCount()) {
            gameEnvironment.getLevelModel().getCurrentLevel().unlockDoor();
        }
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

        double offsetX = ((x * ConfigData.scaledW) + (Camera.camX));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.camY));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.drawImage(Resources.getImage("key"), (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH, null);
    }

}
