package utils.files;

import models.environments.menus.mainmenu.children.StartScreenPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Will contain references to all resources stored. Id's will be granted to all resources, grouped based on directory
 * type and named based on the file name
 */
public class R {



    public R() {

    }

    public void init() {
        BufferedImage img = null;
        InputStream resourceBuff = StartScreenPage.class.getResourceAsStream("/images/testbutton.png");
        try {
            if(resourceBuff != null) {
                img = ImageIO.read(resourceBuff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
