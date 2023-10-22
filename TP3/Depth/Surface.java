package Nemo;

public class Surface extends DepthState{
	@Override
		public int getZ() {
			return this.depth = 0;
		}
	@Override
		public DepthState goDown() {
			return new Cursory();
		}
	@Override
		public DepthState goUp() {
			return this;
		}
	@Override
		public void launchCapsule() {
		}
	
}
