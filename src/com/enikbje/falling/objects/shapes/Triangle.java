package com.enikbje.falling.objects.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.enikbje.falling.objects.MovableObject;

public class Triangle extends MovableObject {

	private double corner1;
	private double corner2;
	private double corner3;
	private float rotation;
	
	public Triangle (int pos_x, int pos_y, int c1, int c2, int c3, float rot) {
		super(pos_x, pos_y);
		corner1 = c1;
		corner2 = c2;
		corner3 = c3;
		rotation = rot;
	}
	
	public double[] getCorners() {
		return new double[]{corner1, corner2, corner3};
	}
	
	public float getRotation() {
		return rotation;
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawLines(new float[] {(float)corner1, (float)corner2, (float)corner3,(float)corner1}, paint);
	}
}
