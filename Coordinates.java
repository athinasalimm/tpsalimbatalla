package Nemo;

public class Coordinates{
		
		private int coordenadaX;
		private int coordenadaY;

		public Coordinates (int coordenadaX, int coordenadaY) {
			this.coordenadaX = coordenadaX;
			this.coordenadaY = coordenadaY;
			}
		
		public int posGetX () {
			return this.coordenadaX;
		}
		
		public int posGetY () {
			return this.coordenadaY;
		}

		public void setY(int coordenadaY) {
	        this.coordenadaY = coordenadaY;
	    }
		
		public void setX(int coordenadaX) {
	        this.coordenadaX = coordenadaX;
	    }
		
		public Coordinates suma(Coordinates anotherCoord) {
			int newX = this.coordenadaX + anotherCoord.coordenadaX;
			int newY =  this.coordenadaY + anotherCoord.coordenadaY;
			Coordinates newPosition = new Coordinates(newX,newY);
			return newPosition;
		}
	

}
