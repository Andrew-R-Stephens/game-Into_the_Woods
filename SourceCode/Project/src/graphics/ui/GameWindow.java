package graphics.ui;

import data.PreferenceData;
import viewmodels.game.ControlsViewModel;

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


        Thread updateThread = new Thread(() -> {

            isRunning = true;

            while(isRunning) {

                gameCanvas.update();

                try {
                    long now = System.nanoTime();
                    long updateTime = System.nanoTime() - now;

                    long OPTIMAL_TIME = 1000000000 / preferences.getFrameRate();
                    long wait = (long) ((OPTIMAL_TIME - updateTime) / 1000000.0);

                    if (wait < 0) {
                        wait = 1;
                    }

                    Thread.sleep(wait);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        updateThread.start();

        Thread renderThread = new Thread(() -> {

            isRunning = true;

            while(isRunning) {

                gameCanvas.render();

                try {
                    long now = System.nanoTime();
                    long updateTime = System.nanoTime() - now;

                    long OPTIMAL_TIME = 1000000000 / preferences.getFrameRate();
                    long wait = (long) ((OPTIMAL_TIME - updateTime) / 1000000.0);

                    if (wait < 0) {
                        wait = 1;
                    }

                    Thread.sleep(wait);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        renderThread.start();

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
