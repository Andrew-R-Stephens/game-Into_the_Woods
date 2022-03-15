package models.states;

import utils.updates.IUpdatable;

public abstract class AEnvironment implements IUpdatable {

    protected enum Type {MENU, GAME}

    @Override
    public void update(float delta) {

    }

}
