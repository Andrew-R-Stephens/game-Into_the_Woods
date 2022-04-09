package models.actors.gameactors.props.triggers.interactibles;

import models.actors.gameactors.PlayerAvatar;
import models.camera.Camera;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.config.ConfigData;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;

public class SpikesTrigger extends ATrigger implements IDrawable, IHUDDrawable, IUpdatable {
    public SpikesTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy,
                         int MAX_CYCLES) {
        super(gameEnvironment, x, y, w, h, vx, vy, MAX_CYCLES, false, false);
    }

    @Override
    public void doAction() {
        gameEnvironment.reset();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        if(!(a instanceof PlayerAvatar)) {
            return false;
        }

        boolean hasCollision = super.hasCollision(a, delta);

        if(MAX_CYCLES != -1) {
            if (currentCycles > MAX_CYCLES) {
                return false;
            }
        }

        if(hasCollision) {
            doAction();
            currentCycles++;
        }

        return hasCollision;
    }

    @Override
    public void draw(Graphics g) {
        double offsetX = ((x * ConfigData.scaledW) + (Camera.camX));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.camY));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;

        g.drawImage(Resources.getImage("spikes"), (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH, null);
    }
}
