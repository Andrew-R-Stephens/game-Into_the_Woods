package models.utils.config;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * <p>DisplayInfo is the configuration representative for the default display. It accepts custom scaling settings
 * done within the system settings of the computer, allowing for scale factor changes within the Config.</p>
 * @author Andrew Stephens
 */
public class DisplayInfo {

    /**<p>The system's extra scale factor of all GUI components.</p>*/
    public static float SYS_SCALEX = 1f, SYS_SCALEY = 1f;

    /**<p>List of all screen dimensions.</p>*/
    private ArrayList<Dimension> windowDimensions = new ArrayList<>();

    /**
     * <p>Uses a temporary JFrame to obtain graphics configurations. Graphics configurations are then used to
     * determine the custom scale of the system.</p>
     */
    public DisplayInfo () {
        JFrame f = new JFrame();
        GraphicsConfiguration gc = f.getGraphicsConfiguration();
        SYS_SCALEX = (float) gc.getDefaultTransform().getScaleX();
        SYS_SCALEY = (float) gc.getDefaultTransform().getScaleY();

        int[] heights = {
                576, 648, 720, 768, 900, 1080, 1440, 2160
        };
        for(int height: heights) {
            if(height > Toolkit.getDefaultToolkit().getScreenSize().getHeight() ||
                height > Toolkit.getDefaultToolkit().getScreenSize().getWidth()) {
                break;
            }
            windowDimensions.add(new Dimension((int)(height*(16f/9f)), height));
        }
    }

    public ArrayList<Dimension> getWindowDimensions() {
        return windowDimensions;
    }
}
