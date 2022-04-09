package models.actors.gameactors.props.triggers.interactibles;

import models.actors.gameactors.TestActor;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.level.prop.trigger.ATrigger;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.Random;

public class TestTrigger extends ATrigger implements IDrawable, IUpdatable {

    public TestTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy, boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, 1, hasGravity, canMoveOnCollision);
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
            System.out.println("Triggered!");

            doAction();

            currentCycles++;
        }
        return hasCollision;
    }

    @Override
    public void doAction() {
        for(int i = 0; i < 10; i++) {
            gameEnvironment.queueActor(new TestActor(
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
        //super.update(delta);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
