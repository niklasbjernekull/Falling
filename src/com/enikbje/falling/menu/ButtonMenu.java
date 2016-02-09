package com.enikbje.falling.menu;

import java.util.ArrayList;
import java.util.Iterator;

import com.enikbje.falling.menu.buttons.Button;
import com.enikbje.falling.objects.MovableObject;
import com.enikbje.falling.objects.shapes.Circle;
import com.enikbje.falling.objects.shapes.Line;
import com.enikbje.falling.objects.shapes.Rectangle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ButtonMenu extends Rectangle{

	public String MENU = "MENU";
	public String LINE = "LINE";
	public String DRAW = "DRAW";
	public String CIRCLE = "CIRCLE";
	public String ERASE = "ERASE";
	public String NONE = "NONE";
	
	private ArrayList <Button> buttons;
	
	//public String selected = NONE;
	private int selectedIndex;
	private String[] selectedArray = {LINE, DRAW, CIRCLE, ERASE, MENU, NONE};
	
	private Paint buttonPaint = new Paint();
	
	public ButtonMenu(int pos_x, int pos_y, int w, int h, float rot) {
		super(pos_x, pos_y, w, h, rot);
		// TODO Auto-generated constructor stub
		buttons = new ArrayList<Button>();
		int noButtons = 4;
		int x_spacing = w/5, y_spacing = h/(4*noButtons+1);
		
		ArrayList <MovableObject> objectsToSend = new ArrayList<MovableObject>();
		objectsToSend.add(new Line(pos_x+x_spacing+10, pos_y+y_spacing*4-10, pos_x+x_spacing*4-10, pos_y+y_spacing+10, 10));
		buttons.add(new Button(pos_x+x_spacing, pos_y+y_spacing, x_spacing*3, y_spacing*3, rot, objectsToSend));
		
		objectsToSend = new ArrayList<MovableObject>();
		objectsToSend.add(new Line(pos_x+x_spacing+10, pos_y+y_spacing*8-10, pos_x+x_spacing*3, pos_y+y_spacing*7+10, 10));
		objectsToSend.add(new Line(pos_x+x_spacing*3, pos_y+y_spacing*7+10, pos_x+x_spacing*4-10, pos_y+y_spacing*5+10, 10));
		buttons.add(new Button(pos_x+x_spacing, pos_y+y_spacing*5, x_spacing*3, y_spacing*3, rot, objectsToSend));
		
		objectsToSend = new ArrayList<MovableObject>();
		objectsToSend.add(new Circle(pos_x+x_spacing+(x_spacing*3/2), pos_y+y_spacing*9+(y_spacing*3/2), x_spacing));
		buttons.add(new Button(pos_x+x_spacing, pos_y+y_spacing*9, x_spacing*3, y_spacing*3, rot, objectsToSend));
		
		objectsToSend = new ArrayList<MovableObject>();
		objectsToSend.add(new Circle(pos_x+x_spacing+(x_spacing*3/2), pos_y+y_spacing*13+(y_spacing*3/2), x_spacing/2));
		objectsToSend.add(new Line(pos_x+x_spacing+10, pos_y+y_spacing*16-10, pos_x+x_spacing*4-10, pos_y+y_spacing*13+10, 10));
		objectsToSend.add(new Line(pos_x+x_spacing+10, pos_y+y_spacing*13+10, pos_x+x_spacing*4-10, pos_y+y_spacing*16-10, 10));
		buttons.add(new Button(pos_x+x_spacing, pos_y+y_spacing*13, x_spacing*3, y_spacing*3, rot, objectsToSend));
		
		selectedIndex = 0;
		buttons.get(selectedIndex).select();
		
		buttonPaint.setColor(Color.MAGENTA);
	}
	
	public void onClick(float x_in, float y_in) {
		selectedIndex = 4; //MENU is selected
		
		Iterator <Button> biter = buttons.iterator();
		while(biter.hasNext()) {
			Button aButton = biter.next();
			aButton.unSelect();
			if(aButton.isInRectangle(x_in, y_in)) {
				aButton.select();
				selectedIndex = buttons.indexOf(aButton);
			}
		}
	}
	
	public void onHover(float x_in, float y_in) {
		selectedIndex = 4; //MENU is selected
		
		Iterator <Button> biter = buttons.iterator();
		while(biter.hasNext()) {
			Button aButton = biter.next();
			aButton.unSelect();
			if(aButton.isInRectangle(x_in, y_in)) {
				aButton.select();
			}
		}
	}
	
	public void setSelected(String inSelect) {
		for(int i=0;i<selectedArray.length;i++) {
			if(selectedArray[i].equalsIgnoreCase(inSelect)) {
				selectedIndex = i;
			}
		}
	}
	
	public String getSelected() {
		return selectedArray[selectedIndex];
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		
		Iterator <Button> biter = buttons.iterator();
		while(biter.hasNext()) {
			Button aButton = biter.next();
			aButton.draw(canvas, buttonPaint);
		}
	}

}
