package views.window;

import models.environments.EnvironmentsHandler;
import models.prototypes.views.AWindow;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * <p>MainWindow class inherits from the AWindow class. MainWindow contains references to the EnvironmentsHandler.</p>
 * <p>This class is used to build a new Window as a JFrame, and allows the structuring of such a window to the
 * specifications set by the user.</p>
 * <p>It allows for the creation of a custom cursor as well as rebuild the window based on user preferences.</p>
 */
public class MainWindow extends AWindow {

    private EnvironmentsHandler environmentsHandler;

    /**
     * <p></p>
     * @param environmentsHandler -
     */
    public void init(EnvironmentsHandler environmentsHandler){
        this.environmentsHandler = environmentsHandler;

        constructWindowAndDimensions();

        buildCursor(true);
    }

    /**
     * <p></p>
     */
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

    /**
     * <p></p>
     */
    public void build() {
        clearComponents();

        addKeyListener(environmentsHandler.getCurrentEnvironment().getKeyController());
        getContentPane().addMouseListener(environmentsHandler.getCurrentEnvironment().getMouseController());
        getContentPane().addMouseMotionListener(environmentsHandler.getCurrentEnvironment().getMouseController());

        add(environmentsHandler.getCurrentCanvas());
        pack();
    }

}
