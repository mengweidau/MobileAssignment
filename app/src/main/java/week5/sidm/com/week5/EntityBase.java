package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

//Every Entity must have this class/function
public interface EntityBase
{
    boolean IsDone();
    void SetIsDone(boolean _isDone);

    void Init(SurfaceView _view);
    void Update(float dt);
    void Render(Canvas _canvas);

    boolean IsInit();
    int GetRenderLayer();
    void SetRenderLayer(int layer);
}
