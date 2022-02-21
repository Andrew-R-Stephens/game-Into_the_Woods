package graphics.ui;

import files.PreferenceData;
import game.objects.entities.Player;
import viewmodels.game.GameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameWindow extends JFrame {

    private GameViewModel gameViewModel;

    private GameCanvas gameCanvas;
    private boolean isRunning = false;

    public GameWindow() {
    }

    public void init(PreferenceData preferences, GameCanvas gameCanvas, GameViewModel gameViewModel){
        this.gameCanvas = gameCanvas;
        this.gameViewModel = gameViewModel;

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

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE -> {
                        System.out.println("Quitting");
                        System.exit(1);
                    }
                    case KeyEvent.VK_LEFT -> {
                        gameCanvas.isPressed[0] = true;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        gameCanvas.isPressed[1] = true;
                    }
                    case KeyEvent.VK_UP -> {
                        gameCanvas.isPressed[2] = true;
                    }
                    case KeyEvent.VK_DOWN -> {
                        gameCanvas.isPressed[3] = true;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> {
                        gameCanvas.isPressed[0] = false;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        gameCanvas.isPressed[1] = false;
                    }
                    case KeyEvent.VK_UP -> {
                        gameCanvas.isPressed[2] = false;
                    }
                    case KeyEvent.VK_DOWN -> {
                        gameCanvas.isPressed[3] = false;
                    }
                }
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                gameViewModel.addGameObject(new Player(e.getX(), e.getY(), 10, 10, 0, 0, 9.8));
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {  }
        });


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
