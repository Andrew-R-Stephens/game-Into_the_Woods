package controls.menu;

import utils.AMouseController;
import viewmodels.controls.ControlsModel;

import java.awt.event.MouseEvent;

/**
 * Menu Mouse Controls define the controls for Main Manu or Pause Menu navigation with Mouse input.
 */
public class MenuMouseControls extends AMouseController {

    private final ControlsModel controlsViewModel;

    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private final int[] mPos = new int[]{-100, -100};

    public MenuMouseControls(ControlsModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
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
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = true;
                mPos[0] = e.getX();
                mPos[1] = e.getY();
            }
        }

        System.out.println("Pressed!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> {
                isLeftPressed = false;
                mPos[0] = -100;
                mPos[1] = -100;
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = false;
                mPos[0] = -100;
                mPos[1] = -100;
            }
        }

        System.out.println("Released!");

    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {  }

    @Override
    public void mouseDragged(MouseEvent e) {
        mPos[0] = e.getX();
        mPos[1] = e.getY();
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
        return isRightPressed;
    }

    public int[] getPos() {
        return mPos;
    }

}
