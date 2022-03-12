package graphics.ui.game;

import viewmodels.controls.ControlsModel;
import viewmodels.data.PreferenceData;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * The Application Window contains the Canvas which is currently active.
 */
public class GameWindow extends JFrame {

    private final JFrame thisFrame = this;
    private int updates = 0, frames = 0, lastUpdates = Integer.MAX_VALUE, lastFrames = Integer.MAX_VALUE;
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
            final short targetFPS = PreferenceData.GAME_UPDATE_RATE;
            double ns = 1000000000 / (float)targetFPS, delta = 0;

            LinkedList<Integer> fpsPoints = new LinkedList<>();

            // FRAMERATE DISPLAY

            JFrame fpsframe = new JFrame();
            fpsframe.setAlwaysOnTop(true);
            fpsframe.setPreferredSize(new Dimension(200, thisFrame.getHeight()));

            JPanel fpspanel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    g.fillRect(0,0,getWidth(), getHeight());
                    g.setColor(Color.GREEN);

                    int x = 0, prevPoint = targetFPS;
                    for (Integer point : fpsPoints) {
                        g.drawLine(
                                (int)(x * fpsWindowScale),
                                getHeight() - (int)(fpsWindowScale * point),
                                (int)(fpsWindowScale * (x-1)),
                                getHeight() - (int)(fpsWindowScale * prevPoint));
                        prevPoint = point;
                        x++;
                    }

                    g.setColor(Color.GREEN);

                    if(!fpsPoints.isEmpty()) {
                        g.drawString(fpsPoints.getLast() + "", 0, 20);
                    } else {
                        g.drawString(String.valueOf(targetFPS), 0, 20);
                    }
                }
            };

            fpspanel.addMouseWheelListener(e -> fpsWindowScale -= e.getWheelRotation() * .1);
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
                        float frameRatio = lastUpdates / (float) PreferenceData.GAME_UPDATE_RATE;
                        gameCanvas.update(frameRatio);
                        //gameCanvas.update(.5f);
                        updates++;
                    });
                    t.start();

                    delta--;
                }
                if(gameCanvas.isRenderReady()) {
                    Thread t = new Thread(() -> {
                        gameCanvas.render();
                        frames++;
                    });
                    t.start();
                }

                if(System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;

                    lastUpdates = updates;
                    lastFrames = frames;

                    updates = 0;
                    frames = 0;

                    fpsPoints.addLast(lastUpdates);
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
