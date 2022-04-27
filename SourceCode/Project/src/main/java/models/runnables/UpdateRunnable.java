package models.runnables;

import models.prototypes.environments.AEnvironment;
import models.prototypes.threading.ARunnable;
import models.utils.config.Config;

/**
 * <p>The Runnable for Update calls. This is an integral piece of all Environments.</p>
 * <p>Contains a continuous loop that dictates the update rate based on a standard tick rate against the current
 * rate. The Current rate is found by determining the number of ticks made in a given second. The ticks are recorded
 * before resetting after a full second, and the ratio of actual updates vs target updates is found. This ratio,
 * or delta, is passed into the update(float) method of the Environment references. The delta normalizes the
 * updates for all updatable objects in the target environment.</p>
 * @author Andrew Stephens
 */
public class UpdateRunnable extends ARunnable {

    private AEnvironment environment;

    /**
     * <p>Initializes the AEnvironment for this particular Update loop.</p>
     * @param environment the AEnvironment used with this loop.
     */
    public void init(AEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
        final short targetTicks = Config.GAME_UPDATE_RATE;
        float ns = 1000000000 / (float) targetTicks, delta = 0;
        float updateRatio = 1;

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {

                updateRatio = lastUpdates / (float) Config.GAME_UPDATE_RATE;
                environment.update(updateRatio);
                updates++;

                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                lastUpdates = updates;
                updates = 0;


            }

        }
    }
}
