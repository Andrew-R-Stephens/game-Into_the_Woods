package models.prototypes.window;

import models.utils.config.ConfigData;

import javax.swing.*;
import java.awt.*;

public abstract class AWindow extends JFrame {

    protected void constructWindowAndDimensions(ConfigData preferences) {

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

        ConfigData.scaledW = ConfigData.scaledW / dScalingX;
        ConfigData.scaledH = ConfigData.scaledH / dScalingY;

        System.out.println(ConfigData.scaledW + " " + ConfigData.scaledH);

        setAlwaysOnTop(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
