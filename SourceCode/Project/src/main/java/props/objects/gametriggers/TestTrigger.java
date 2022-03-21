package props.objects.gametriggers;

import models.environments.game.GameModel;
import props.objects.gameactors.TestActor;
import prototypes.actor.AActor;
import prototypes.actor.trigger.ATrigger;

import java.awt.*;
import java.util.Random;

/**
 * The type Test trigger.
 */
public class TestTrigger extends ATrigger {

    /**
     * Instantiates a new Test trigger.
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
    public TestTrigger(GameModel gameModel, float x, float y, float w, float h, float vx, float vy, boolean hasGravity, boolean canMoveOnCollision) {
        super(gameModel, x, y, w, h, vx, vy, hasGravity, canMoveOnCollision);
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        boolean hasCollision = super.hasCollision(a, delta);

        if(hasCollision && (currentCycles < MAX_CYCLES)) {
            System.out.println("Triggered!");

            doAction();

            currentCycles++;
        }

        return hasCollision;
    }

    @Override
    public void doAction() {
        for(int i = 0; i < 10; i++) {
            gameModel.queueAddGameObject(new TestActor(
                    x, y,
                    10,
                    10,
                    new Random().nextFloat(-10f, 10f), new Random().nextFloat(-10f, 10f),
                    true)
            );
        }
    }


    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
