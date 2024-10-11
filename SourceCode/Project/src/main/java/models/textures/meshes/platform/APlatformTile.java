package models.textures.meshes.platform;

import models.utils.resources.Resources;
import models.textures.meshes.ATile;

import java.awt.image.BufferedImage;

public class APlatformTile extends ATile {

    public APlatformTile(Resources resources, String image) {
        super(resources, image);
    }

    public APlatformTile(BufferedImage[] images) {
        super(images, new boolean[]{false, false, false, false});
    }

    public APlatformTile(BufferedImage[] images, boolean[] corners) {
        super(images, corners);
    }

    public APlatformTile() {
        super();
    }
}
