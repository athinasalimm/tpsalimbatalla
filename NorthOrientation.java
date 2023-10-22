package Nemo;

public class NorthOrientation extends Orientation{

	public Orientation goRight() {
		return new EastOrientation();
	}
	
	public Orientation goLeft(){
		return new WestOrientation();
	}
	
	public String orientationGetter() {
		return "north"; 
	}
	
	public Coordinates advanceForward() {
		return new Coordinates(0, 1);
	}

}
