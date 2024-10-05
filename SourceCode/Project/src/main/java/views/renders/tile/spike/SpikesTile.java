package views.renders.tile.spike;

import models.utils.resources.Resources;

/**
 * <p>A Platform is a physical barrier object. It is the fundamental piece that allows
 * the other actors to behave as if in a physical world.</p>
 * @author Andrew Stephens
 */
public class SpikesTile extends ASpikesTile {

    public SpikesTile(Resources resources, String name) {
        super(resources, name);
    }

    public SpikesTile(Resources resources) {
        this(resources, "spikes");
    }
}