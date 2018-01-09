package week5.sidm.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.SurfaceView;


import java.util.Random;

public class TrashEntity implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime;
    private int renderLayer;
    private Vibrator _vibrator;


    private SurfaceView view = null;
    private float offset = 0.0f;

    int health = 100;
    boolean recyclable;

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
        if (Math.random() < 0.5f)
            recyclable = true;
        else
            recyclable = false;

        if (recyclable)
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.recyclebag);
        else
            bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.trashbag);

        //lifeTime = 5.0f;

        Random ranGen = new Random();

        xPos = _view.getWidth() + 50.0f;
        yPos = _view.getHeight() - 120.0f;

        xDir = 1.0f;
        yDir = 1.0f;
        //offset = 0.0f;

        //rand between 0 - 1
        //if rand == 1//recyclable = true
        _vibrator = (Vibrator)_view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
    }

    public void StartVibrate()
    {
        if (Build.VERSION.SDK_INT >= 26)
        {
            _vibrator.vibrate(VibrationEffect.createOneShot(500, 10));
        }
        else
        {
            long pattern[] = {0, 50, 0};
            _vibrator.vibrate(pattern, -1);
        }
    }
    public void StopVibrate()
    {
        _vibrator.cancel();
    }

    @Override
    public void Update(float _dt) {
        // lifeTime -= _dt;
        //if(lifeTime <= 0.0f)
        //{
        // SetIsDone(true);
        //}

        Random ranGen = new Random();
        xPos -= xDir * ranGen.nextFloat() * 30.0f - 15.0f * _dt;
        yPos += yDir * _dt;

        if (xPos < -10)
        {
            SetIsDone(true);
        }

       // offset -= _dt * 0.1f;
       // if (offset < 0)
       // {
       //     SetIsDone(true);
       // }
       // if(TouchManager.Instance.isDown())
       // {
            //Check Collision Here!!!!
          //  float imgRadius = bmp.getHeight() * 0.5f;

            //if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                  //  TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius))
           // {
                //SetIsDone(true);
           // }
       // }

    }

    @Override
    public void Render(Canvas _canvas)
    {
       // xPos = 0.5f * view.getWidth();
       // yPos = 0.5f * view.getHeight();

      //  float xoffSet = (float) offset * bmp.getWidth();

       // Matrix transform = new Matrix();
       // transform.postScale(0.75f,0.75f);
       // transform.postTranslate(xPos + xoffSet, yPos);

       // _canvas.drawBitmap(bmp,transform,null);

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

    public static TrashEntity create()
    {
        TrashEntity result = new TrashEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType() {

        if (recyclable)
            return "GoodTrashEntity";
        else
            return "BadTrashEntity";
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
        if(_other.GetType() == "PlayerEntity")
        {
            SetIsDone(true);
            StartVibrate();
        }
    }
}
