package com.enikbje.falling.objects;

public abstract class MovableObject implements MovableObjectInterface{
	public double x;
	public double y;
	protected double speed_x;
	protected double speed_y;
	
	public MovableObject(double pos_x, double pos_y) {
		x = pos_x;
		y = pos_y;
		speed_x=0;
		speed_y=0;
	}
	
	public MovableObject(double pos_x, double pos_y, double spex, double spey) {
		x = pos_x;
		y = pos_y;
		speed_x = spex;
		speed_y = spey;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setX(double new_x) {
		x = new_x;
	}

	@Override
	public void setY(double new_y) {
		y = new_y;
	}

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
