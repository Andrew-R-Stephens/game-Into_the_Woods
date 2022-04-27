package models.utils.config;

import javax.swing.*;
import java.awt.*;

/**
 * <p>DisplayInfo is the configuration representative for the default display. It accepts custom scaling settings
 * done within the system settings of the computer, allowing for scale factor changes within the Config.</p>
 * @author Andrew Stephens
 */
public class DisplayInfo {

    /**<p>The system's extra scale factor of all GUI components.</p>*/
    public static float SYS_SCALEX = 1f, SYS_SCALEY = 1f;

    /**
     * <p>Uses a temporary JFrame to obtain graphics configurations. Graphics configurations are then used to
     * determine the custom scale of the system.</p>
     */
    public DisplayInfo () {
        JFrame f = new JFrame();
        GraphicsConfiguration gc = f.getGraphicsConfiguration();
        SYS_SCALEX = (float) gc.getDefaultTransform().getScaleX();
        SYS_SCALEY = (float) gc.getDefaultTransform().getScaleY();
    }
}
