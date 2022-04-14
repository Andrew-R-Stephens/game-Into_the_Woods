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

public class DoorKey extends ACollectibleTrigger implements IDrawable, IUpdatable {
    public DoorKey(GameEnvironment gameModel, float x, float y, float w, float h, float vx, float vy) {
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
            gameEnvironment.getLevelsList().getCurrentLevel().unlockDoor();
        }
    }

    @Override
    public void reset() {
        super.reset();
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

        float offsetX = ((x * ConfigData.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * ConfigData.scaledH_zoom) + (Camera.camY));

        float scaledW = w * ConfigData.scaledW_zoom;
        float scaledH = h * ConfigData.scaledH_zoom;

        g.drawImage(Resources.getImage("key"), (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH, null);
    }

    @Override
    public void drawAsHUD(Graphics g) {

        g.setColor(Color.WHITE);

        float offsetX = ((x * ConfigData.scaledW_zoom) + (Camera.targX * ConfigData.scaledW_zoom));
        float offsetY = ((y * ConfigData.scaledH_zoom) + (Camera.targY * ConfigData.scaledH_zoom));

        float scaledW = w * ConfigData.scaledW_zoom;
        float scaledH = h * ConfigData.scaledH_zoom;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));

    }

}
