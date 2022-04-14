package models.prototypes.window;

import models.utils.config.ConfigData;
import models.utils.resources.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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

        ConfigData.scaledW_zoom = ConfigData.scaledW_zoom / dScalingX;
        ConfigData.scaledH_zoom = ConfigData.scaledH_zoom / dScalingY;

        System.out.println(ConfigData.scaledW_zoom + " " + ConfigData.scaledH_zoom);

        setAlwaysOnTop(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void buildCursor(boolean isVisible) {
        Dimension prefCursorDim = Toolkit.getDefaultToolkit().getBestCursorSize(32, 32);
        Image scaledImage = null;
        if(isVisible) {
             scaledImage = Resources.getImage("cursor2")
                    .getScaledInstance(prefCursorDim.width, prefCursorDim.height, Image.SCALE_SMOOTH);
        } else {
            // Transparent 16 x 16 pixel cursor image.
            scaledImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        }

        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(
                scaledImage,
                new Point(0, 0),
                "customCursor");

        super.setCursor(c);
    }

}
