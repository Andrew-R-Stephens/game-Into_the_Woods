package models.runnables.menu;

import graphics.canvas.menu.MenuCanvas;
import utils.config.PreferenceData;
import prototypes.threading.ARenderRunnable;

/**
 * The type Menu render runnable.
 */
public class MenuRenderRunnable extends ARenderRunnable {

    private MenuCanvas canvas;

    /**
     * Init.
     *
     * @param canvas the canvas
     */
    public void init(MenuCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
        final short targetFPS = PreferenceData.frameRate;
        double ns = 1000000000 / (float)targetFPS, delta = 0;

        // GAME LOOP

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                frames++;
                canvas.render();
                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                System.out.println("Menu Rendering");
                lastFrames = frames;
                frames = 0;
            }

        }
    }


}
