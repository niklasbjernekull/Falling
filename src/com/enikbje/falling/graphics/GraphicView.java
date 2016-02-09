package com.enikbje.falling.graphics;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import com.enikbje.falling.collision.Collisions;
import com.enikbje.falling.menu.ButtonMenu;
import com.enikbje.falling.objects.ConnectedCircles;
import com.enikbje.falling.objects.FallingAnimal;
import com.enikbje.falling.objects.MovableObject;
import com.enikbje.falling.objects.animals.Bird;
import com.enikbje.falling.objects.animals.Monkey;
import com.enikbje.falling.objects.shapes.Circle;
import com.enikbje.falling.objects.shapes.Line;
import com.enikbje.falling.objects.shapes.Rectangle;

/**
 * Graphic View that is started by FallingActivity.
 * */
public class GraphicView extends View implements SensorEventListener{
	private static int height;
	private static int width;
	private ArrayList <FallingAnimal> animals;
	private ArrayList <MovableObject> circles;
	private ArrayList <Line> lines;
	private ButtonMenu menu;
	private Collisions collisions;
	
	float downx = 0, downy = 0; //touch coordinates
	String downMenu = "";
	
	//private ConnectedCircles connected;
	
	Paint movingPaint = new Paint();
	Paint stationaryPaint = new Paint();
	//private Circle aCircle;
	
	/**
	 * This constructor sets up the objects used on the screen:
	 * Falling animals and stationary objects (MovableObject).
	 * @param context
	 * @param w = width
	 * @param h = height
	 */
	public GraphicView(Context context, int w, int h) {
        super(context);
        
        height = h;
        width = w;
        
        collisions = new Collisions(height, width); 
        
        animals = new  ArrayList<FallingAnimal>();
        /*FallingAnimal theAnimal = new Monkey(width/2, 0, 100, 1, getResources());
        animals.add(theAnimal);
        theAnimal = new Bird(width/3, 100, 70, 1, getResources());
        animals.add(theAnimal);
        theAnimal = new Monkey((width*2)/3, 200, 50, 1, getResources());
        animals.add(theAnimal);*/
        FallingAnimal theAnimal = new Monkey(width/6, 0, 50, 2, getResources());
        animals.add(theAnimal);
        theAnimal = new Bird(width/3, 100, 70, 1, getResources());
        animals.add(theAnimal);
        theAnimal = new Monkey(width/2, 200, 50, 2, getResources());
        animals.add(theAnimal);
        theAnimal = new Monkey(width*2/3, 0, 50, 2, getResources());
        animals.add(theAnimal);
        theAnimal = new Bird(width*5/6, 100, 70, 1, getResources());
        animals.add(theAnimal);
        
        circles = new ArrayList<MovableObject>();
        /*Circle aCircle = new Circle(width/2+50, 600, 50);
        circles.add(aCircle);
        aCircle = new Circle(width/3-45, 450, 32);
        circles.add(aCircle);
        aCircle = new Circle(width/2, 1500, 75);
        circles.add(aCircle);
        aCircle = new Circle(width/2-300, 1250, 150);
        circles.add(aCircle);
        aCircle = new Circle(width/2+300, 1750, 150);
        circles.add(aCircle);*/
        
        lines = new ArrayList<Line>();
        //lines.add(new Line(0,250,width/2,900,10));
        /*lines.add(new Line(width,600,width/2,1100,10));
        lines.add(new Line(width/3,1500,width*2/3,2000,10));
        lines.add(new Line(100,height-500,500,height-600,10));
        lines.add(new Line(0,height-200,width,height-200,10));*/
        
        //connected = new ConnectedCircles(20, 20, 10, 10, 5);
        
        menu = new ButtonMenu(width-250, 0, 250, 850, 0);
        
        //Set paint
        movingPaint.setColor(Color.RED);
        stationaryPaint.setColor(Color.BLACK);
        
        
	}
	
	/**
	 * Default constructor
	 * (Just implemented to shut up eclipse)
	 * @param context
	 */
	public GraphicView(Context context) {
		super(context);
		height=100;
		width=100;
	}
	
