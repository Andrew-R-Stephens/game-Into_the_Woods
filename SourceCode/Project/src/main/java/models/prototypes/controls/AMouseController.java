package models.prototypes.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class AMouseController implements MouseListener, MouseMotionListener {

    protected AControls controlsViewModel;

    protected final int[] mPos = new int[]{-100, -100};

    protected boolean isLeftPressed = false;
    protected boolean isRightPressed = false;

    protected AMouseController(AControls controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public void setPos(int x, int y) {
        mPos[0] = x;
        mPos[1] = y;
    }

    public int[] getPos() {
        return mPos;
    }

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
                setPos(-100, -100);
            }
            case MouseEvent.BUTTON2 -> {
                isRightPressed = false;
                setPos(-100, -100);
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
