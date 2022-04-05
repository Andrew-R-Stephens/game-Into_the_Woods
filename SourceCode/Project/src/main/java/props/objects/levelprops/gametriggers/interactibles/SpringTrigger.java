package props.objects.levelprops.gametriggers.interactibles;

import models.camera.Camera;
import models.environments.game.GameEnvironment;
import prototypes.actor.AActor;
import prototypes.level.prop.trigger.ATrigger;
import utils.config.ConfigData;
import utils.files.Resources;

import java.awt.*;

public class SpringTrigger extends ATrigger {

    AActor lastActor;

    /**
     * Instantiates a new LevelSpring.
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
    public SpringTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy,
                            boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, -1, hasGravity, canMoveOnCollision);

        spriteSheet = Resources.loadSpriteSheet("spring_spritesheet");
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        boolean hasCollision = super.hasCollision(a, delta);

        if(MAX_CYCLES != -1) {
            if (currentCycles > MAX_CYCLES) {
                return false;
            }
        }

        if(hasCollision) {
            lastActor = a;

            doAction();
            currentCycles++;
        }

        return hasCollision;
    }

    @Override
    public void doAction() {
        //isActivated = true;
        lastActor.setVY(-100);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        /*
        if(isActivated) {
            spriteSheet.update(delta);
            if(spriteSheet.isLastFrame()) {
                isActivated = false;
                spriteSheet.setCurrentFrame(0);
            }
        }
        */
    }

    @Override
    public void draw(Graphics g) {
        /*
        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;
        spriteSheet.draw(g, (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH);
        */
        super.draw(g);
    }
}
