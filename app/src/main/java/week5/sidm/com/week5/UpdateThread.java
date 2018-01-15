package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class UpdateThread extends Thread {

    static final long targetFPS = 60;

    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    public UpdateThread(GameView _view) {
        view = _view;
        holder = _view.getHolder();

        ResourceManager.Instance.Init(_view);
        AudioManager.Instance.Init(_view);
        StateManager.Instance.Init(_view);
        EntityManager.Instance.Init(_view);
        GameSystem.Instance.Init(_view);
    }

    //if(<New Codes> && isRunning) << ====== this is variable
    public boolean isRunning() {
        //we want to make sure stuff here
        //more codes <New Codes>

        return isRunning;
    }

    public void Initialized() {
        isRunning = true;
    }

    public void Terminate() {
        isRunning = false;
    }

    @Override
    public void run()
    {
        // This is our game loop!!! most Important thing ever!
        // next to sleep. Sleep is Important

        //Init Here for Variable needed ////////////////////////////////////
        long framePerSecond = 1000 / targetFPS; //1000 is milliseconds -> 1 second
        long startTime = 0; // This is for FRC (Frame Rate Controller)

        long prevTime = System.nanoTime();

        //will always start with the state named "Default"
        StateManager.Instance.Start("Default");

        while(isRunning())
        {
            startTime = System.currentTimeMillis();

            // Update////////////////////////////////////
            long currTime = System.nanoTime();
            float deltaTime = (float)((currTime - prevTime)/ 1000000000.0f);
            prevTime = currTime;

            //Update game
            StateManager.Instance.Update(deltaTime);

            //Render////////////////////////////////////
            Canvas canvas = holder.lockCanvas();
            if(canvas != null)
            {
                // Start doing our Stuff Here
                synchronized (holder)
                {
                    //Start doing our stuff here!!!
                    canvas.drawColor(Color.BLACK);

                    StateManager.Instance.Render(canvas);

                }
                holder.unlockCanvasAndPost(canvas);
            }

            //Post Update////////////////////////////////////
            try
            {
                long sleepTime = framePerSecond - (System.currentTimeMillis() - startTime);

                if(sleepTime > 0)
                    sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                AudioManager.Instance.Exit();
                Terminate();
            }
            //end of the loop
        }
    }
}

