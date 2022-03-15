package threads.gameloop;

import graphics.ui.game.GameCanvas;
import models.data.PreferenceData;
import threads.ARenderRunnable;

public class GameRenderRunnable extends ARenderRunnable {

    private GameCanvas canvas;

    public GameRenderRunnable(GameCanvas canvas) {
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
                System.out.println(lastFrames);
                frames = 0;
            }

        }
    }
}
