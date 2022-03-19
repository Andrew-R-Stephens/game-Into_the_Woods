package props.prototypes.window.environments.menu;

import models.controls.menu.MenuControlsModel;
import props.prototypes.window.environments.AEnvironment;

import java.awt.*;
import java.util.Stack;

public abstract class AMenuModel extends AEnvironment {

    private final Stack<AMenu> menuStack = new Stack<>();

    public void init(MenuControlsModel controlsModel) {

        keyController = controlsModel.getKeyController();
        mouseController = controlsModel.getMouseController();

    }

    public void initPage(AMenu defaultMenu) {
        push(defaultMenu);
    }

    public void push(AMenu menu){
        menuStack.push(menu);
    }

    protected AMenu peek() {
        return menuStack.peek();
    }

    public AMenu pop() {
        return menuStack.pop();
    }

    @Override
    public void draw(Graphics g) {
        menuStack.peek().draw(g);
    }

    @Override
    public void update(float delta) {
        peek().update(delta);
    }
}
