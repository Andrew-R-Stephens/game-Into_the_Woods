package controls.menu;

import controls.MenuControls;
import models.prototypes.controls.AControlsModel;
import models.prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

public class MenuKeyControls extends AKeyController{

    public MenuKeyControls(AControlsModel controlsModel) {
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
            }case KeyEvent.VK_ENTER -> {
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

    public boolean isAction(MenuControls.Actions type) {
        return ((MenuControls) controlsModel).isAction(type);
    }

    public void resetInput() {
        ((MenuControls) controlsModel).resetInput();
    }

}
