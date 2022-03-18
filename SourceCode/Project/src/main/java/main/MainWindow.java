package main;

import graphics.ui.game.GameCanvas;
import graphics.ui.menu.MenuCanvas;
import models.controls.ControlsModel;
import models.data.PreferenceData;
import models.environments.menus.MenusModel;
import props.prototypes.window.environments.AEnvironment;
import models.environments.game.GameModel;
import props.prototypes.window.ACanvas;
import props.prototypes.window.AWindow;
import props.threads.gameloop.GameRenderRunnable;
import props.threads.gameloop.GameUpdateRunnable;
import props.threads.menuloop.MenuRenderRunnable;
import props.threads.menuloop.MenuUpdateRunnable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The Application Window contains the Canvas which is currently active.
 */
public class MainWindow extends AWindow {

    private final ArrayList<AEnvironment> environments = new ArrayList<>();
    private final ArrayList<ACanvas> canvases = new ArrayList<>();

    private ControlsModel controlsModel;

    private int currentState = 0;

    public void init(PreferenceData preferences, ControlsModel controlsModel){
        constructWindowAndDimensions(preferences);

        setControlsModel(controlsModel);
    }

    private void setControlsModel(ControlsModel controlsModel) {
        this.controlsModel = controlsModel;
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

    private void initThreads() {
        Thread updates = null;
        Thread renders = null;
        if(environments.get(currentState) instanceof GameModel m) {
            updates = new Thread(new GameUpdateRunnable(m));

            if(canvases.get(currentState) instanceof GameCanvas c) {
                renders = new Thread(new GameRenderRunnable(c));
            }
        } else
        if(environments.get(currentState) instanceof MenusModel m) {
            updates = new Thread(new MenuUpdateRunnable(m));

            if(canvases.get(currentState) instanceof MenuCanvas c) {
                renders = new Thread(new MenuRenderRunnable(c));
            }
        }

        if(updates != null && renders != null) {
            updates.start();
            renders.start();
            /*
            try {
                updates.join();
                renders.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation);
    }

    public void addEnvironmentWithCanvas(AEnvironment model, ACanvas canvas) {
        environments.add(model);
        canvases.add(canvas);
    }

    //TODO: Set enums for environment types
    public void initEnvironmentAndCanvas(AEnvironment type) {

    }

    public void initEnvironmentAndCanvas(int currentState) {
        this.currentState = currentState;

        //this.removeAll();

        addKeyListener(controlsModel.getKeyController());
        addMouseListener(controlsModel.getMouseController());
        addMouseMotionListener(controlsModel.getMouseController());
        add(canvases.get(currentState));

        System.out.println(currentState);

        pack();

        initThreads();
    }

}