	/**
	 * Draws everything on the screen usin the canvas.
	 * Also calculates the collision between objects using Collisions.
	 * Is invalidated to start redraw.
	 */
	@Override
    protected void onDraw(Canvas canvas) {
		//Draw background
		canvas.drawRGB(120, 120, 255);
		
		//Paint animals
		Iterator <FallingAnimal> iter = animals.iterator();
		while(iter.hasNext()) {
			FallingAnimal anAnimal = iter.next();
			anAnimal.draw(canvas, movingPaint);
		}
		
		//connected.draw(canvas, movingPaint);
		
		//Paint stationary objects
		Iterator <MovableObject> citer = circles.iterator();
		while(citer.hasNext()) {
			MovableObject aCircle = citer.next();
			aCircle.draw(canvas, stationaryPaint);
		}	
		
		//Paint lines
		Iterator <Line> liter = lines.iterator();
			while(liter.hasNext()) {
				MovableObject aLine = liter.next();
				aLine.draw(canvas, movingPaint);
		}
		
		menu.draw(canvas, stationaryPaint);
		
		//ArrayList <MovableObject> tempArray = animals;
		
		collisions.calculateCollisionsFallAndWall(animals);
		//collisions.calculateCollisionsCircles(animals, tempArray);
		collisions.calculateCollisionsCircles(animals, circles);
		collisions.calculateCollisionsLines(animals, lines);
		/*collisions.calculateCollisions(animals, connected.getConnected());
		collisions.calculateCollisionsWithoutSelfCollision(connected.getConnected(), circles);*/
		//connected.calculations();
		
		printDebugText(true, false, true, true, canvas);
		
		invalidate();
	}
	
	/**
	 * For printing data on screen
	 * @param canvas
	 */
	private void printDebugText(boolean printAnimals, boolean printLines, boolean printMenu, boolean printSpeed, Canvas canvas) {

		Paint text_paint = new Paint();
		text_paint.setColor(Color.RED);
		int text_y = 40, text_size=40, i=1;
		text_paint.setTextSize(text_size);
		String debug_text;
		
		Iterator <FallingAnimal> iter = animals.iterator();
		if(printAnimals) {
			while(iter.hasNext()) {
				FallingAnimal anAnimal = iter.next();
				debug_text = Integer.toString(i) + " : [" + Double.toString(anAnimal.x) + ", " + Double.toString(anAnimal.y) + "]";
				canvas.drawText(debug_text, 10, text_y, text_paint);
				i++; text_y+=text_size*1.5;
			}
		}
		
		if(printLines) {
			i=1;
			Iterator <Line> line_iter = lines.iterator();
			while(line_iter.hasNext()) {
				Line aLine = line_iter.next();
				debug_text = "L" + Integer.toString(i) + " : [" + Double.toString(aLine.x) + ", " + Double.toString(aLine.y) + "]" + "[" + Double.toString(aLine.end_x) + ", " + Double.toString(aLine.end_y) + "]";
				canvas.drawText(debug_text, 10, text_y, text_paint);
				i++; text_y+=text_size*1.5;
			}
		}
		
		if(printMenu) {
			debug_text = menu.getSelected();
			canvas.drawText(debug_text, width-250, 850, text_paint);
		}
		
		if(printSpeed) {
			Paint speed_paint = new Paint();
			speed_paint.setColor(Color.RED);
			speed_paint.setStrokeWidth(2);
			iter = animals.iterator();
			while(iter.hasNext()) {
				FallingAnimal anAnimal = iter.next();
				canvas.drawLine((float)anAnimal.x, (float)anAnimal.y, (float)(anAnimal.getSpeedX()+anAnimal.x), (float)(anAnimal.getSpeedY()+anAnimal.y), speed_paint);
			}
		}
		
	}

