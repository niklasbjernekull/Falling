package com.enikbje.falling.collision;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Color;

import com.enikbje.falling.objects.FallingAnimal;
import com.enikbje.falling.objects.MovableObject;
import com.enikbje.falling.objects.shapes.Circle;
import com.enikbje.falling.objects.shapes.Line;

/**
 * Handles collision of objects sent in
 * @author enikbje
 *
 */
public class Collisions {
	private int height;
	private int width;
	
	/**
	 * Constructor
	 * @param h = height
	 * @param w = width
	 */
	public Collisions (int h, int w) {
		height = h;
		width = w;
	}
	
	public void calculateCollisionsFallAndWall(ArrayList <FallingAnimal> animals) {
		Iterator <FallingAnimal> iter = animals.iterator();
		FallingAnimal anAnimal;
		while(iter.hasNext()) {
			anAnimal = iter.next();
			//Calculate movement
			anAnimal.calculateFall();
			
			//Calculate collisions
			calculateWallOnlyCollision(anAnimal, anAnimal.getFriction());	//Animal-Wall
			//calculateWallFloorCollision(anAnimal, anAnimal.getFriction());	//Animal-Wall
		}
	}
	
	
	/**
	 * Main class to start collision handling between
	 * animals and otherObjects
	 * @param animals = FallingAnimals
	 * @param otherObjects = MovableObject
	 */
	public void calculateCollisionsCircles(ArrayList <FallingAnimal> animals, ArrayList <MovableObject> otherObjects) {
		
		/*Iterator <FallingAnimal> iter = animals.iterator();
		//FallingAnimal oldAnimal = null;
		FallingAnimal anAnimal = iter.next();
		FallingAnimal nextAnimal;
		while(iter.hasNext()) {*/
		FallingAnimal anAnimal;
		FallingAnimal nextAnimal;
		for(int i=0; i<animals.size(); i++) {
			anAnimal = animals.get(i);
			
			Iterator <MovableObject> citer = otherObjects.iterator();
			MovableObject anObject;
			while(citer.hasNext()) {
				anObject = citer.next();
				calculateCircleCircleForceCollision((Circle)anAnimal, (Circle)anObject);
			}
			
			/*Iterator <FallingAnimal> nextiter = iter;
			while(nextiter.hasNext()) {*/
			for(int j=i+1; j<animals.size(); j++) {
				FallingAnimal otherAnimal = animals.get(j);
				if(!otherAnimal.equals(anAnimal)) {	
					calculateCircleCircleForceCollision((Circle)anAnimal, (Circle)otherAnimal);
				}
			}
		}
	}
	
	/**public void calculateCollisionsWithoutSelfCollision(ArrayList <FallingAnimal> animals, ArrayList <MovableObject> otherObjects) {
		
		/*Iterator <FallingAnimal> iter = animals.iterator();
		//FallingAnimal oldAnimal = null;
		FallingAnimal anAnimal = iter.next();
		FallingAnimal nextAnimal;
		while(iter.hasNext()) {*
		FallingAnimal anAnimal;
		FallingAnimal nextAnimal;
		for(int i=0; i<animals.size(); i++) {
			anAnimal = animals.get(i);
			
			//Calculate movement
			anAnimal.calculateFall();
			
			//Calculate collisions
			calculateWallCollision(anAnimal, anAnimal.getFriction());	//Animal-Wall
			
			Iterator <MovableObject> citer = otherObjects.iterator();
			MovableObject anObject;
			while(citer.hasNext()) {
				anObject = citer.next();
				calculateCircleCircleForceCollision((Circle)anAnimal, (Circle)anObject);
			}
			
			/*Iterator <FallingAnimal> nextiter = iter;
			while(nextiter.hasNext()) {*
			/*for(int j=i+1; j<animals.size(); j++) {
				FallingAnimal otherAnimal = animals.get(j);
				if(!otherAnimal.equals(anAnimal)) {	
					calculateCircleCircleForceCollision((Circle)anAnimal, new Circle(otherAnimal.getX(), otherAnimal.getY(), otherAnimal.getRadius()));
				}
			}*
		}
	}*/
	
	/**
	 * Calculates the collision between Circle and screen edges
	 * @param aCircle
	 */
	private void calculateWallFloorCollision(Circle aCircle, double friction) {
		//if hitting x=max (width)
		if(aCircle.getX()+aCircle.getRadius() >= width) {
			if (aCircle.getSpeedX() > 0) {
				aCircle.setSpeedX(-aCircle.getSpeedX());
			}
			aCircle.setX(width-aCircle.getRadius());
		//if hitting x=min (0)
		} else if (aCircle.getX()-aCircle.getRadius() <= 0) {
			if (aCircle.getSpeedX() < 0) {
				aCircle.setSpeedX(-aCircle.getSpeedX());
			}
			aCircle.setX(aCircle.getRadius());
		}
		
		//if hitting y=max (height)
		if(aCircle.getY()+aCircle.getRadius() >= height) {
			if (aCircle.getSpeedY() > 0) {
				aCircle.setSpeedY(-aCircle.getSpeedY()+friction);
			}
			aCircle.setY(height-aCircle.getRadius());
			//if hitting y=min (0)
		} else if (aCircle.getY()-aCircle.getRadius() <= 0) {
			if (aCircle.getSpeedY() < 0) {
				aCircle.setSpeedY(-aCircle.getSpeedY()-friction);
			}
			aCircle.setY(aCircle.getRadius());
		}
	}
	
