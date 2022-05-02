package controls.menu;

import models.prototypes.controls.AControls;
import models.prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Dedicated AMenuEnvironment Controls class that extends the AKeyController.
 * @author Andrew Stephens
 */
public class MenuKeyControls extends AKeyController{

    /**
     * <p>Creates the MenuKeyControls and references the parent AControls</p>
     * @param controlsModel - the AControls parent model
     */
    public MenuKeyControls(AControls controlsModel) {
        super(controlsModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(MenuControls.Actions.ESCAPE, true);
            }
            case KeyEvent.VK_ENTER -> {
                controlsModel.setAction(MenuControls.Actions.ENTER, true);
            }
            case KeyEvent.VK_LEFT -> {
                controlsModel.setDirectional(MenuControls.Directionals.LEFT, true);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsModel.setDirectional(MenuControls.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_UP -> {
                controlsModel.setDirectional(MenuControls.Directionals.UP, true);
            }
            case KeyEvent.VK_DOWN -> {
                controlsModel.setDirectional(MenuControls.Directionals.DOWN, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(MenuControls.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_ENTER -> {
                controlsModel.setAction(MenuControls.Actions.ENTER, false);
            }
            case KeyEvent.VK_LEFT -> {
                controlsModel.setDirectional(MenuControls.Directionals.LEFT, false);
            }
            case KeyEvent.VK_RIGHT -> {
                controlsModel.setDirectional(MenuControls.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_UP -> {
                controlsModel.setDirectional(MenuControls.Directionals.UP, false);
            }
            case KeyEvent.VK_DOWN -> {
                controlsModel.setDirectional(MenuControls.Directionals.DOWN, false);
            }
        }
    }

    /**
     * <p>Checks if the action passed is an action.</p>
     * @param type the action requested
     * @return if the action exists
     */
    public boolean isAction(MenuControls.Actions type) {
        return ((MenuControls) controlsModel).isAction(type);
    }

    /**
     * <p>Resets the control Model</p>
     */
    public void reset() {
        controlsModel.reset();
    }

}
