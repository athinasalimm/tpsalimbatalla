package Nemo;

public class SouthOrientation extends Orientation{

	public Orientation goRight() {
		return new WestOrientation();
	}
	
	public Orientation goLeft(){
		return new EastOrientation();
		}
	
	public String orientationGetter() {
		return "south";
	}
	
	public Coordinates advanceForward() {
		return new Coordinates(0, -1);
	}
	}

