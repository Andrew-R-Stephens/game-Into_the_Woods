package models.prototypes.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * <p></p>
 */
public abstract class AMouseController implements MouseListener, MouseMotionListener {

    protected AControls controlsViewModel;

    protected final int[] mPos = new int[]{-100, -100};

    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;

    /**
     * <p></p>
     * @param controlsViewModel -
     */
    protected AMouseController(AControls controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    /**
     * <p></p>
     * @return
     */
    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    /**
     * <p></p>
     * @return
     */
    public boolean isRightPressed() {
        return isRightPressed;
    }

    /**
     * <p></p>
     * @param x -
     * @param y -
     */
    public void setPos(int x, int y) {
        mPos[0] = x;
        mPos[1] = y;
    }

    /**
     * <p></p>
     * @return
     */
    public int[] getPos() {
        return mPos;
    }

    /**
     * <p></p>
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
