package project3;

//import the junit package
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class is responsible for testing the PossibleLocationsQueue class's methods
 * using junit testing.
 *
 * @author Nanako Chung
 */
@SuppressWarnings("unused")
public class PossibleLocationsQueueTest {

	//tests default constructor 
	@Test
	public void testConstructor() {
		try {
			
			//creates a new queue and tests whether it is empty or not
			PossibleLocationsQueue queue=new PossibleLocationsQueue();
			assertTrue("The queue has been created and is empty", queue.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised for no param constructor");
		}
	}

	//tests constructor with capacity as the parameter 
	@Test
	public void testConstructorOneParam_1() {
		try { 
			
			//creates a new queue with a negative capacity and tests whether it is empty or not
			PossibleLocationsQueue queue=new PossibleLocationsQueue(-1);
			assertTrue("The queue has been created and is empty", queue.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised for negative number as param");
		}
	}

	//tests constructor with capacity as the parameter
	@Test
	public void testConstructorOneParam_2() {
		try {
			
			//creates a new queue with 0 capacity and tests whether it is empty or not
			PossibleLocationsQueue queue=new PossibleLocationsQueue(0);
			assertTrue("The queue has been created and is empty", queue.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised for 0 as param");
		}
	}

	//tests constructor with capacity as the parameter
	@Test
	public void testConstructorOneParam_3() {
		try {
			
			//creates a new queue with positive capacity and tests whether it is empty or not
			PossibleLocationsQueue queue=new PossibleLocationsQueue(10);
			assertTrue("The queue has been created and is empty", queue.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised for positive number as param");
		}
	}

	//tests add method
	@Test
	public void testAdd_1() {
		try {

			//creates a new empty queue, adds Location object to it, and tests whether it is empty or not
			PossibleLocationsQueue queue=new PossibleLocationsQueue();
			Location s=new Location(1,1);
			queue.add(s);
			assertFalse("Item added", queue.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised for add method");
		}
	}

	//tests another case of the add method
	@Test
	public void testAdd_2() {
		try {

			//creates a new empty queue, attempts to add null to it, and tests whether it is empty or not
			PossibleLocationsQueue queue=new PossibleLocationsQueue();
			queue.add(null);
			assertTrue("The queue is still empty", queue.isEmpty());
		} 

		catch (IllegalArgumentException e) {
			//add method throws proper exception
		}

		catch (Exception ex) {
			fail("Exception raised for add method");
		}
	}

	//tests add and remove methods together for proper order
	@Test
	public void testAddAndRemove_1() {
		try {

			//creates a new queue with 2 as the capacity
			PossibleLocationsQueue queue=new PossibleLocationsQueue(2);

			//adds a Location object to an empty queue
			Location s=new Location(0,0);
			queue.add(s);

			//adds a Location object to a semi-full queue
			Location s2=new Location(0,1);
			queue.add(s2);

			//adds a Location object to a full queue
			Location s3=new Location(0,2);
			queue.add(s3);

			//removes each Location object and checks to see if it removed in the proper order that it was added in
			Location removed=queue.remove();
			assertTrue("Removed correct item", removed==s);
			Location removed2=queue.remove();
			assertTrue("Removed correct item", removed2==s2);
			Location removed3=queue.remove();
			assertTrue("Removed correct item", removed3==s3);
		} 

		catch (Exception ex) {
			fail("Exception raised");
		}
	}

	//tests add method in PossibleLocationsQueue class
	@Test
	public void testRemove() {
		try {

			//checks to see if removing from an empty queue returns null
			PossibleLocationsQueue queue=new PossibleLocationsQueue();
			Location removed=queue.remove();
			assertTrue("Correctly returned null", removed==null);
		} 

		catch (Exception ex) {
			fail("Exception raised");
		}
	}

	//tests isEmpty method in PossibleLocationsQueue class
	@Test
	public void testIsEmpty_1(){
		try {

			//checks to see if an empty queue is empty
			PossibleLocationsQueue queue=new PossibleLocationsQueue();
			assertTrue("This queue is empty", queue.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised");
		}
	}

	//tests isEmpty method in PossibleLocationsQueue class
	@Test
	public void testIsEmpty_2() {
		try {

			//checks to see if a non-empty queue is not empty
			PossibleLocationsQueue queue=new PossibleLocationsQueue();
			Location s=new Location(10,10);
			queue.add(s);
			assertFalse("This queue is not empty", queue.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception ex");
		}
	}
}