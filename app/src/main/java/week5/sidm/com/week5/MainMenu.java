package week5.sidm.com.week5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends Activity implements View.OnClickListener{

    //Define buttons
    private Button btn_start;
    private Button btn_credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mainmenu);

        btn_start = (Button)findViewById(R.id.button);
        btn_start.setOnClickListener(this);
    }

    @Override
    //invoke a callback event in the view
    public void onClick(View v)
    {
        //Intent = action to be performed
        //Intent is an object that provides runtime binding

        Intent intent = new Intent();

        if (v == btn_start)
        {
            //intent -> to set to another class which is another page or screen that we are loading
            intent.setClass(this, GamePage.class);
        }

        startActivity(intent);
    }
}

