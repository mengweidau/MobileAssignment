package week5.sidm.com.week5;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.HashMap;


public class StateManager
{
    public static final StateManager Instance = new StateManager();

    private SurfaceView view = null;

    //container to store all states
    private HashMap<String, StateBase> stateMap = new HashMap<String, StateBase>();

    private StateBase currState = null;
    private StateBase nextState = null;

    private StateManager()
    {
    }

    public void Init(SurfaceView _view)
    {
        view = _view;
    }

    void Update(float _dt)
    {
        //process all the state changes
        if (nextState != currState)
        {
            //change state
            currState.OnExit();
            nextState.OnEnter(view);
            currState = nextState;
        }

        //safety catch
        if (currState == null)
            return;

        currState.Update(_dt);
    }

    void Render (Canvas _canvas)
    {
        currState.Render(_canvas);
    }

    void ChangeState(String _nextState)
    {
        //get next state
        nextState = stateMap.get(_nextState);

        //fail case handle: if there is no next state
        if (nextState == null)
            nextState = currState;
    }

    void AddState(StateBase _newState)
    {
        stateMap.put(_newState.GetName(), _newState);
    }

    void Start(String _newCurrent)
    {
        //ensure can only be called once
        if (currState != null || nextState != null)
            return;

        currState = stateMap.get(_newCurrent);
        if (currState != null)
        {
            currState.OnEnter(view);
            nextState = currState;
        }
    }

    String GetCurrentState()
    {
        if (currState == null)
            return "INVALID";

        return currState.GetName();
    }
}
