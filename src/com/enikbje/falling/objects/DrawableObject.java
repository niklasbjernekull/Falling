package com.enikbje.falling.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface DrawableObject {

	public double getX();
	
	public double getY();
	
	public void setX(double x);
	
	public void setY(double y);
	
	public void draw(Canvas canvas, Paint paint);

}
