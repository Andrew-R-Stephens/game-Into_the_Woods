package models.runnables;

import prototypes.threading.ARunnable;
import prototypes.window.ACanvas;
import utils.config.ConfigData;

/**
 * The type Game render runnable.
 */
public class RenderRunnable extends ARunnable {

    private ACanvas canvas;

    /**
     * Init.
     *
     * @param canvas the canvas
     */
    public void init(ACanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
        final short targetFPS = ConfigData.frameRate;
        double ns = 1000000000 / (float)targetFPS, delta = 0;

        // GAME LOOP

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                updates++;
                canvas.render();
                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                System.out.println("Rendering" + canvas.getClass());
                lastUpdates = updates;
                updates = 0;
            }

        }
    }
}
