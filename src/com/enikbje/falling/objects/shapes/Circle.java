package com.enikbje.falling.objects.shapes;

import com.enikbje.falling.objects.MovableObject;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends MovableObject{
	protected int radius;
	public int mass;
	
	public Circle(double pos_x, double pos_y, int r) {
		super(pos_x, pos_y);
		radius = r;
		mass = r;
	}
	
	public int getRadius() {
		return radius;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawCircle((float)x, (float)y, radius, paint);	
	}
}
