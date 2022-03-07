package graphics.ui;

import data.PreferenceData;
import viewmodels.controls.ControlsModel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameWindow extends JFrame {

    private final JFrame thisFrame = this;
    private int updates = 0, frames = 0, lastFPS = Integer.MAX_VALUE;
    private double avgFrames;
    private boolean isRunning;


    private double fpsWindowScale = 1;

    public void init(PreferenceData preferences, GameCanvas gameCanvas, ControlsModel controlsViewModel){

        add(gameCanvas);
        constructWindowAndDimensions(preferences);

        addKeyListener(controlsViewModel.getKeyController());
        addMouseListener(controlsViewModel.getMouseController());
        addMouseMotionListener(controlsViewModel.getMouseController());

        // CREATE GAME THREAD
        Thread gameThread = new Thread(() -> {

            long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
            final short targetFPS = preferences.getFrameRate();
            double ns = 1000000000 / (float)targetFPS, delta = 0;
            avgFrames = targetFPS;

            LinkedList<Integer> fpsPoints = new LinkedList<>();


            // FRAMERATE DISPLAY

            JFrame fpsframe = new JFrame();
            fpsframe.setAlwaysOnTop(true);
            fpsframe.setPreferredSize(new Dimension(200, thisFrame.getHeight()));
            JPanel fpspanel = new JPanel(){
                int secondX = 0;
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int x = 0, prevPoint = targetFPS;
                    g.fillRect(0,0,getWidth(), getHeight());
                    for (Integer point : fpsPoints) {
                        g.setColor(Color.GREEN);
                        g.drawLine(
                                (int)(x* fpsWindowScale),
                                getHeight() - (int)(fpsWindowScale * point),
                                (int)(fpsWindowScale *(x-1)),
                                getHeight() - (int)(fpsWindowScale * prevPoint));
                        prevPoint = point;
                        x++;
                    }
                    g.setColor(Color.RED);
                    if(!fpsPoints.isEmpty()) {
                        g.drawString(fpsPoints.getLast() + "", 0, 20);
                    } else {
                        g.drawString(String.valueOf(targetFPS), 0, 20);
                    }
                    secondX ++;
                }
            };
            fpspanel.addMouseWheelListener(e -> {
                fpsWindowScale -= e.getWheelRotation() * .1;
            });
            fpsframe.add(fpspanel);
            fpsframe.pack();
            fpsframe.setVisible(true);
            fpsframe.setLocation(thisFrame.getX() - fpsframe.getWidth(), thisFrame.getY());


            // GAME LOOP

            isRunning = true;
            while(isRunning) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                if(delta >= 1) {
                    Thread t = new Thread(() -> {
                        //double frameRatio = (double) lastFPS / (double) PreferenceData.FRAMERATE_DEFAULT;
                        double frameRatio = lastFPS / (double) PreferenceData.FRAMERATE_DEFAULT;
                        gameCanvas.update(frameRatio);
                        updates++;
                    });
                    t.start();

                    delta--;
                }
                if(gameCanvas.isRenderReady()) {
                    Thread t = new Thread(() -> {
                        gameCanvas.render();
                        frames++;
                    }
                    );
                    t.start();
                }

                if(System.currentTimeMillis() - timer > 1000) {
                    timer += 1000; // add a thousand to timer
                    lastFPS = frames;
                    avgFrames = .5 * (lastFPS + avgFrames);
                    updates = 0;
                    frames = 0;

                    fpsPoints.addLast((int)avgFrames);
                    if(fpsPoints.size()* fpsWindowScale > (fpspanel.getWidth())) {
                        fpsPoints.removeFirst();
                    }
                    fpsframe.repaint();
                }

            }
        });
        gameThread.start();

    }

    private void constructWindowAndDimensions(PreferenceData preferences) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        switch (preferences.getWindowType()) {
            case FULLSCREEN -> {
                int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                int height = Toolkit.getDefaultToolkit().getScreenSize().height;
                preferences.setWindowWidthActual(width);
                preferences.setWindowHeightActual(height);

                setPreferredSize(new Dimension(width, height));
                setUndecorated(true);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            case WINDOWED_FULLSCREEN -> {
                int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                int height =Toolkit.getDefaultToolkit().getScreenSize().height;
                preferences.setWindowWidthActual(width);
                preferences.setWindowHeightActual(height);

                setPreferredSize(new Dimension(width, height));
            }
            default -> {
                int width = preferences.getWindowWidthSelected();
                int height = preferences.getWindowHeightSelected();
                preferences.setWindowWidthActual(width);
                preferences.setWindowHeightActual(height);

                setPreferredSize(new Dimension(width, height));
            }
        }

        setAlwaysOnTop(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        isRunning = false;

        super.setDefaultCloseOperation(operation);
    }

}
