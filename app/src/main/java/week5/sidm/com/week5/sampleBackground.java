package week5.sidm.com.week5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.util.DisplayMetrics;

public class SampleBackground implements EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, offset;

    //TODO: background week 9 - part 1
    // Week 9
    //Typeface myfont;

    // New items!! Week 9 for screen resizing
    int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;

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

        bmp = ResourceManager.Instance.GetBitmap(R.drawable.backgroundgame);

        // Retrieve information of your surfaceview or any device size view
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp,ScreenWidth,ScreenHeight, true);

        //TODO: background week 9 - part 2
        // Week 9
        //myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/Gemcut.otf");

    }

    @Override
    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused())
            return;

        offset += _dt * 0.1f;

        // Week 9
        xPos -= _dt * 500;

        if (xPos < - ScreenWidth){
            xPos = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas) {

       /* float xOffset = (float) Math.sin(offset) * bmp.getWidth() * 0.3f;
        //xPos += xOffset;
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f + xOffset, yPos - bmp.getHeight() * 0.5f, null);*

        /*
        Matrix transform = new Matrix();
        transform.postTranslate(-bmp.getWidth()*0.5f, -bmp.getHeight()*0.5f);
        transform.postScale((float)view.getWidth()/(float)bmp.getWidth(), (float)view.getHeight()/(float)bmp.getHeight());
        //transform.postRotate((float)Math.toDegrees(lifeTime));
        transform.postTranslate(xPos, yPos);
        _canvas.drawBitmap(bmp, transform, null);
        */

        // Week 9
        _canvas.drawBitmap(scaledbmp,xPos, yPos, null);
        _canvas.drawBitmap(scaledbmp, xPos + ScreenWidth, yPos, null);


    }

    @Override
    public boolean IsInit() {
        return bmp != null;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.BACKGROUND_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    public static SampleBackground Create()
    {
        SampleBackground result = new SampleBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}

