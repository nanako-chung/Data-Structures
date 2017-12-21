package project3;

//import the junit package
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class is responsible for testing the PossibleLocationsStack class's methods
 * using junit testing.
 *
 * @author Nanako Chung
 */
@SuppressWarnings("unused")
public class PossibleLocationsStackTest {

	//tests default constructor 
	@Test
	public void testConstructor() {
		try {
			
			//creates a new stack and tests whether it is empty or not
			PossibleLocationsStack stack=new PossibleLocationsStack();
			assertTrue("The stack has been created and is empty", stack.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised for no param constructor");
		}
	}

	//tests add method
	@Test
	public void testAdd_1() {
		try {

			//creates a new empty stack, adds Location object to it, and tests whether it is empty or not
			PossibleLocationsStack stack=new PossibleLocationsStack();
			Location s=new Location(1,1);
			stack.add(s);
			assertFalse("Item added", stack.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised for add method");
		}
	}

	//tests another case of the add method
	@Test
	public void testAdd_2() {
		try {

			//creates a new empty stack, attempts to add null to it, and tests whether it is empty or not
			PossibleLocationsStack stack=new PossibleLocationsStack();
			stack.add(null);
			assertTrue("The stack is still empty", stack.isEmpty());
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

			//creates a new stack with 2 as the capacity
			PossibleLocationsStack stack=new PossibleLocationsStack();

			//adds Location objects to an empty stack
			Location s=new Location(0,0);
			stack.add(s);
			Location s2=new Location(0,1);
			stack.add(s2);

			//removes each Location object and checks to see if it removed in the proper order
			Location removed2=stack.remove();
			assertTrue("Removed correct item", removed2==s2);
			Location removed3=stack.remove();
			assertTrue("Removed correct item", removed3==s);
		} 

		catch (Exception ex) {
			fail("Exception raised");
		}
	}

	//tests add method in PossibleLocationsStack class
	@Test
	public void testRemove() {
		try {

			//checks to see if removing from an empty stack returns null
			PossibleLocationsStack stack=new PossibleLocationsStack();
			Location removed=stack.remove();
			assertTrue("Correctly returned null", removed==null);
		} 

		catch (Exception ex) {
			fail("Exception raised");
		}
	}

	//tests isEmpty method in PossibleLocationsStack class
	@Test
	public void testIsEmpty_1(){
		try {

			//checks to see if an empty stack is empty
			PossibleLocationsStack stack=new PossibleLocationsStack();
			assertTrue("This stack is empty", stack.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception raised");
		}
	}

	//tests isEmpty method in PossibleLocationsStack class
	@Test
	public void testIsEmpty_2() {
		try {

			//checks to see if a non-empty stack is not empty
			PossibleLocationsStack stack=new PossibleLocationsStack();
			Location s=new Location(10,10);
			stack.add(s);
			assertFalse("This stack is not empty", stack.isEmpty());
		} 

		catch (Exception ex) {
			fail("Exception ex");
		}
	}
}