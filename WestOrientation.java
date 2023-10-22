package Nemo;

public class WestOrientation extends Orientation {

	public Orientation goRight() {
		return new NorthOrientation();
	}
	
	public Orientation goLeft(){
		return new SouthOrientation();
	}
	
	public String orientationGetter() {
		return "west"; 
	}
	
	public Coordinates advanceForward() {
		return new Coordinates(-1, 0);
	}

}