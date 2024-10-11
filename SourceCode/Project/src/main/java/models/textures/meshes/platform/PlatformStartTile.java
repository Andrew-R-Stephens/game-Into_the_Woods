package models.textures.meshes.platform;

import models.utils.resources.Resources;

/**
 * <p>A Platform is a physical barrier object. It is the fundamental piece that allows
 * the other actors to behave as if in a physical world.</p>
 * @author Andrew Stephens
 */
public class PlatformStartTile extends APlatformTile {

    public PlatformStartTile(Resources resources, String name) {
        super(resources, name);
    }

    public PlatformStartTile(Resources resources) {
        this(resources, "platform_level1_left");
    }
}
