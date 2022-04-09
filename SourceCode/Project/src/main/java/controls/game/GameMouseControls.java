package controls.game;

import models.prototypes.controls.AControlsModel;
import models.prototypes.controls.AMouseController;

import java.awt.event.MouseEvent;

public class GameMouseControls extends AMouseController {

    public GameMouseControls(AControlsModel controlsViewModel) {
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
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = true;
                mPos[0] = e.getX();
                mPos[1] = e.getY();
            }
        }

        System.out.println("Game Pressed!");
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

        System.out.println("Game Released!");

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
