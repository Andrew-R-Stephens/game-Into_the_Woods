package models.runnables;

import models.prototypes.threading.ARunnable;
import models.prototypes.views.ACanvas;
import models.utils.config.Config;

/**
 * <p>The Runnable for Render calls. This is an integral piece of all Environments.</p>
 * <p>Contains a continuous loop that dictates the render rate based on a standard tick rate against the current
 * rate. The Current rate is found by determining the number of ticks made in a given second. The ticks are recorded
 * before resetting after a full second.</p>
 * The render rate is normalized by the framerate maximum, either set manually or by the display device's settings.</p>
 */
public class RenderRunnable extends ARunnable {

    private ACanvas canvas;

    /**
     * <p>Initializes the subtype Canvas object for this particular render loop.</p>
     * @param canvas The ACanvas used with this loop.
     */
    public void init(ACanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();

        final short targetFPS = Config.frameRate;
        double ns = 1000000000 / (float)targetFPS, delta = 0;

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / (1000000000 / (float) Config.frameRate);
            lastTime = now;

            if(delta >= 1) {
                updates++;
                canvas.render();
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
