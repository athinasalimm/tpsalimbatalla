package queue;

public class Elements extends Container {
	public Elements(Queue queue, Object cargo) {
		super(queue, cargo);
	}

	public Object take() {
		return queue.listOfContainers.remove(0).getCargo();
	}

	public Object head() {
		return queue.listOfContainers.get(0).getCargo();
	}
}
