package views.swing.window;

import models.environments.EnvironmentsHandler;
import models.prototypes.window.AWindow;
import models.utils.config.ConfigData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainWindow extends AWindow {

    private EnvironmentsHandler environmentsHandler;

    public void init(ConfigData preferences, EnvironmentsHandler environmentsHandler){
        this.environmentsHandler = environmentsHandler;

        constructWindowAndDimensions(preferences);
    }


    public void clearComponents() {
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

    public void build() {
        clearComponents();

        addKeyListener(environmentsHandler.getCurrentEnvironment().getKeyController());
        getContentPane().addMouseListener(environmentsHandler.getCurrentEnvironment().getMouseController());
        getContentPane().addMouseMotionListener(environmentsHandler.getCurrentEnvironment().getMouseController());

        add(environmentsHandler.getCurrentCanvas());
        pack();
    }

}