	/**
	 * Calculates the collision between Circle and screen edges
	 * @param aCircle
	 */
	private void calculateWallOnlyCollision(Circle aCircle, double friction) {
		//if hitting x=max (width)
		if(aCircle.getX()+aCircle.getRadius() >= width) {
			if (aCircle.getSpeedX() > 0) {
				aCircle.setSpeedX(-aCircle.getSpeedX());
			}
			aCircle.setX(width-aCircle.getRadius());
		//if hitting x=min (0)
		} else if (aCircle.getX()-aCircle.getRadius() <= 0) {
			if (aCircle.getSpeedX() < 0) {
				aCircle.setSpeedX(-aCircle.getSpeedX());
			}
			aCircle.setX(aCircle.getRadius());
		}
		
		//if going through floor
		if(aCircle.getY()-aCircle.getRadius() >= height) {
			aCircle.setY(-aCircle.getRadius());
		}
	}
	
	/**
	 * Calculate collision between two circles; FallingAnimal and Circle
	 * @param anAnimal
	 * @param aCircle
	 *
	private void calculateCircleCircleCollision(Circle anAnimal, Circle aCircle) {
		double ax = anAnimal.getX(); double ay = anAnimal.getY();
		double cx = aCircle.getX(); double cy = aCircle.getY();
		int ar = anAnimal.getRadius(); int cr = aCircle.getRadius();
		
		//elimination
		if((ax+ar < cx-cr || ax-ar > cx+cr) && (ay+ar < cy-cr || ay-ar > cy+cr)) {
			return;
		}
		
		double deltaXSquared = ax - cx; // calc. delta X
		deltaXSquared *= deltaXSquared; // square delta X
		double deltaYSquared = ay - cy; // calc. delta Y
		deltaYSquared *= deltaYSquared; // square delta Y

		
		// Calculate the sum of the radii, then square it
		double sumRadiiSquared = ar + cr; 
		sumRadiiSquared *= sumRadiiSquared;

		//if they touch
		if(deltaXSquared + deltaYSquared <= sumRadiiSquared){
			//Calculate total force and distributes it according to
			//the angle of impact of the other circle
			float forceTotal = Math.abs((float)anAnimal.getSpeedX() + (float)anAnimal.getSpeedY());
			float absX =  Math.abs((float)ax-(float)cx);
			float absHundredPercent = Math.abs((float)ay-(float)cy) + absX;
			float forceX = ( absX / absHundredPercent)*forceTotal;
			float forceY = forceTotal - forceX;
			
			// Since the forces are calculated with Math.abs we need to
			// find out which side the circles hit and maybe change direction.
			if(ax < cx) {
				forceX = -forceX;
			}
			if(ay < cy) {
				forceY = -forceY;
			}
			
			anAnimal.setSpeedX(forceX);
			anAnimal.setSpeedY(forceY);
			
			//if they intersect we need to separate the circles
			if(deltaXSquared + deltaYSquared < sumRadiiSquared){
				double extentionPercentage = (ar + cr) / Math.sqrt(deltaXSquared+deltaYSquared);
				if(extentionPercentage!=0) {
					double newXpos = ((ax-cx)*extentionPercentage)+cx;
					anAnimal.setX(newXpos);
					double newYpos = ((ay-cy)*extentionPercentage)+cy;
					anAnimal.setY(newYpos);
				}
			}
		}
	}*/
	
