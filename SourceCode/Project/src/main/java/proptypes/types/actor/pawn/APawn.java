package proptypes.types.actor.pawn;

import data.PreferenceData;
import proptypes.types.actor.AActor;
import utils.IDrawable;

import java.awt.*;

/**
 * TODO: Add description
 */
public abstract class APawn extends AActor implements IDrawable {

    protected APawn(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    protected void update(float delta) {
        super.update(delta);

        x += vX;// / PreferenceData.GAME_UPDATE_RATE / delta;
        y += vY;// / PreferenceData.GAME_UPDATE_RATE / delta;

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}
