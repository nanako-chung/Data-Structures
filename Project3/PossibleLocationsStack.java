package project3;

/**
 * This class implements the PossibleLocations interface and therefore implements its methods: 
 * add, remove, and isEmpty. This class is responsible for creating a stack data structure
 * through the linked list implementation, which is why it has a private static Node class.
 *
 * @author Nanako Chung
 */
public class PossibleLocationsStack implements PossibleLocations {

	/**
	 * This class is responsible for allowing the programmers to create Nodes, a critical way of representing our 
	 * Location elements in a linked list. This class is static because for every instance of Node the nodes of the 
	 * linked list should be the same (i.e. the head element is the head element of every instance of Node).
	 * The class is also private because there is no need for changes/access of the Node class in other classes.
	 */
	private static class Node {

		//establishes private data fields of Node class
		private Location element;
		private Node next;

		/**
		 * Creates an instance of Node
		 *
		 * @param e of type Location
		 */
		public Node(Location e) {
			this.element=e;
		}

		/**
		 * returns the element of the Node
		 *
		 * @return element that the Node holds
		 */
		public Location getElement() {
			return this.element;
		}

		/** 
		 * returns the reference of the next Node
		 *
		 * @return next Node in linked list
		 */
		public Node getNext() {
			return this.next;
		}

		/**
		 * sets the next Node reference to n
		 *
		 * @param Node n that the user wants to set the next Node's reference to
		 */
		public void setNext(Node n) {
			this.next=n;
		}
	}

	//private data fields in PossibleLocationsStack class
	private Node top=null;
	private int size=0;

	/**
	 * default constructor (no parameters)
	 */
	public PossibleLocationsStack() {
		this.top=null;
		this.size=0;
	}

	/**
	 * Adds Location element to the top of the stack. The element must not be null.
	 *
	 * @param Location s to be appended to the list
	 * @throws IllegalArgumentException if the specified element is null
	 */
	@Override
	public void add(Location s) throws IllegalArgumentException {

		//checks to see if object is null and throws exception if it is
		if (s==null) {
			throw new IllegalArgumentException();
		}

		//creates a new Node that stores Location s and sets the next node to the top
		Node newNode=new Node(s);
		newNode.setNext(top);

		//sets the newNode as top
		top=newNode;

		//increases size
		this.size++;
	}

	/**
	 * Removes Location element from the top of the stack. If the size of the stack is 0,
	 * the method returns null.
	 *
	 * @return Location element of Node that is being removed
	 */
	@Override
	public Location remove() {

		//checks to see if list is empty & returns null
		if (this.size==0) {
			return null;
		}

		//stores the top element's element in variable data
		Location data=top.getElement();

		//removes the top node, decrements size, and returns data
		top=top.getNext();
		this.size--;
		return data;
	}

	/**
	 * Checks to see if the linked list is empty
	 *
	 * @return boolean value based on whether the top is null (list is empty)
	 */
	@Override
	public boolean isEmpty(){

		//checks to see if the top points to null (if the list is empty)
		return top==null;
	}
}