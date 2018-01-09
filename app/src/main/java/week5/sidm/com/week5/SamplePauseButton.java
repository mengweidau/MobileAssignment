package week5.sidm.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SamplePauseButton implements EntityBase, Collidable
{
private boolean isDone = false;
private Bitmap bmp = null;
private float xPos, yPos, offSet;
private SurfaceView view = null;
private boolean isInit = false;
private int renderLayer = 0;
boolean touched = false;

    @Override
    public String GetType() {
        return "button";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return bmp.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {

    }

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.pausebutton);
        view = _view;
        offSet =0.0f;

    }

    @Override
    public void Update(float dt) {
        if (TouchManager.Instance.isDown()&& !touched)
        {
            touched = true;
        }
        else if (!TouchManager.Instance.isDown() && touched)
        {
            touched = false;
            float imgRadius = bmp.getHeight() * 0.5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius))
            {
                EasyGame.Instance.SetIsPaused(!EasyGame.Instance.GetIsPaused()); //EasyGame = GameSystem
            }
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        float xOffset = (float) Math.sin(offSet) * bmp.getWidth() * 0.3f;
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f + xOffset, yPos - bmp.getHeight() * 0.5f, null);
    }

    @Override
    public boolean IsInit()
    {
        return bmp != null;
    }

    @Override
    public int GetRenderLayer(){return LayerConstants.UI_LAYER;}

    @Override
    public void SetRenderLayer(int _newLayer)
    {
        return;
    }

    public static SamplePauseButton Create()
    {
        SamplePauseButton result = new SamplePauseButton();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
