package Nemo;

public abstract class Orientation {
	
	public abstract Orientation goRight();
	
	public abstract Orientation goLeft();
	
	public abstract Coordinates advanceForward();
	
	public abstract String orientationGetter(); 

}
