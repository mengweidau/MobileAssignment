package week5.sidm.com.week5;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView
{
    //Context context = null;

    //Will wrtie this later
    private SurfaceHolder holder = null;
    private UpdateThread updateThread = new UpdateThread(this);


    public GameView(Context _context)
    {
        super(_context);
        holder = getHolder();

        if (holder != null)
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                    if (!updateThread.isRunning())
                        updateThread.Initialized();

                    if (!updateThread.isAlive())
                        updateThread.start();


                }



                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    //nothing to do here : take control from file
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    updateThread.Terminate();
                }
            });
    }


}

