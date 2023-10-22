package Nemo;

import java.util.List;
import java.util.stream.Collectors;

public class Nemo {
		public DepthState profundity = new Surface();
		public Coordinates Coordinates;
		public  Orientation orientation ;

		
		public Nemo(Coordinates enteredCoordinates, Orientation enteredOrientation ) {
			Coordinates = enteredCoordinates;
			orientation = enteredOrientation;
			
		}
		
	    public int getProfundity() {
	        return profundity.getZ();
	    }
	    
	    public String getOrientation() {
	        return orientation.orientationGetter(); //fijarse que al getter lo puedo hacer durectamente como un atributo de cada rotacion
	    }
		
	    public int getY () {
			return Coordinates.posGetY();
		}
	    
	    public int getX () {
			return Coordinates.posGetX();
		}
 
		public Nemo sendInstructions(String instructions) {
	        List<Character> instructionsList = instructions.chars()
	                .mapToObj(instruction-> (char) instruction)
	                .collect(Collectors.toList());
	       for (char instruction : instructionsList) {
	    	   Command.executeInstructions(instruction, this);
	    	   }
		return this;
	        }
	 
		
		public void forward () {
			Coordinates = Coordinates.suma(orientation.advanceForward());
		}
		
		public void turnRight() {
			orientation = orientation.goRight();
		}
		
		public void turnLeft() {
			orientation = orientation.goLeft();
		}
		
		public void ascend() {
			profundity = profundity.goUp();
		}
		
		public void descend() {
			profundity = profundity.goDown();
		}
		
		public void capsuleLaunching(){
			profundity.launchCapsule();
		}
		
	
		}

