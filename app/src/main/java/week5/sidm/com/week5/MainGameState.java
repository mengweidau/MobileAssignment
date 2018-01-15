package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class MainGameState implements StateBase
{
    float timer = 0.0f;

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

        SampleBackground.Create();
        Player.create();
        SamplePauseButton.Create();
        AudioManager.Instance.PlayAudio(R.raw.bgm1, 0.2f);
    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {
        EntityManager.Instance.Render(_canvas);
    }

    @Override
    public void Update(float _dt) {
        EntityManager.Instance.UpdatePauseButton(_dt);
        if (GameSystem.Instance.GetIsPaused())
            return;
        timer  += _dt;

        if(timer > 1.0f)
        {
            TrashEntity.create();
            timer = 0.0f;
        }
        EntityManager.Instance.Update(_dt);
    }
}
