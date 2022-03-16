package graphics.ui;

import graphics.ui.game.GameCanvas;
import models.controls.ControlsModel;
import models.data.PreferenceData;
import models.states.AEnvironment;
import models.states.game.GameModel;
import threads.gameloop.GameRenderRunnable;
import threads.gameloop.GameUpdateRunnable;

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
