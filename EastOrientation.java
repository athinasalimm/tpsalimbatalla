package Nemo;

public class EastOrientation extends Orientation{

	public Orientation goRight() {
		return new SouthOrientation();
	}
	
	public Orientation goLeft(){
		return new NorthOrientation();
	}
	
	public String orientationGetter() {
		return "east"; 
	}

	public Coordinates advanceForward() {
		return new Coordinates(1, 0);
	}

}
