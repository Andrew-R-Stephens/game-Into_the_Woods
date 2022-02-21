package graphics.sprite;

import java.awt.image.BufferedImage;

public interface SpriteSheetParser {

    BufferedImage grabImage(int x, int y, int w, int h);

}
