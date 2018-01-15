package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

import week5.sidm.com.week5.EntityBase;

/**
 * Created by Howard_tansiewlan on 1/3/2018.
 */

public class RenderTextEntity implements EntityBase
{

    private boolean isDone = false;

    int frameCount;
    long lastTime = 0;
    long lastFPSTime = 0;
    float fps;

    Typeface myfont;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {

        // Week 8 Use my own fonts
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/Gemcut.otf");

    }

    @Override
    public void Update(float _dt) {

        // get actual fps

        frameCount++;

        long currentTime = System.currentTimeMillis();

        lastTime = currentTime;

        if(currentTime - lastFPSTime > 1000)
        {
            fps = (frameCount * 1000.f) / (currentTime - lastFPSTime);
            lastFPSTime = currentTime;
            frameCount = 0;
        }


    }

    @Override
    public void Render(Canvas _canvas)
    {

        Paint paint = new Paint();
        paint.setARGB(255, 0,0,0);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(70);
        _canvas.drawText("FPS: " + fps, 30, 80, paint);

    }

    @Override
    public boolean IsInit() {
        return true;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    public static RenderTextEntity Create()
    {
        RenderTextEntity result = new RenderTextEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }


}
