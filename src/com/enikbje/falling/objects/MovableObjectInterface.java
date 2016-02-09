package com.enikbje.falling.objects;

public interface MovableObjectInterface extends DrawableObject{
	
	public double getSpeedX();
	
	public double getSpeedY();
	
	public void setSpeedX(double sx);
	
	public void setSpeedY(double sy);
}

