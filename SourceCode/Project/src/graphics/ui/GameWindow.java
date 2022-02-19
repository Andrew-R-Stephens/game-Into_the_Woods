package graphics.ui;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private GameCanvas gameCanvas;

    public GameWindow(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;

        init();
    }

    public void init() {

        add(gameCanvas);

        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);

        double[] screenDim =
                {Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height};

        setPreferredSize(new Dimension((int)screenDim[0], (int)screenDim[1]));

        pack();

    }

    public void showWindow() {
        setVisible(true);
    }

    public void hideWindow() {
        setVisible(false);
    }

}
