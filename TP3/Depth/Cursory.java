package Nemo;

public class Cursory extends DepthState {
	
	public Cursory() {
		this.depth = -1;
		this.previous= new Surface();
	}
	

	public DepthState goDown() {
		return new Deep(this.depth -1, this);
	}

	public DepthState goUp() {
		return previous;
	}

	public void launchCapsule() {}

}
