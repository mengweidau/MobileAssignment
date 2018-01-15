package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by Rook on 1/16/2018.
 */

public interface StateBase
{
    String GetName();

    void OnEnter(SurfaceView _view);
    void OnExit();
    void Render(Canvas _canvas);
    void Update(float _dt);
}
