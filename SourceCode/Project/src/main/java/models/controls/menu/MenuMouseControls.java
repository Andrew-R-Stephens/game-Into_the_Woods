package models.controls.menu;

import props.prototypes.controls.AControlsModel;
import props.prototypes.controls.AMouseController;

import java.awt.event.MouseEvent;

/**
 * Menu Mouse Controls define the controls for Main Manu or Pause Menu navigation with Mouse input.
 */
public class MenuMouseControls extends AMouseController {

    private final MenuControlsModel controlsViewModel;

    private final int[] mPos = new int[]{-100, -100};

    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    public MenuMouseControls(AControlsModel controlsViewModel) {
        this.controlsViewModel = (MenuControlsModel) controlsViewModel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> {
                isLeftPressed = true;
                mPos[0] = e.getX();
                mPos[1] = e.getY();
                //controlsViewModel.isAwaitingRelease = true;
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = true;
                mPos[0] = e.getX();
                mPos[1] = e.getY();
                //controlsViewModel.isAwaitingRelease = true;
            }
        }

        System.out.println("Menu Pressed!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> {
                isLeftPressed = false;
                mPos[0] = -100;
                mPos[1] = -100;
                //controlsViewModel.isAwaitingRelease = false;
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = false;
                mPos[0] = -100;
                mPos[1] = -100;
                //controlsViewModel.isAwaitingRelease = false;
            }
        }

        System.out.println("Menu Released!");

    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {  }

    @Override
    public void mouseDragged(MouseEvent e) {
        mPos[0] = e.getX();
        mPos[1] = e.getY();
        resetInput();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mPos[0] = e.getX();
        mPos[1] = e.getY();
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed && !controlsViewModel.isAwaitingRelease;
    }

    public int[] getPos() {
        return mPos;
    }

    public void resetInput() {
        isLeftPressed = false;
        isRightPressed = false;
        controlsViewModel.isAwaitingRelease = false;
    }

}