	/**
	 * Calculate collision between two circles; and the force on both
	 * @param anAnimal
	 * @param aCircle
	 */
	private void calculateCircleCircleForceCollision(Circle anAnimal, Circle aCircle) {
		double ax = anAnimal.getX(); double ay = anAnimal.getY();
		double cx = aCircle.getX(); double cy = aCircle.getY();
		int ar = anAnimal.getRadius(); int cr = aCircle.getRadius();
		
		//elimination, bounding squares
		if((ax+ar < cx-cr || ax-ar > cx+cr) && (ay+ar < cy-cr || ay-ar > cy+cr)) {
			return;
		}
		
		double deltaXSquared = ax - cx; // calc. delta X
		deltaXSquared *= deltaXSquared; // square delta X
		double deltaYSquared = ay - cy; // calc. delta Y
		deltaYSquared *= deltaYSquared; // square delta Y

		
		// Calculate the sum of the radii, then square it
		double sumRadiiSquared = ar + cr; 
		sumRadiiSquared *= sumRadiiSquared;

		//if they touch
		if(deltaXSquared + deltaYSquared <= sumRadiiSquared){
			//Calculate total force and distributes it according to
			//the angle of impact of the other circle
			float absX = (float)ax-(float)cx;
			float absY = (float)ay-(float)cy;
			float absHundredProcent = Math.abs(absX)+Math.abs(absY);
			
			float forceTotal = Math.abs((float)anAnimal.getSpeedX()) +  Math.abs((float)anAnimal.getSpeedY());
			
			//Change force on anAnimal
			float forceX = (absX / absHundredProcent)*forceTotal;
			float forceY = (absY / absHundredProcent)*forceTotal;
			
			anAnimal.setSpeedX(forceX);
			anAnimal.setSpeedY(forceY);
			
			//Change force on aCircle
			absX = (float)cx-(float)ax;
			absY = (float)cy-(float)ay;
			absHundredProcent = Math.abs(absX)+Math.abs(absY);
			
			forceTotal = Math.abs((float)aCircle.getSpeedX()) +  Math.abs((float)aCircle.getSpeedY());
			
			forceX = (absX / absHundredProcent)*forceTotal;
			forceY = (absY / absHundredProcent)*forceTotal;
			
			aCircle.setSpeedX(forceX);
			aCircle.setSpeedY(forceY);
			
			//if they intersect we need to separate the circles
			if(deltaXSquared + deltaYSquared < sumRadiiSquared){
				double extentionPercentage = (ar + cr) / Math.sqrt(deltaXSquared+deltaYSquared);
				if(extentionPercentage!=0) {
					double newXpos = ((ax-cx)*extentionPercentage)+cx;
					anAnimal.setX(newXpos);
					double newYpos = ((ay-cy)*extentionPercentage)+cy;
					anAnimal.setY(newYpos);
				}
			}
		}
	}
	
	
	public void calculateCollisionsLines(ArrayList <FallingAnimal> animals, ArrayList <Line> lines) {
		Circle aCircle;
		Line aLine;
		
		Iterator <FallingAnimal> citer = animals.iterator();
		while(citer.hasNext()) {
			aCircle = (Circle) citer.next();
			Iterator <Line> liter = lines.iterator();
			while(liter.hasNext()) {
				aLine = (Line) liter.next();
				calculateCircleLineCollision(aCircle, aLine);
			}
		}
		
	}
	
	/**
	 * Calculate the intersection of line and circle
	 * @param anAnimal
	 * @param aLine
	 */
	public void calculateCircleLineCollision(Circle aCircle, Line aLine) {
		//line points
		double lax = aLine.x; double lay = aLine.y;
		double lbx = aLine.end_x; double lby = aLine.end_y;
		//circle
		double cx = aCircle.x; double cy = aCircle.y; double cr = aCircle.getRadius();
		
		//if line is a dot
		if(lax==lbx && lay == lby) {
			return;
		}
		
		//quick test to see if in boundary square
		if((cx+cr < lax && cx+cr < lbx) || (cx-cr > lax && cx-cr > lbx) || (cy+cr < lay && cy+cr < lby) || (cy-cr > lay && cy-cr > lby)) {
			return;
		}
		
		double baX = lbx - lax;
        double baY = lby - lay;
        double caX = cx - lax;
        double caY = cy - lay;

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - cr * cr;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double discriminant = pBy2 * pBy2 - q;
        if (discriminant < 0) {
        	//does not intersect
        	
        	return;
        }
        // if disc == 0 ... dealt with later
        double tmpSqrt = Math.sqrt(discriminant);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        
        double p1x = lax - baX * abScalingFactor1;
        double p1y = lay - baY * abScalingFactor1;
        
        //center point of intersection
        double pcx = p1x;
        double pcy = p1y;
        
        if (discriminant == 0) { // abScalingFactor1 == abScalingFactor2
        	//one point intersection [p1x,p1y]
        	aLine.setColor(Color.GREEN);

        } else {
	        double p2x = lax - baX * abScalingFactor2;
	        double p2y = lay - baY * abScalingFactor2;
	        //two point intersection [p1x,p1y][p2x,p2y]
	        aLine.setColor(Color.BLUE);
	        
	        //center point of p1 and p2
	        pcx = ((p2x-p1x)/2)+p1x;
	        pcy = ((p2y-p1y)/2)+p1y;
        }
        
        /**-----Reject Circle-----**/
        
        float absX = (float)cx-(float)pcx;
		float absY = (float)cy-(float)pcy;
		float absHundredProcent = Math.abs(absX)+Math.abs(absY);
		
		float forceTotal = Math.abs((float)aCircle.getSpeedX()) +  Math.abs((float)aCircle.getSpeedY());
		
		float forceX = (absX / absHundredProcent)*forceTotal;
		float forceY = (absY / absHundredProcent)*forceTotal;
		
		aCircle.setSpeedX(forceX);
		aCircle.setSpeedY(forceY);
	}

}
