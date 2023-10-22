package Nemo;

public abstract class DepthState {
	
	protected int depth;
	
	public static final String NemoCannotThrowCapsule = "Cannot throw capsule in these conditions. Nemo is destroyed.";
	
	public DepthState previous;

	public int getZ() {
		return this.depth;
	}
	
	public DepthState goUp() {
		return previous;
	}
	
	public abstract DepthState goDown();
	
	public abstract void launchCapsule();

}
