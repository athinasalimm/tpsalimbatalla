package queue;
import java.util.ArrayList;


public class Queue {
	
	
	public ArrayList<Container> listOfContainers;
	private Container newQueue;
	private Container previousInstanceOfNewQueue;
	
	
	public Queue(){
		listOfContainers = new ArrayList<>();
		Object cargo = null;
		newQueue = new Empty(this, cargo);
		previousInstanceOfNewQueue = new Empty(this, cargo);
	}

	
public boolean isEmpty() { 
	  return listOfContainers.isEmpty();
  }

	public Queue add(Object cargo) {
		listOfContainers.add(new Elements(this, cargo));
		previousInstanceOfNewQueue = newQueue;
		newQueue = new Elements(this, cargo); 
		return this;
	}
	
	
	public Object take() { 
		Object previouselement = newQueue.take();
		newQueue = previousInstanceOfNewQueue;
		return previouselement;
		
	}

	public Object head() {
		return newQueue.head();
		}

	public int size() {
		return listOfContainers.size();
	}

}
