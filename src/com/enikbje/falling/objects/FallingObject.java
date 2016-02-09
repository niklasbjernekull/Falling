package com.enikbje.falling.objects;

public abstract class FallingObject extends MovableObject implements FallingObjectsInterface{
	
	public FallingObject(double pos_x, double pos_y) {
		super(pos_x, pos_y);
	}

	protected double speed_x;
	protected double speed_y;

	@Override
	public double getSpeedX() {
		return speed_x;
	}

	@Override
	public double getSpeedY() {
		return speed_y;
	}

	@Override
	public void setSpeedX(double sx) {
		speed_x = sx;
	}

	@Override
	public void setSpeedY(double sy) {
		speed_y = sy;	
	}
}
