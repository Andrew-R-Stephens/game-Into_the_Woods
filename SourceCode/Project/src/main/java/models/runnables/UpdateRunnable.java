package models.runnables;

import prototypes.threading.ARunnable;
import prototypes.window.environments.AEnvironment;
import utils.config.ConfigData;

/**
 * The type Game update runnable.
 */
public class UpdateRunnable extends ARunnable {

    private AEnvironment environment;

    /**
     * Init.
     *
     * @param environment the game model
     */
    public void init(AEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
        final short targetTicks = ConfigData.GAME_UPDATE_RATE;
        double ns = 1000000000 / (float) targetTicks, delta = 0;
        float updateRatio = 1;

                // GAME LOOP

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                updateRatio = lastUpdates / (float) ConfigData.GAME_UPDATE_RATE;
                environment.update(updateRatio);
                updates++;

                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                System.out.println("Updating" + environment.getClass());
                lastUpdates = updates;
                updates = 0;

            }

        }
    }
}
