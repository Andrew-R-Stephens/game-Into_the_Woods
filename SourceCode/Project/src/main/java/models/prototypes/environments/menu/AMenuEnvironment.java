package models.prototypes.environments.menu;

import controls.MenuControls;
import models.environments.EnvironmentsHandler;
import models.prototypes.environments.AEnvironment;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.Stack;

public abstract class AMenuEnvironment extends AEnvironment implements IDrawable, IUpdatable {

    private final Stack<AMenu> menuStack = new Stack<>();

    public void init(EnvironmentsHandler parentEnvironmentsModel, MenuControls controlsModel) {
        super.init(parentEnvironmentsModel, controlsModel);
    }

    public void initPage(AMenu defaultMenu) {
        push(defaultMenu);
    }

    public void push(AMenu menu){
        if(!menuStack.isEmpty()) {
            menuStack.peek().reset();
        }
        menuStack.push(menu);
        menuStack.peek().reset();
    }

    public AMenu peek() {
        return menuStack.peek();
    }

    public AMenu pop() {
        if(menuStack.size() > 1)
            return menuStack.pop();

        return null;
    }

    protected int getStackDepth() {
        return menuStack.size();
    }

    @Override
    public void draw(Graphics2D g) {
        menuStack.peek().draw(g);
    }

    @Override
    public void update(float delta) {
        peek().update(delta);
    }

    @Override
    public void onExit() {
        super.onExit();
    }

    @Override
    public void reset() {
        menuStack.empty();
    }
}
