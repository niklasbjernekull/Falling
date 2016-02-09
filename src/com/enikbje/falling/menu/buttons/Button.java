package com.enikbje.falling.menu.buttons;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.enikbje.falling.objects.MovableObject;
import com.enikbje.falling.objects.shapes.Rectangle;

public class Button extends Rectangle{

	public boolean is_selected = false;
	private ArrayList <MovableObject> objectTodraw;
	
	private Paint objectPaint = new Paint();
	
	public Button(int pos_x, int pos_y, int w, int h, float rot) {
		super(pos_x, pos_y, w, h, rot);
		// TODO Auto-generated constructor stub
	}
	
	public Button(int pos_x, int pos_y, int w, int h, float rot, ArrayList <MovableObject> incomingObjects) {
		super(pos_x, pos_y, w, h, rot);
		objectTodraw = incomingObjects;
		objectPaint.setColor(Color.BLUE);
	}

	public void select() {
		is_selected = true;
	}
	
	public void unSelect() {
		is_selected = false;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		
		if(is_selected) {
			Paint thisPaint = new Paint();
			thisPaint.setColor(Color.GREEN);
			super.draw(canvas, thisPaint);
		} else {
			super.draw(canvas, paint);
		}

		Iterator <MovableObject> miter = objectTodraw.iterator();
		while(miter.hasNext()) {
			MovableObject anObject = miter.next();
			anObject.draw(canvas, objectPaint);
		}
	}

}
