package com.enikbje.falling.objects;

import com.enikbje.falling.objects.shapes.Circle;

public class FallingAnimal extends Circle {
	private int friction;
	
	private static final int MAX_FALL_SPEED = 40;
	private static final int MAX_SIDE_SPEED = 20;
	private static final float SPEED_DECREASE = 1;
	private static final float FALL_INCREASE = 2;
	
	public FallingAnimal(double pos_x, double pos_y, int r, int f) {
		super(pos_x, pos_y, r);
		speed_x = 0;
		speed_y = 0;
		friction = f;
	}
	
	public void calculateFall () {
		x += speed_x;
		y += speed_y;
		
		/*if(speed_y < MAX_FALL_SPEED) {
			speed_y+=SPEED_DECREASE;
		}*/
		
		speed_y += FALL_INCREASE;
		if(speed_y < 0) {
			speed_y += FALL_INCREASE;
		} else if(speed_y > MAX_FALL_SPEED) {
			speed_y = MAX_FALL_SPEED;
		}
		
		if(speed_x > MAX_SIDE_SPEED) {
			speed_x-=SPEED_DECREASE;
		} else if (speed_x < -MAX_SIDE_SPEED) {
			speed_x+=SPEED_DECREASE;
		}
	}
	
	public int getFriction() {
		return friction;
	}
	
	public int getMass() {
		return mass;
	}
}
