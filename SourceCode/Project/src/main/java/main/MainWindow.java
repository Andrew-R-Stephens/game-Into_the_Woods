package main;

import graphics.ui.game.GameCanvas;
import graphics.ui.menu.MenuCanvas;
import models.data.PreferenceData;
import models.environments.game.GameModel;
import models.environments.menus.mainmenu.MainMenuModel;
import props.prototypes.window.AWindow;
import props.threads.gameloop.GameRenderRunnable;
import props.threads.gameloop.GameUpdateRunnable;
import props.threads.menuloop.MenuRenderRunnable;
import props.threads.menuloop.MenuUpdateRunnable;

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

    public void initEnvironmentAndCanvas(EnvironmentsModel.EnvironmentType environmentType) {
        environmentsModel.setCurrentEnvironment(environmentType);

        addKeyListener(environmentsModel.getCurrentEnvironment().getKeyController());
        getContentPane().addMouseListener(environmentsModel.getCurrentEnvironment().getMouseController());
        getContentPane().addMouseMotionListener(environmentsModel.getCurrentEnvironment().getMouseController());

        add(environmentsModel.getCurrentCanvas());
        pack();

        initThreads();
    }

}
