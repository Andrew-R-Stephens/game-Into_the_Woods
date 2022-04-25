package models.prototypes.environments.menu;

import controls.MenuControls;
import models.environments.EnvironmentsHandler;
import models.prototypes.environments.AEnvironment;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.util.Stack;

/**
 * <p>AMenuEnvironment class is an AEnvironment subtype that controls any Menu Environment and its contained
 * entities.</p>
 * <p>Child subtypes include both MainMenuEnvironment and PauseMenuEnvironment.</p>
 */
public abstract class AMenuEnvironment extends AEnvironment implements IDrawable, IUpdatable {

    private final Stack<AMenu> menuStack = new Stack<>();

    /**
     * <p>Initializes the AMenuEnvironment through super calls from the subclass.</p>
     * @param parentEnvironmentsModel The containing EnvironmentsHandler
     * @param controlsModel The ControlsModel that this Environment should be using.
     */
    public void init(EnvironmentsHandler parentEnvironmentsModel, MenuControls controlsModel) {
        super.init(parentEnvironmentsModel, controlsModel);
    }

    /**
     * <p>Initializes the first page with a default AMenu.</p>
     * @param defaultMenu The chosen AMenu subtype to be added. Should not be null.
     */
    public void initPage(AMenu defaultMenu) {
        push(defaultMenu);
    }

    /**
     * <p>Accepts an AMenu subtype and pushes it to the top of a stack of AMenus. The top Menu will always be the
     * one that is active.</p>
     * @param menu The AMenu that should be added to the top of the AMenu stack.
     */
    public void push(AMenu menu){
        if(!menuStack.isEmpty()) {
            menuStack.peek().reset();
        }
        menuStack.push(menu);
        menuStack.peek().reset();
    }

    /**
     * <p>Returns the reference to the top-most Menu in the stack.</p>
     * @return The top-most Menu.
     */
    public AMenu peek() {
        return menuStack.peek();
    }

    /**
     * <p>Removes the top-most Menu in the stack and returns it.</p>
     * @return the top-most menu in the stack. Null if the size of the stack is <= 1.
     */
    public AMenu pop() {
        if(menuStack.size() > 1)
            return menuStack.pop();

        return null;
    }

    /**
     * <p>Returns the size of the stack</p>
     * @return the quantity of menus in the stack.
     */
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
