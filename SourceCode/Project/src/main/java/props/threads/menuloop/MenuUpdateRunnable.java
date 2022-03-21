package props.threads.menuloop;

import models.data.PreferenceData;
import models.environments.menus.mainmenu.MainMenuModel;
import prototypes.threading.AUpdateRunnable;

/**
 * The type Menu update runnable.
 */
public class MenuUpdateRunnable extends AUpdateRunnable {

    private MainMenuModel menuModel;

    /**
     * Init.
     *
     * @param menuModel the menu model
     */
    public void init(MainMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
        final short targetFPS = PreferenceData.GAME_UPDATE_RATE;
        double ns = 1000000000 / (float)targetFPS, delta = 0;
        float updateRatio = 1;

        // GAME LOOP

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                updateRatio = lastUpdates / (float) PreferenceData.GAME_UPDATE_RATE;
                menuModel.update(updateRatio);
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
