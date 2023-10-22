package Nemo;

public class Deep extends DepthState{
	

	public Deep(int depth, DepthState previous) {
		this.depth = depth;
		this.previous = previous;
	}
	
	public DepthState goDown() {
		return new Deep(depth - 1, this);
	}

	public DepthState goUp() {
		return previous;
	}

	public void launchCapsule() {
		throw new Error (NemoCannotThrowCapsule);
		
	}

}
