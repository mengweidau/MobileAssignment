package week5.sidm.com.week5;


import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;

public class AudioManager {

    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<>();

    public final static AudioManager Instance = new AudioManager();

    private AudioManager() {
    }

    public void Init(SurfaceView _view) {
        view = _view;
        Exit();
    }

    //Adjust your sound from 0.0 to 1.0f;

    public void PlayAudio(int _id, float _volume) {

        if (audioMap.containsKey(_id)) {

            MediaPlayer curr = audioMap.get(_id);

            curr.seekTo(0);

            curr.setVolume(_volume, _volume);

            curr.start();

        } else {

            MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
            audioMap.put(_id, newAudio);
            newAudio.start();

        }
    }

    public void Exit() {
        for (HashMap.Entry<Integer, MediaPlayer> entry : audioMap.entrySet()) {
            entry.getValue().stop();
            entry.getValue().reset();
            entry.getValue().release();
        }
        audioMap.clear();
    }

}
