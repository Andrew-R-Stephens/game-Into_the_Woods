package graphics.ui;

import files.Preferences;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private GameCanvas gameCanvas;

    public GameWindow(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }

    public void init(Preferences preferences) {

        add(gameCanvas);

        if(preferences.getWindowType() == Preferences.WindowType.FULLSCREEN) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        } else {
            double[] screenDim = {
                    preferences.getWindowWidth(),
                    preferences.getWindowHeight()
            };
            setPreferredSize(new Dimension((int)screenDim[0], (int)screenDim[1]));
        }
        setVisible(true);
        setAlwaysOnTop(true);
        setResizable(false);


        pack();

    }

    public void showWindow() {
        setVisible(true);
    }

    public void hideWindow() {
        setVisible(false);
    }

}
