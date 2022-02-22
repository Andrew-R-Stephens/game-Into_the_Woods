package graphics.ui;

import data.PreferenceData;
import viewmodels.controls.ControlsViewModel;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private ControlsViewModel controlsViewModel;

    //private GameCanvas gameCanvas;
    private boolean isRunning = false;

    public void init(PreferenceData preferences, GameCanvas gameCanvas, ControlsViewModel controlsViewModel){
        //this.gameCanvas = gameCanvas;
        this.controlsViewModel = controlsViewModel;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(gameCanvas);

        switch (preferences.getWindowType()) {
            case FULLSCREEN -> {
                double[] screenDim = {
                        Toolkit.getDefaultToolkit().getScreenSize().width,
                        Toolkit.getDefaultToolkit().getScreenSize().height
                };
                setPreferredSize(new Dimension((int) screenDim[0], (int) screenDim[1]));
                setUndecorated(true);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            case WINDOWED_FULLSCREEN -> {
                double[] screenDim = {
                        Toolkit.getDefaultToolkit().getScreenSize().width,
                        Toolkit.getDefaultToolkit().getScreenSize().height
                };
                setPreferredSize(new Dimension((int) screenDim[0], (int) screenDim[1]));
            }
            default -> {
                double[] screenDim = {
                        preferences.getWindowWidth(),
                        preferences.getWindowHeight()
                };
                setPreferredSize(new Dimension((int) screenDim[0], (int) screenDim[1]));
            }
        }

        setAlwaysOnTop(true);
        setResizable(false);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);


        addKeyListener(this.controlsViewModel.getKeyController());
        addMouseListener(this.controlsViewModel.getMouseController());

        Thread combinedThread = new Thread(() -> {

            long lastTime = System.nanoTime();
            final double amountOfTicks = preferences.getFrameRate();
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            int updates = 0;
            int lastFPS = preferences.getFrameRate();
            int frames = 0;
            long timer = System.currentTimeMillis();

            isRunning = true;
            while(isRunning) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                if(delta >= 1) {
                    updates++;
                    gameCanvas.update((double)lastFPS/ (double) PreferenceData.FRAMERATE_DEFAULT);
                    delta--;
                }
                if(gameCanvas.isRenderReady()) {
                    gameCanvas.render();
                }
                frames++;

                if(System.currentTimeMillis() - timer > 1000) {
                    timer += 1000; // add a thousand to timer
                    lastFPS = updates;
                    //System.out.println("Ticks: " + updates + " / " + PreferenceData.FRAMERATE_DEFAULT + " = " + (lastFPS / (double) PreferenceData.FRAMERATE_DEFAULT) + ", Cycles: " + frames + "\n");
                    updates = 0;
                    frames = 0;
                }
            }
        });
        combinedThread.start();

    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        isRunning = false;

        super.setDefaultCloseOperation(operation);
    }

    public void showWindow() {
        setVisible(true);
    }

    public void hideWindow() {
        setVisible(false);
    }

}
