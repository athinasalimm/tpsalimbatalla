package queue;

public class Empty extends Container {
	
	public static String QueueIsEmpty = "Queue is empty";

	public Empty(Queue queue, Object cargo) {
		super(queue, cargo);

	}

	public Object take() {
		throw new Error(QueueIsEmpty);
	}

	public Object head() {
		throw new Error(QueueIsEmpty);
	}

}
