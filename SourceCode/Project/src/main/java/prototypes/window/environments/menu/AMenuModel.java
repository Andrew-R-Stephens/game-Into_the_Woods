package prototypes.window.environments.menu;

import models.controls.MenuControlsModel;
import models.environments.EnvironmentsModel;
import prototypes.window.environments.AEnvironment;

import java.awt.*;
import java.util.Stack;

/**
 * The abstract class AMenuModel derives from an AEnvironment for the draw() and update() methods as well as to allow
 * for an easier time with type-referencing passed variables.
 */
public abstract class AMenuModel extends AEnvironment {

    /**
     * The Stack of AMenu objects. The head of the Stack dictates the AMenu which will be drawn.
     */
    private final Stack<AMenu> menuStack = new Stack<>();

    /**
     * Init.
     *
     * @param parentEnvironmentsModel the parent environments model
     * @param controlsModel           the controls model
     */
    public void init(EnvironmentsModel parentEnvironmentsModel, MenuControlsModel controlsModel) {

        super.init(parentEnvironmentsModel, controlsModel.getKeyController(), controlsModel.getMouseController());

    }

    /**
     * Init the Stack with the first AMenu page.
     *
     * @param defaultMenu the default menu
     */
    public void initPage(AMenu defaultMenu) {
        push(defaultMenu);
    }

    /**
     * Push a new AMenu page into the head of the stack.
     *
     * @param menu the menu
     */
    public void push(AMenu menu){
        menuStack.push(menu);
    }

    /**
     * Peek the head AMenu.
     *
     * @return the a menu
     */
    protected AMenu peek() {
        return menuStack.peek();
    }

    /**
     * Pop the head AMenu, which makes the previous Menu visible.
     *
     * @return the a menu
     */
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
