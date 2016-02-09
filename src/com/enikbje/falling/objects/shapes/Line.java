package com.enikbje.falling.objects.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.enikbje.falling.objects.MovableObject;

public class Line extends MovableObject {
	public double end_x;
	public double end_y;
	private int lineWidth;

	Paint linePaint;

	public Line(double start_x, double start_y, double in_end_x, double in_end_y, int width) {
		super(start_x, start_y);
		this.end_x = in_end_x;
		this.end_y = in_end_y;
		this.lineWidth = width;
		
		linePaint = new Paint();
		linePaint.setStrokeWidth(lineWidth);
	}
	
	public void setColor(int setColor) {
		linePaint.setColor(setColor);
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {

		paint.setStrokeWidth(lineWidth);
		canvas.drawLine((float)x, (float)y, (float)end_x, (float)end_y, paint);
		
	}

}
