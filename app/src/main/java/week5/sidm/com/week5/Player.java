package week5.sidm.com.week5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class Player implements EntityBase, Collidable
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, yDir;
    private float originYPos;
    boolean isInit = false;

    float maxJumpHeight;

    int health;
    int points;
    boolean isJumping, isFalling;

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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.playerrun);;

        health = 5; //initialise health to 5
        points = 0;
        isJumping = isFalling = false;

        //Random ranGen = new Random();

        //initialise the player position
        xPos = 150.0f;
        originYPos = yPos = _view.getHeight() - 150.0f;

        maxJumpHeight = yPos - 400;
        //xDir = 1.0f;
        yDir = 1.0f; // yDir is positive, meaning he will move up
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused())
            return;

        if(health <= 0) //when player health reaches 0 , he dies OR GAME OVER
        {
            //TODO: go to game over screen
            SetIsDone(true);
        }

        if (isJumping) //is jumping
        {
            yPos -= yDir * _dt * 450; //move up
        }
        if (yPos < maxJumpHeight) //if position reaches max jump height
        {
            isJumping = false;    //stop jump
            isFalling = true;       //is falling = true

        }
        if (isFalling)
        {
            yPos += yDir * _dt * 500;    //move down
        }
        if (yPos >= originYPos && isFalling)
        {
            yPos = originYPos;
            isFalling = false;
        }

        if(TouchManager.Instance.isDown())
        {
            //Check Collision Here!!!!
            float imgRadius = bmp.getHeight() * 0.5f;

            if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                    TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRadius))
            {
                AudioManager.Instance.PlayAudio(R.raw.jumpsfx, 1.0f);
                isJumping = true;
            }
        }

    }

    @Override
    public void Render(Canvas _canvas)
    {
        Paint Lives = new Paint();
        Lives.setColor(Color.WHITE);
        Lives.setStyle(Paint.Style.FILL);

        Lives.setColor(Color.BLACK);
        Lives.setTextSize(60);
        _canvas.drawText("Lives : " + health, 1500,45,Lives);

        Paint Scores = new Paint();
        Lives.setColor(Color.WHITE);
        Lives.setStyle(Paint.Style.FILL);

        Lives.setColor(Color.BLACK);
        Lives.setTextSize(60);
        _canvas.drawText("Scores : " + points, 100,45,Lives);

        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f,
                yPos - bmp.getHeight() * 0.5f, null);
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.GAMEOBJECTS_LAYER + 1;
    }

    @Override
    public void SetRenderLayer(int layer) {
        return;
    }

    public static Player create()
    {
        Player result = new Player();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    public void SetIsJumping(boolean bool) {
        isJumping =  bool;
    }

    public boolean GetIsJumping () {
        return isJumping;
    }

    @Override
    public String GetType() {
        return "PlayerEntity";
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

        if(_other.GetType() == "GoodTrashEntity") //touched recyclable trash, i add score ?
        {
            points += 100;
           ++health;
        }
        else if(_other.GetType() == "BadTrashEntity") //touched recyclable trash, i decrease health
        {
            --health;
        }
    }
}
