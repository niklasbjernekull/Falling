package com.enikbje.falling.objects;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Canvas;
import android.graphics.Paint;

public class ConnectedCircles {

	int x;
	int y;
	int r;
	int f;
	ArrayList <FallingAnimal> connected;
	int center;
	
	public ConnectedCircles(int x_in, int y_in, int r_in, int f_in, int amount){
		this.x = x_in;
		this.y = y_in;
		this.r = r_in;
		this.f = f_in;
		connected = new ArrayList<FallingAnimal>();
		
		for(int i=0; i<amount; i++) {
		connected.add(new FallingAnimal(x+(r*i*2), y, r, f));
		}
		
		center = amount/2;
		if(amount%2 == 0) {
			center--;
		}
	}
	
	public void draw(Canvas canvas, Paint paint){
		Iterator <FallingAnimal> iter = connected.iterator();
		while(iter.hasNext()) {
			FallingAnimal anAnimal = iter.next();
			anAnimal.draw(canvas, paint);
		}
	}
	
	public ArrayList getConnected() {
		return connected;
	}
	
	public void calculations() {
		/*FallingAnimal c0 = connected.get(0);
		FallingAnimal c1 = connected.get(1);
		
		double delta = Math.sqrt(Math.pow(c0.x-c1.x,2)+Math.pow(c0.y-c1.y,2));
		double percent = (c0.getRadius()+c1.getRadius())/delta;
		c0.setX(Math.abs(c1.x-((c1.x-c0.x)*percent)));
		c0.setY(Math.abs(c1.y-((c1.y-c0.y)*percent)));
		
		System.out.println("C0: [" + c0.x + ", " + c0.y + "];");
		System.out.println("C1: [" + c1.x + ", " + c1.y + "];");
		
		FallingAnimal c2 = connected.get(2);
		
		delta = Math.sqrt(Math.pow(c2.x-c1.x,2)+Math.pow(c2.y-c1.y,2));
		percent = (c2.getRadius()+c1.getRadius())/delta;
		c2.setX(Math.abs(c1.x-((c1.x-c2.x)*percent)));
		c2.setY(Math.abs(c1.y-((c1.y-c2.y)*percent)));
		
		System.out.println("C2: [" + c2.x + ", " + c2.y + "];");*/
		
		Iterator <FallingAnimal> iter = connected.iterator();
		FallingAnimal animal1 = iter.next();
		FallingAnimal animal2;
		double delta;
		double percent;
		while(iter.hasNext()) {
			animal2 = iter.next();
			delta = Math.sqrt(Math.pow(animal1.x-animal2.x,2)+Math.pow(animal1.y-animal2.y,2));
			percent = (animal1.getRadius()+animal2.getRadius())/delta;
			animal2.setX(Math.abs(animal1.x-((animal1.x-animal2.x)*percent)));
			animal2.setY(Math.abs(animal1.y-((animal1.y-animal2.y)*percent)));
			animal1 = animal2;
		}
		
		/*	One chasing other
		 * 		Iterator <FallingAnimal> iter = connected.iterator();
		FallingAnimal animal1 = iter.next();
		FallingAnimal animal2;
		double delta;
		double percent;
		while(iter.hasNext()) {
			animal2 = iter.next();
			delta = Math.sqrt(Math.pow(animal1.x-animal2.x,2)+Math.pow(animal1.y-animal2.y,2));
			percent = (animal1.getRadius()+animal2.getRadius())/delta;
			animal1.setX(Math.abs(animal1.x-((animal1.x-animal2.x)*percent)));
			animal1.setY(Math.abs(animal1.y-((animal1.y-animal2.y)*percent)));
		 */
	}

}
