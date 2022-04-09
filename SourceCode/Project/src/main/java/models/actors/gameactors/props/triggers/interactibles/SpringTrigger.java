package models.actors.gameactors.props.triggers.interactibles;

import models.actors.gameactors.PlayerAvatar;
import models.environments.game.GameEnvironment;
import models.prototypes.actor.AActor;
import models.prototypes.actor.pawn.character.ACharacter;
import models.prototypes.level.prop.trigger.ATrigger;
import models.sprites.SpriteSheet;
import models.utils.drawables.IDrawable;
import models.utils.drawables.IHUDDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.HashMap;

public class SpringTrigger extends ATrigger implements IDrawable, IHUDDrawable, IUpdatable {

    public ActionType actionState = ActionType.IDLE;
    public enum ActionType {
        IDLE
    }

    protected HashMap<ActionType, SpriteSheet> spriteSheets = new HashMap<>();

    public SpringTrigger(GameEnvironment gameEnvironment, float x, float y, float w, float h, float vx, float vy,
                            boolean hasGravity, boolean canMoveOnCollision) {
        super(gameEnvironment, x, y, w, h, vx, vy, -1, hasGravity, canMoveOnCollision);

        spriteSheets.put(actionState, Resources.getSpriteSheet("spring_spritesheet"));
    }

    @Override
    public boolean hasCollision(AActor a, float delta) {
        boolean hasCollision = super.hasCollision(a, delta);

        if(MAX_CYCLES != -1) {
            if (currentCycles > MAX_CYCLES) {
                return false;
            }
        }

        if(hasCollision) {
            lastActor = a;

            doAction();
            currentCycles++;
        }

        return hasCollision;
    }

    @Override
    public void doAction() {
        //isActivated = true;
        lastActor.setVY(-100);
        if(lastActor instanceof PlayerAvatar p) {
            p.actionState = ACharacter.ActionType.FLOOR_JUMPING;
            p.spriteSheets.get(p.actionState).reset();
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        /*
        if(isActivated) {
            spriteSheet.update(delta);
            if(spriteSheet.isLastFrame()) {
                isActivated = false;
                spriteSheet.setCurrentFrame(0);
            }
        }
        */
    }

    @Override
    public void draw(Graphics g) {
        /*
        double offsetX = ((x * ConfigData.scaledW) + (Camera.x));
        double offsetY = ((y * ConfigData.scaledH) + (Camera.y));

        double scaledW = w * ConfigData.scaledW;
        double scaledH = h * ConfigData.scaledH;
        spriteSheet.draw(g, (int)offsetX, (int)offsetY, (int)scaledW, (int)scaledH);
        */
        super.draw(g);
    }
}
