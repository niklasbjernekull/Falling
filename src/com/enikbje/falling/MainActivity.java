package com.enikbje.falling;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity  extends Activity {

	/**
	 * Main class
	 * Here is where the app starts
	 * Sets main.xml as layout
	 */
	@Override
    public void onCreate(Bundle savedInstanceState)
   {
       super.onCreate(savedInstanceState);
       
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       
       setContentView(R.layout.main);
   }
	
	/**
	 * Is activated by button in main.xml layout
	 * @param v is variable of View Class
	 */
	public void startFallingActivity (View v) {
        startActivity(new Intent(this, FallingActivity.class));
	}
}
