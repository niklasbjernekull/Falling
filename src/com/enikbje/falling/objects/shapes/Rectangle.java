package com.enikbje.falling.objects.shapes;

import com.enikbje.falling.objects.MovableObject;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends MovableObject {

	protected int width;
	protected int height;
	private float rotation;
	
	public Rectangle (int pos_x, int pos_y, int w, int h, float rot) {
		super(pos_x, pos_y);
		width = w;
		height = h;
		rotation = rot;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public boolean isInRectangle(float x_in, float y_in) {
		if((x_in >= x && x_in <= x+width) && (y_in >= y && y_in <= y + height)) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawRect((float)x, (float)y, (float)x+width, (float)y+height, paint);
	}
}
