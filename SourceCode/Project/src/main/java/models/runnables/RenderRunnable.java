package models.runnables;

import models.prototypes.threading.ARunnable;
import models.prototypes.window.ACanvas;
import models.utils.config.ConfigData;

public class RenderRunnable extends ARunnable {

    private ACanvas canvas;

    public void init(ACanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();

        final short targetFPS = ConfigData.frameRate;
        double ns = 1000000000 / (float)targetFPS, delta = 0;

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            //delta += (now - lastTime) / ns;
            delta += (now - lastTime) / (1000000000 / (float)ConfigData.frameRate);
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
