package queue;

public abstract class Container {
	protected Queue queue;
	private Object cargo;
	
	public Container(Queue queue, Object cargo) {
		this.queue = queue;
		this.cargo = cargo;
	}
	public Object getCargo() {
		return cargo;
	}
	public abstract Object take();
	public abstract Object head(); 
}
