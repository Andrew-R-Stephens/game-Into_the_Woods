package models.runnables.game;

import models.environments.game.GameEnvironment;
import prototypes.threading.AUpdateRunnable;
import utils.config.PreferenceData;

/**
 * The type Game update runnable.
 */
public class GameUpdateRunnable extends AUpdateRunnable {

    private GameEnvironment gameModel;

    /**
     * Init.
     *
     * @param gameModel the game model
     */
    public void init(GameEnvironment gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
        final short targetTicks = PreferenceData.GAME_UPDATE_RATE;
        double ns = 1000000000 / (float) targetTicks, delta = 0;
        float updateRatio = 1;

                // GAME LOOP

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                updateRatio = lastUpdates / (float) PreferenceData.GAME_UPDATE_RATE;
                gameModel.update(updateRatio);
                updates++;

                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                System.out.println("Game Updating");
                lastUpdates = updates;
                updates = 0;

                if(lastUpdates < (targetTicks*.8)) {
                    System.out.println("Calling GC");
                    gameModel.gc();
                }
            }

        }
    }
}
