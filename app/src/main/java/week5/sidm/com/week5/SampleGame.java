package week5.sidm.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleGame
{
    public final static SampleGame Instance = new SampleGame();

    float timer = 0.0f;
    private SampleGame()
    {
        //Only here to create the class

    }

    public void Init(SurfaceView _view)
    {
        EntityManager.Instance.Init(_view);
    }

    public void Update(float _deltaTime)
    {
        timer  += _deltaTime;

        if(timer > 1.0f)
        {
            SampleEntity.create();
            timer = 0.0f;
        }
        EntityManager.Instance.Update(_deltaTime);
    }

    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }
}
