package week5.sidm.com.week5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;
import android.util.DisplayMetrics;

public class sampleBackground implements EntityBase{
    private Bitmap bmp = null;
    private int renderLayer;

    private boolean isDone =false;

    private SurfaceView view = null;

    private float offset, offset2 = 0.0f;
    private float xPos = 0.0f;
    private float yPos =0.0f;

    @Override
    public boolean IsDone()
    {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone)
    {
        isDone = _isDone;
    }

    @Override
    public void Init (SurfaceView _view)
    {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.backgroundgame);
        view = _view;
        offset = 0.0f;

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        
    }

    @Override
    public void Update(float _dt)
    {
        //xPos -= _dt * 500;
        //if (xPos < - view.getWidth()) {
          //  xPos = 0;
        //}

        offset -= _dt * 0.1f;
        if (offset < -0.5)
        {
            offset = 0;
        }
        offset2 -= _dt * 0.1f;
        if (offset2 < -0.5)
        {
            offset2 = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();

        //float xoffSet = (float) Math.sin(offset) * bmp.getWidth() * 0.3f;
        //float scaleFactor = 0.5f + Math.abs((float)Math.sin())

        //transform.postTranslate(xPos,yPos);
        float xoffSet = (float) offset * bmp.getWidth();
        float xoffSet2 = (float) offset2 * bmp.getWidth();

        Matrix transform = new Matrix();
        transform.postScale(0.75f,0.75f);
        transform.postTranslate(xPos + xoffSet, yPos -700);

        Matrix transform2 = new Matrix();
        transform2.postScale(0.75f,0.75f);
        transform2.postTranslate(xPos + xoffSet2 - 4010, yPos -700);

        _canvas.drawBitmap(bmp,transform,null);
        _canvas.drawBitmap(bmp,transform2,null);
        //_canvas.drawBitmap(bmp, xPos + bmp.getWidth() * 0.005f + xoffSet, yPos - bmp.getHeight() * 0.0005f,null);
        //_canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.005f + xoffSet + 1000, yPos- bmp.getHeight() * 0.0005f , null);
        //_canvas.drawBitmap(bmp,xPos + bmp.getWidth() + xoffSet, bmp.getHeight(), null);
        //_canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f + xoffSet, yPos - bmp.getHeight() * 0.05f, null);
    }

    @Override
    public boolean IsInit() {
        return false;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int layer) {
        return;
    }

    public static sampleBackground Create()
    {
        sampleBackground result = new sampleBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

}

