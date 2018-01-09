package week5.sidm.com.week5;

import android.view.MotionEvent;

public class TouchManager {
    public final static TouchManager Instance = new TouchManager();

    private TouchManager()
    {}

    public enum TouchState
    {
        NONE,
        DOWN,
        MOVE
    }

    private TouchState Status = TouchState.NONE;
    private int posX, posY;
    public boolean HasTouch()
    {
        return Status == TouchState.DOWN || Status == TouchState.MOVE;
    }
    public boolean isDown()
    {
        // #ifdef Android Version #endif
        return Status == TouchState.DOWN;
        // IOS Version

        // PC Version

        //return true;
    }

    public int GetPosX()
    {
        return posX;
    }

    public int GetPosY()
    {
        return posY;
    }

    public void Update(int _posX, int _posY, int _motionEventStatus)
    {
        posX = _posX;
        posY = _posY;

        switch(_motionEventStatus)
        {
            case MotionEvent.ACTION_DOWN:
                Status = TouchState.DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                Status = TouchState.MOVE;
                break;

            case MotionEvent.ACTION_UP:
                Status = TouchState.NONE;
                break;
        }
    }
}

