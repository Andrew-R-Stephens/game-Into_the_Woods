package models.actors.triggers.collectibles.key;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.collectibles.ACollectibleTrigger;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class DoorKey extends ACollectibleTrigger implements IDrawable, IUpdatable {
    public DoorKey(Resources resources, GameEnvironment gameModel, float x, float y, float w, float h, float vx, float vy) {
        super(resources, gameModel, x, y, w, h, vx, vy, 1,false, false);
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
    public void draw(Graphics2D g) {
        if(!isActive) {
            return;
        }

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.camX));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.camY));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        g.drawImage(resources.getImage("key"), (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH, null);
    }

    @Override
    public void drawAsHUD(Graphics2D g) {

        g.setColor(Color.WHITE);

        float offsetX = ((x * Config.scaledW_zoom) + (Camera.mapX * Config.scaledW_zoom));
        float offsetY = ((y * Config.scaledH_zoom) + (Camera.mapY * Config.scaledH_zoom));

        float scaledW = w * Config.scaledW_zoom;
        float scaledH = h * Config.scaledH_zoom;

        g.fillRect((int) ((offsetX)), (int) (offsetY), (int) (scaledW), (int) (scaledH));

    }

}
