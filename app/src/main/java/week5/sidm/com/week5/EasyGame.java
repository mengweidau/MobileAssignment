package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class EasyGame
{
    public final static EasyGame Instance = new EasyGame(); //singleton of EasyGame

    private boolean isPaused = false;

    public void SetIsPaused(boolean _isPaused)
    {
        isPaused = _isPaused;
    }
    public boolean GetIsPaused()
    {
        return isPaused;
    }

    private EasyGame()
    {
        //Only here to create the class
    }
    float timer = 0.0f;


    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
        EntityManager.Instance.AddEntity(new sampleBackground()); //create 1 background
        Player.create();
        AudioManager.Instance.PlayAudio(R.raw.bgm1, 0.2f);
    }

    public void Update(float _deltaTime)
    {
        EntityManager.Instance.UpdatePauseButton(_deltaTime);
        timer  += _deltaTime;

        if(timer > 1.0f)
        {
            TrashEntity.create();
            timer = 0.0f;
        }
        EntityManager.Instance.Update(_deltaTime);
    }

    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }
}
