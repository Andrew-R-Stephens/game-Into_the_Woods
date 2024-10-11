package models.prototypes.level;

import models.prototypes.level.prop.AProp;
import models.prototypes.level.propChunk.PropChunk;

public record ChunkProp(AProp prop, PropChunk chunk) {
    public ChunkProp(AProp prop, PropChunk chunk) {
        this.prop = prop;
        this.chunk = chunk;
    }
}
