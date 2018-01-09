package week5.sidm.com.week5;

public interface Collidable
{
    String GetType();
    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(Collidable _other);
}
