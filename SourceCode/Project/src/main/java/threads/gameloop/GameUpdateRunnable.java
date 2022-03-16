package threads.gameloop;

import models.data.PreferenceData;
import models.states.game.GameModel;
import threads.AUpdateRunnable;

public class GameUpdateRunnable extends AUpdateRunnable {

    private GameModel gameModel;

    public GameUpdateRunnable(GameModel gameModel) {
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

                lastUpdates = updates;
                System.out.println(lastUpdates);
                updates = 0;

                if(lastUpdates < (targetTicks*.8)) {
                    System.out.println("Calling GC");
                    gameModel.gc();
                }
            }

        }
    }
}
