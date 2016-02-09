package com.enikbje.falling;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.enikbje.falling.graphics.GraphicView;

public class FallingActivity extends Activity {
	private GraphicView graphicView;

    /** Called when the activity is first created.
     * This is the second activity, started by the button press in MainActivity
     * and main.xml.
     * Sets up the graphic view that handles the game (see GraphicView)
     * */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        graphicView = new GraphicView(this, width, height);
        setContentView(graphicView);
    }
}
