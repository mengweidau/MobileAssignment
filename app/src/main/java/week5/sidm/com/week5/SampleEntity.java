package week5.sidm.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class SampleEntity implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime;

    int health = 100;

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
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.mipmap.ic_launcher_round);

        lifeTime = 5.0f;

        Random ranGen = new Random();

        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = ranGen.nextFloat() * _view.getHeight();

        xDir = ranGen.nextFloat() *100.0f - 50.0f;
        yDir = ranGen.nextFloat() *100.0f - 50.0f;

    }

    @Override
    public void Update(float _dt) {
       // lifeTime -= _dt;
        //if(lifeTime <= 0.0f)
        //{
           // SetIsDone(true);
        //
        //}

        xPos += xDir * _dt;
        yPos += yDir * _dt;

        if(TouchManager.Instance.isDown())
        {
            //Check Collision Here!!!!
            float imgRadius = bmp.getHeight() * 0.5f;

            if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                    TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius))
            {
                SetIsDone(true);
            }
        }

    }

    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f,
                yPos - bmp.getHeight() * 0.5f, null);
    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.GAMEOBJECTS_LAYER;
    }

    @Override
    public void SetRenderLayer(int layer) {
        return;
    }

    public static SampleEntity create()
    {
        SampleEntity result = new SampleEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType() {
        return "SampleEntity";
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
        if(_other.GetType() == "SampleEntity")
        {
            SetIsDone(true);
        }
    }
}

