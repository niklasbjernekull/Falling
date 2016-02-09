package com.enikbje.falling.objects.animals;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.enikbje.falling.R;
import com.enikbje.falling.objects.FallingAnimal;

public class Monkey extends FallingAnimal{
	
	Bitmap monkeyBall;
	
	public Monkey(double pos_x, double pos_y, int r, int f, Resources resources) {
		super(pos_x, pos_y, r, f);
		
        Bitmap temp_bitmap = BitmapFactory.decodeResource(resources, R.drawable.monkeyball);
		monkeyBall = Bitmap.createScaledBitmap(temp_bitmap, radius*2, radius*2, true);;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(monkeyBall, (float)x-radius, (float)y-radius, null);
	}

}
