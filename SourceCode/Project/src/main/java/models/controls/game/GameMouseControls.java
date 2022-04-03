package models.controls.game;

import prototypes.controls.AControlsModel;
import prototypes.controls.AMouseController;

import java.awt.event.MouseEvent;

/**
 * Game Mouse Controls define the controls for in-game actions with Mouse input.
 */
public class GameMouseControls extends AMouseController {

    /**
     * Instantiates a new Game mouse controls.
     *
     * @param controlsViewModel the controls view model
     */
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

    /**
     * Is left pressed boolean.
     *
     * @return the boolean
     */
    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    /**
     * Is right pressed boolean.
     *
     * @return the boolean
     */
    public boolean isRightPressed() {
        return isRightPressed;
    }

    /**
     * Get pos int [ ].
     *
     * @return the int [ ]
     */
    public int[] getPos() {
        return mPos;
    }

}
