package models.prototypes.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * <p>The AMouseController is an abstract class that Mouse controller based class inherit.</p>
 * @author Andrew Stephens
 */
public abstract class AMouseController implements MouseListener, MouseMotionListener {

    protected AControls controlsViewModel;

    protected final int[] mPos = new int[]{-100, -100};

    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;

    /**
     * <p>Creates the AMouseController</p>
     * @param controlsViewModel The parent AControls
     */
    protected AMouseController(AControls controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    /**
     * <p>Checks if the left mouse button is pressed.</p>
     * @return if the left mouse button is pressed.
     */
    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    /**
     * <p>Checks if the right mouse button is pressed.</p>
     * @return if the right mouse button is pressed.
     */
    public boolean isRightPressed() {
        return isRightPressed;
    }

    /**
     * <p>Records the x and y position of the mouse pointer.</p>
     * @param x The horizontal mouse position
     * @param y The vertical mouse position
     */
    public void setPos(int x, int y) {
        mPos[0] = x;
        mPos[1] = y;
    }

    /**
     * <p>Gets the saved position of the mouse</p>
     * @return the recorded mouse position.
     */
    public int[] getPos() {
        return mPos;
    }

    /**
     * <p>Resets the left and right button</p>
     */
    public void reset() {
        isLeftPressed = false;
        isRightPressed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> {
                isLeftPressed = true;
                setPos(e.getX(), e.getY());
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = true;
                setPos(e.getX(), e.getY());
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (e.getButton()){
            case MouseEvent.BUTTON1 -> {
                isLeftPressed = false;
                //setPos(-100, -100);
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = false;
                //setPos(-100, -100);
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) {  }

    @Override
    public void mouseDragged(MouseEvent e) {
        setPos(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setPos(e.getX(), e.getY());
    }

}
