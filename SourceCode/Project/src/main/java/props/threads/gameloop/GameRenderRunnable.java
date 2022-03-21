package props.threads.gameloop;

import graphics.ui.game.GameCanvas;
import models.data.PreferenceData;
import prototypes.threading.ARenderRunnable;

/**
 * The type Game render runnable.
 */
public class GameRenderRunnable extends ARenderRunnable {

    private GameCanvas canvas;

    /**
     * Init.
     *
     * @param canvas the canvas
     */
    public void init(GameCanvas canvas) {
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

                lastFrames = frames;
                frames = 0;
            }

        }
    }
}