package main;

import models.data.PreferenceData;
import models.environments.EnvironmentsModel;
import prototypes.window.AWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The Application Window contains the Canvas which is currently active.
 */
public class MainWindow extends AWindow {

    private EnvironmentsModel environmentsModel;

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

        float dScalingX = (float) getContentPane().getGraphicsConfiguration().getDefaultTransform().getScaleX();
        float dScalingY = (float) getContentPane().getGraphicsConfiguration().getDefaultTransform().getScaleX();

        PreferenceData.scaledW = PreferenceData.scaledW / dScalingX;
        PreferenceData.scaledW = PreferenceData.scaledW / dScalingY;

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
    public void initEnvironmentsModel(EnvironmentsModel environmentsModel) {
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
    public void applyEnvironmentAndCanvas(EnvironmentsModel.EnvironmentType environmentType) {
        environmentsModel.setCurrentEnvironment(environmentType);

        applyEnvironmentAndCanvas();
    }

    /**
     * Apply environment and canvas.
     */
    public void applyEnvironmentAndCanvas() {
        getContentPane().removeAll();
        repaint();

        addKeyListener(environmentsModel.getCurrentEnvironment().getKeyController());
        getContentPane().addMouseListener(environmentsModel.getCurrentEnvironment().getMouseController());
        getContentPane().addMouseMotionListener(environmentsModel.getCurrentEnvironment().getMouseController());

        add(environmentsModel.getCurrentCanvas());
        pack();

        initThreads();
    }

}
