package controls.menu;

import models.prototypes.controls.AControlsModel;
import models.prototypes.controls.AMouseController;

import java.awt.event.MouseEvent;

public class MenuMouseControls extends AMouseController {

    public MenuMouseControls(AControlsModel controlsViewModel) {
        super(controlsViewModel);
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



}
