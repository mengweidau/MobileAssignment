package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class EntityManager
{
    public final static EntityManager Instance = new EntityManager();
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();
    private SurfaceView view = null;

    public void UpdatePauseButton(float _dt)
    {
        for (EntityBase currEntity : entityList)
        {
            if (currEntity instanceof SamplePauseButton) {
                currEntity.Update(_dt);
            }
        }
    }

    //Its only for one class
    private EntityManager()
    {

    }

    public void Init (SurfaceView _view)
    {
        view = _view;
    }

    public void Update(float _dt)
    {
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        for (EntityBase currEntity: entityList)
        {
            if (!currEntity.IsInit())
                currEntity.Init(view);

            currEntity.Update(_dt);

            //check if it is done
            if(currEntity.IsDone())
            {
                //completed and go to the removal list
                removalList.add(currEntity);
            }
        }

        //Time to removed!
        for (EntityBase currEntity : removalList)
        {
            entityList.remove(currEntity);
        }
        removalList.clear();
        for(int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            if(currEntity instanceof Collidable)
            {
                Collidable first = (Collidable) currEntity;

                for (int j = i + 1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    if(otherEntity instanceof  Collidable)
                    {
                        Collidable second = (Collidable) otherEntity;

                        if (Collision.SphereToSphere(first.GetPosX(),first.GetPosY(),first.GetRadius(),
                                second.GetPosX(),second.GetPosY(),second.GetRadius()))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }
            }
            if(currEntity.IsDone())
            {
                removalList.add(currEntity);
            }
        }
        for(EntityBase currEntity: removalList)
        {
            entityList.remove(currEntity);
        }
        removalList.clear();
    }

    public void Render(Canvas _canvas)
    {
        Collections.sort(entityList, new Comparator<EntityBase>() {
            @Override
            public int compare(EntityBase o1, EntityBase o2) {

                return o1.GetRenderLayer() - o2.GetRenderLayer();
            }
        });
        //Render all Entities here
        for (EntityBase currEntity: entityList)
        {
            currEntity.Render(_canvas);
        }
    }

    public void AddEntity(EntityBase _newEntity)
    {
        _newEntity.Init(view);
        entityList.add(_newEntity);
    }
}

