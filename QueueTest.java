package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


public class QueueTest {

  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWithSomething().isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( "Something", queueWithSomething().head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
	  Queue queue = queueWithSomething();
	  queue.take();
	  assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( "Something", queueWithSomething().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWithFirstAndSecond();
    assertEquals( queue.take(), "First" );
    assertEquals( queue.take(), "Second" );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    assertEquals( queueWithFirstAndSecond().head(), "First" );
  }
  

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
	Queue queue = queueWithSomething();
    assertEquals( 1, queueWithSomething().size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWithFirstAndSecond().size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLikeForTest(() -> NewQueue().take());

  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithSomething();
    queue.take();
    assertThrowsLikeForTest(() -> queue.take());
    
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLikeForTest(() -> NewQueue().head());
  }
  
  private Queue NewQueue() {
		Queue queue = new Queue();
		return queue;
  }
  
  private Queue queueWithSomething() {
		return new Queue().add( "Something" );
  }

  private Queue queueWithFirstAndSecond() {
	Queue queue = NewQueue();
    queue.add( "First" );
    queue.add( "Second" );
	return queue;
  }
	
  private void assertThrowsLikeForTest(Executable executable) {
	  assertThrowsLike(executable, Empty.QueueIsEmpty);
  }

  private void assertThrowsLike(Executable exectuable, String message) {
	  assertEquals(message, (assertThrows(Error.class, exectuable)).getMessage());
  }
}