	/**
	 * Class needed to be implemented
	 */
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Class needed to be implemented
	 */
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
	
	}
	
	/**
	 * Handles touch events on the screen
	 */
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        
        
        //event.getDownTime();
		float upx, upy;
        int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
        	downx = event.getX();
  		  	downy = event.getY();
  		  	if(menu.isInRectangle(downx, downy)) {
  		  		menu.setSelected(menu.MENU);
  		  		menu.onHover(downx, downy);
  		  		
  		  	}else if(menu.getSelected().matches(menu.LINE)){
  		  		Line aLine = new Line(downx,downy,downx,downy,10);
  		  		lines.add(aLine);
  		  	} else if(menu.getSelected().matches(menu.DRAW)){
  		  		Line aLine = new Line(downx,downy,downx,downy,10);
		  		lines.add(aLine);
  		  	} else if(menu.getSelected().matches(menu.CIRCLE)){
  		  		Circle aCircle = new Circle(downx, downy, 1);
  		  		circles.add(aCircle);
  		  	}
  		   
  		  	break;
        case MotionEvent.ACTION_MOVE:
        	upx = event.getX();
	        upy = event.getY();
	        
	        if(menu.getSelected().matches(menu.MENU)){
  		  		menu.onHover(upx, upy);
  		  	} else if(menu.getSelected().matches(menu.LINE)){
  		  		Line aLine = new Line(downx,downy,upx,upy,10);
	            lines.set(lines.size()-1, aLine);
	        } else if(menu.getSelected().matches(menu.DRAW)){
	        	double radius = Math.sqrt((downx-upx)*(downx-upx)+(downy-upy)*(downy-upy));
	        	Line aLine = new Line(downx,downy,upx,upy,10);
	        	if( radius > 50) {
	        		lines.set(lines.size()-1, aLine);
	        		downx = upx;
	        		downy = upy;
	        		aLine = new Line(downx,downy,upx,upy,10);
	        		lines.add(aLine);
	        	} else {
	        		lines.set(lines.size()-1, aLine);
	        	}
  		  	} else if(menu.getSelected().matches(menu.CIRCLE)){
  		  		double radius = Math.sqrt((downx-upx)*(downx-upx)+(downy-upy)*(downy-upy));
  		  		Circle aCircle = new Circle(downx, downy, (int) radius);
		  		circles.set(circles.size()-1, aCircle);
  		  	}
            
	        break;
        case MotionEvent.ACTION_UP:
        	upx = event.getX();
        	upy = event.getY();
          
        	if(menu.getSelected().matches(menu.MENU)){
    			  menu.onClick(upx, upy);
    			  if(menu.getSelected().matches(menu.ERASE)){
    				  lines  = new ArrayList<Line>();
    				  circles = new ArrayList<MovableObject>();
    			  }
  		  	} else if(menu.getSelected().matches(menu.LINE)){
        		if(upx == downx && upy == downy) {
        			lines.remove(lines.size()-1);
        			break;
            	}
        		Line aLine = new Line(downx,downy,upx,upy,10);
            	lines.set(lines.size()-1, aLine);
        	} else if(menu.getSelected().matches(menu.DRAW)){
        		if(upx == downx && upy == downy) {
        			lines.remove(lines.size()-1);
        			break;
            	}
        		Line aLine = new Line(downx,downy,upx,upy,10);
            	lines.set(lines.size()-1, aLine);
        	} else if(menu.getSelected().matches(menu.CIRCLE)){
        		if(upx == downx && upy == downy) {
        			circles.remove(circles.size()-1);
        			break;
        		}
        		double radius = Math.sqrt((downx-upx)*(downx-upx)+(downy-upy)*(downy-upy));
  		  		Circle aCircle = new Circle(downx, downy, (int) radius);
		  		circles.set(circles.size()-1, aCircle);
        	}
        	
        	break;
        case MotionEvent.ACTION_CANCEL:
        	break;
        default:
        	break;
        }
        
		
		/*float touched_x = event.getX();
        float touched_y = event.getY();
		
        MovableObject aCircle = circles.get(0);
        double x = aCircle.getX();
        double y = aCircle.getY();
        double newX = (double)touched_x;
        double newY = (double)touched_y;
        aCircle.setX(newX);
        aCircle.setY(newY);
        aCircle.setSpeedX(newX-x);
        aCircle.setSpeedY(newY-y);*/
        return true;
	}
}
