package project3;

/**
 * This class implements the PossibleLocations interface and therefore implements its methods: 
 * add, remove, and isEmpty. This class is responsible for creating a queue data structure
 * through the array implementation.
 *
 * @author Nanako Chung
 */
public class PossibleLocationsQueue implements PossibleLocations {

	//private data fields in PossibleLocationsQueue class
	private Location[] array;
	private int size=0;
	private int front=0;
	private int capacity=20;
	private int back=capacity-1;

	/**
	 * default constructor (no parameters) that creates a queue array of type Location
	 */
	public PossibleLocationsQueue() {
		this.array=new Location[this.capacity];
	}

	/**
	 * Constructor with capacity of queue as its parameters; sets to full capacity if 
	 * the capacity in the parameters is less than or equal to 0.
	 *
	 * @param capacity of queue
	 */
	public PossibleLocationsQueue(int capacity) {

		//sets to full capacity if a negative or 0 capacity is entered
		if (capacity<=0) {
			this.capacity=20;
		} 
		
		this.capacity=capacity;
		this.array=new Location[capacity];
	}


	/**
	 * Adds Location element to the queue. The element must not be null.
	 *
	 * @param Location s to be appended to the array
	 * @throws IllegalArgumentException if the specified element is null
	 */
	@Override
	public void add(Location s) throws IllegalArgumentException {
		
		//checks to see if object is null and throws exception if it is
		if (s==null) {
			throw new IllegalArgumentException();
		}

		//checks to see if queue is full and calls the makeLarger method if it is
		if (this.size==this.capacity) {
			array=makeLarger(array);
		}

		//allows for the back to "wrap around" array
		this.back=(back+1)%this.capacity;
		array[back]=s;

		//increases size
		this.size++; 
	}

	/**
	 * Removes Location element from the queue. If the size of the queue is 0,
	 * the method returns null.
	 *
	 * @return Location element that is being removed
	 */
	@Override
	public Location remove() {

		//checks to see if there is anything to remove in the queue
		if (this.size==0) {
			return null;
		}

		//stores data in variable l and returns it after removing it
		//allows front to be "wrapped around" queue if necessary
		Location l=array[front];
		this.front=(front+1)%capacity;

		//decreases size and returns l
		this.size--;
		return l;
	}

	/**
	 * Checks to see if the array is empty
	 *
	 * @return boolean value based on whether the array is empty
	 */
	@Override
	public boolean isEmpty(){

		//sees if size is 0
		if (this.size==0) {
			return true;
		} return false;
	}


	/**
	 * This method is responsible for creating a larger queue array with the same Location values
	 * as the old, smaller array, just with a larger capacity. 
	 *
	 * @return Location[] array 
	 */
	private Location[] makeLarger(Location[] array) {

		//creates a new array that is twice the original capacity 
		Location[] biggerArray=new Location[capacity*2];
		
		//iterates through each element in the old array and adds it to the larger array
		for (int i=0; i<array.length; i++) {
			biggerArray[i]=array[(front+i)%capacity];
		} 

		//resets all the data fields and returns the bigger array
		this.front=0;
		this.back=this.size-1;
		this.capacity=2*capacity;
		return biggerArray;
	}
}