package graphics.ui;

import data.PreferenceData;
import viewmodels.controls.ControlsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.LinkedList;

public class GameWindow extends JFrame {

    private ControlsViewModel controlsViewModel;

    private final JFrame thisFrame = this;

    double scaleFpsWindow = 1;

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
        addMouseMotionListener(this.controlsViewModel.getMouseController());


        Thread combinedThread = new Thread(() -> {



            long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
            final short targetFPS = preferences.getFrameRate();
            double ns = 1000000000 / (float)targetFPS, delta = 0;
            int updates = 0, lastFPS = targetFPS;

            LinkedList<Integer> points = new LinkedList<>();

            //Output data
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
                    for (Integer point : points) {
                        g.setColor(Color.GREEN);
                        g.drawLine((int)(x*scaleFpsWindow), getHeight() - (int)(scaleFpsWindow*point), (int)(scaleFpsWindow*(x-1)),getHeight() - (int)(scaleFpsWindow*prevPoint));
                        prevPoint = point;
                        x++;
                    }
                    g.setColor(Color.RED);
                    if(!points.isEmpty()) {
                        g.drawString(points.getLast() + "", 0, 20);
                    } else {
                        g.drawString(String.valueOf(targetFPS), 0, 20);
                    }
                    secondX ++;
                }
            };
            fpspanel.addMouseWheelListener(e -> {
                scaleFpsWindow -= e.getWheelRotation() * .1;
            });
            fpsframe.add(fpspanel);
            fpsframe.pack();
            fpsframe.setVisible(true);
            fpsframe.setLocation(thisFrame.getX() - fpsframe.getWidth(), thisFrame.getY());



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

                if(System.currentTimeMillis() - timer > 1000) {
                    timer += 1000; // add a thousand to timer
                    lastFPS = updates;
                    //System.out.println("Ticks: " + updates + " / " + PreferenceData.FRAMERATE_DEFAULT + " = " + (lastFPS / (double) PreferenceData.FRAMERATE_DEFAULT) + ", Cycles: " + lastFPS + "\n");
                    updates = 0;

                    points.addLast(lastFPS);
                    if(points.size()*scaleFpsWindow > (fpspanel.getWidth())) {
                        points.removeFirst();
                    }
                    fpsframe.repaint();
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
