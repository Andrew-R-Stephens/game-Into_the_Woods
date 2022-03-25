package graphics.window;

import models.environments.EnvironmentsHandler;
import prototypes.window.AWindow;
import utils.config.PreferenceData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The Application Window contains the Canvas which is currently active.
 */
public class MainWindow extends AWindow {

    private EnvironmentsHandler environmentsModel;

    private Thread updatesThread = null;
    private Thread rendersThread = null;

    /**
     * Init.
     *
     * @param preferences the preferences
     */
    public void init(PreferenceData preferences){
        constructWindowAndDimensions(preferences);
    }

    private void constructWindowAndDimensions(PreferenceData preferences) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        switch (preferences.getWindowType()) {
            case FULLSCREEN_EXCLUSIVE -> {
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
            case WINDOWED_BORDERLESS -> {
                int width = preferences.getWindowWidthSelected();
                int height = preferences.getWindowHeightSelected();
                preferences.setWindowWidthActual(width);
                preferences.setWindowHeightActual(height);

                setUndecorated(true);

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

        float dScalingX = (float) getGraphicsConfiguration().getDefaultTransform().getScaleX();
        float dScalingY = (float) getGraphicsConfiguration().getDefaultTransform().getScaleY();

        System.out.println(dScalingX + " " + dScalingY);

        PreferenceData.scaledW = PreferenceData.scaledW / dScalingX;
        PreferenceData.scaledH = PreferenceData.scaledH / dScalingY;

        System.out.println(PreferenceData.scaledW + " " + PreferenceData.scaledH);

        setAlwaysOnTop(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Init environments model.
     *
     * @param environmentsModel the environments model
     */
    public void initEnvironmentsModel(EnvironmentsHandler environmentsModel) {
        this.environmentsModel = environmentsModel;
    }

    private void initThreads() {

        if(updatesThread != null) {
            updatesThread.interrupt();
            updatesThread = null;
        }
        if(rendersThread != null) {
            rendersThread.interrupt();
            rendersThread = null;
        }

        updatesThread = new Thread(environmentsModel.getCurrentUpdateRunnable());
        rendersThread = new Thread(environmentsModel.getCurrentRenderRunnable());

        updatesThread.start();
        rendersThread.start();
    }

    /**
     * Apply environment and canvas.
     *
     * @param environmentType the environment type
     */
    public void applyEnvironmentAndCanvas(EnvironmentsHandler.EnvironmentType environmentType) {
        environmentsModel.setCurrentEnvironment(environmentType);

        applyEnvironmentAndCanvas();
    }

    public void clearCurrentEnvironment() {
        for(MouseListener l : getContentPane().getMouseListeners()) {
            getContentPane().removeMouseListener(l);
        }
        for(MouseMotionListener l : getContentPane().getMouseMotionListeners()) {
            getContentPane().removeMouseMotionListener(l);
        }
        for(KeyListener l : getKeyListeners()) {
            removeKeyListener(l);
        }

        getContentPane().removeAll();
        repaint();
    }

    /**
     * Apply environment and canvas.
     */
    public void applyEnvironmentAndCanvas() {
        clearCurrentEnvironment();

        addKeyListener(environmentsModel.getCurrentEnvironment().getKeyController());
        getContentPane().addMouseListener(environmentsModel.getCurrentEnvironment().getMouseController());
        getContentPane().addMouseMotionListener(environmentsModel.getCurrentEnvironment().getMouseController());

        add(environmentsModel.getCurrentCanvas());
        pack();

        initThreads();
    }

}
