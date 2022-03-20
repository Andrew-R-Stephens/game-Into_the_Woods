package main;

import models.data.PreferenceData;
import props.prototypes.window.AWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The Application Window contains the Canvas which is currently active.
 */
public class MainWindow extends AWindow {

    private EnvironmentsModel environmentsModel;

    private Thread updatesThread = null;
    private Thread rendersThread = null;

    public void init(PreferenceData preferences){
        constructWindowAndDimensions(preferences);
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
            case WINDOWED_BORDERLESS -> {
                int width = preferences.getWindowWidthSelected();
                int height = preferences.getWindowHeightSelected();
                preferences.setWindowWidthActual(width);
                preferences.setWindowHeightActual(height);

                setUndecorated(true);

                setPreferredSize(new Dimension(width, height));
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

    public void applyEnvironmentAndCanvas(EnvironmentsModel.EnvironmentType environmentType) {
        environmentsModel.setCurrentEnvironment(environmentType);

        applyEnvironmentAndCanvas();
    }

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
