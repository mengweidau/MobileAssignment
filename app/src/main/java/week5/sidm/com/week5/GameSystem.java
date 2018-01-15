package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class GameSystem
{
    public final static GameSystem Instance = new GameSystem();
    private boolean isPaused = false;


    private GameSystem()
    {
    }

    public void Init(SurfaceView _view)
    {
        StateManager.Instance.AddState(new MainGameState());
        StateManager.Instance.AddState(new IntroState());
    }

    public void Update(float _deltaTime)
    {

    }

    public void Render(Canvas _canvas)
    {

    }

    public void SetIsPaused(boolean _isPaused)
    {
        isPaused = _isPaused;
    }
    public boolean GetIsPaused()
    {
        return isPaused;
    }
}

