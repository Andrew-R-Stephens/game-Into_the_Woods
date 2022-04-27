package models.prototypes.views;

import models.utils.config.Config;
import models.utils.resources.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>AWindow is the parent class of MainWindow and exists to separate external functionality from the MainWindow.</p>
 * <p>This class extends JFrame, which means that the AWindow inherits the functionality from that class.</p>
 * <p>This class holds the business logic for constructing the window and building the cursor.</p>
 * @author Andrew Stephens
 */
public abstract class AWindow extends JFrame {

    private Resources resources;

    /**
     * <p>Sets the Resources reference for the AWindow</p>
     * @param resources The Resources
     */
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    /**
     * <p>Constructs the Window based on user preferences. Uses the final design to set the actual dimensions to the
     * Config data.</p>
     */
    protected void constructWindowAndDimensions() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        switch (Config.getWindowType()) {
            case FULLSCREEN_EXCLUSIVE -> {
                int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                int height = Toolkit.getDefaultToolkit().getScreenSize().height;
                Config.setWindowWidthActual(width);
                Config.setWindowHeightActual(height);

                setPreferredSize(new Dimension(width, height));
                setUndecorated(true);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            case WINDOWED_FULLSCREEN -> {
                int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                int height = Toolkit.getDefaultToolkit().getScreenSize().height;
                Config.setWindowWidthActual(width);
                Config.setWindowHeightActual(height);

                setPreferredSize(new Dimension(width, height));
            }
            case WINDOWED_BORDERLESS -> {
                int width = Config.getWindowWidthSelected();
                int height = Config.getWindowHeightSelected();
                Config.setWindowWidthActual(width);
                Config.setWindowHeightActual(height);

                setUndecorated(true);

                setPreferredSize(new Dimension(width, height));
            }
            default -> {
                int width = Config.getWindowWidthSelected();
                int height = Config.getWindowHeightSelected();
                Config.setWindowWidthActual(width);
                Config.setWindowHeightActual(height);

                setPreferredSize(new Dimension(width, height));
            }
        }
        setIconImage(new ImageIcon(resources.getImage("icon")).getImage());

        setAlwaysOnTop(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * <p>If the cursor is visible, we build a new cursor to replace the standard system cursor with an image saved
     * within the resources director.</p>
     * @param isVisible If the cursor should be visible.
     */
    public void buildCursor(boolean isVisible) {
        Dimension prefCursorDim = Toolkit.getDefaultToolkit().getBestCursorSize(32, 32);
        Image scaledImage;
        if(isVisible) {
             scaledImage = resources.getImage("cursor2")
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
