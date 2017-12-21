package project2;

/**
 * This class is responsible for creating and assigning Nodes from the nested Node class. Since this class also implements the 
 * OrderedList<E> interface, there are overridden methods that each perform a distinct task on an OrderedLinkedList object (i.e. adds,
 * removes, clears elements).
 * 
 * @author Nanako Chung
 */
public class OrderedLinkedList<E extends Comparable<E>> implements OrderedList<E> {

	/**
	 * This class is responsible for allowing the programmers to create Nodes, a critical way of representing our 
	 * elements in a OrderedLinkedList. This class is static because for every instance of Node the nodes of the 
	 * OrderedLinkedList should be the same (i.e. the head element is the head element of every instance of Node).
	 * The class is also private because there is no need for changes/access of the Node class in other classes.
	 * 
	 * Implementation based on Data Structures and Algorithms textbook by Michael Goodrich, Roberto Tamassia, and Michael Goldwasser in Chapter 3
	 */
	private static class Node<E> {

		//establishes private data fields of Node class
		private E element;
		private Node<E> next;

		/**
		 * Creates an instance of Node
		 *
		 * @param e element
		 */
		public Node(E e) {
			this.element=e;
		}

		/**
		 * Creates an instance of Node
		 *
		 * @param e element
		 * @param Node<E> next node n
		 */
		public Node(E e, Node<E> n) {
			this.element=e;
			this.next=n;
		}

		/**
		 * returns the element of the Node
		 *
		 * @return element that the Node holds
		 */
		public E getElement() {
			return this.element;
		}

		/** 
		 * returns the reference of the next Node
		 *
		 * @return next Node in OrderedLinkedList
		 */
		public Node<E> getNext() {
			return this.next;
		}

		/**
		 * sets the next Node reference to n
		 *
		 * @param Node of type E that the user wants to set the next Node's reference to
		 */
		public void setNext(Node<E> n) {
			this.next=n;
		}
	}
	
	//private data fields in the OrderedLinkedList<E> class
	private Node<E> head=null;
	private int size=0;

	/**
	 * Default constructor (no parameters) for the OrderedLinkedList class
	 */
	public OrderedLinkedList() {
	}

 	/**
     * Adds the specified element to this list in a sorted order. 
     *
     * <p>The element added must implement Comparable<E> interface. This list 
     * does not permit null elements. 
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws ClassCastException if the class of the specified element
     *         does not implement Comparable<E> 
     * @throws NullPointerException if the specified element is null
     *
	 * Implementation based on lecture02 notes by Joanna Klukowska
     */
	@Override
	public boolean add(E e) throws ClassCastException, NullPointerException {

		//setting the current node equal to head
		Node<E> current=this.head;

		//creating a new node containing element e to be used in the program later on 
		Node<E> newNode=new Node<E>(e);

		//checks to see if the element e is null and throws exception if it is
		if (e==null) {
			throw new NullPointerException();
		}

		//checks to see if the list is empty and sets the new node as the head if it is
		if (this.head==null) {
			this.head=newNode;
			size++;
			return true;
		}

		//checks to see if the current is not null (in case we need to iterate through the LinkedList)
		if (current!=null) {

			//if the e element is less than the head's element, make a new node containing e and set it to the head
			if (e.compareTo(this.head.getElement())<0) {

				//make the new node point to the current first node
				newNode.setNext(this.head);

				//make the head reference point to the new first node
				this.head=newNode;

				//increase size
				size++;

				//indicate to user that the element was successfully added
				return true;
			} 

			//considers if e is greater than the head's element 
			else {

				//iterates through the list for as long as the next node after current is not null and
				//adds to the list if the next element is bigger than e; increases size
				while (current.getNext()!=null) {

					if (e.compareTo(current.getNext().getElement())<0) {
						newNode.setNext(current.getNext());
						current.setNext(newNode);
						size++;
						return true;
					} 

					else {
						current=current.getNext();
					}
				} 

				//if e does not pass the conditions above and is bigger than every element in the list, add
				//the element to the end of the list
				//make the new node point to the current first node
				newNode.setNext(null);

				//make the head reference point to the new first node and increase size
				current.setNext(newNode);
				size++;

				//indicate to user that the element was successfully added
				return true;
			}
		} 

		//if all conditions above fail, return false
		return false;
	}

	/**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     *
	 * Implementation based on lecture02 notes by Joanna Klukowska
     */
	@Override 
	public void clear() {

		//points head node to null to create an empty list
		this.head=null;
		this.size=0;
	}

	/**
     * Returns a shallow copy of this list. (The elements
     * themselves are not cloned.)
     *
     * @return a shallow copy of this list instance
     * @throws CloneNotSupportedException if clone() method cannot be used
     *
	 * Implementation based on Data Structures and Algorithms textbook by Michael Goodrich, Roberto Tamassia, and Michael Goldwasser in Chapter 3
     */
	@Override
	public Object clone() throws CloneNotSupportedException {

		//creates a cloned OrderedLinkedList with variable name other
		OrderedLinkedList<E> other = (OrderedLinkedList<E>) super.clone();

		//if head of list is null, set head of other list to null and return an empty list
		if (this.head==null) {
			other.head=null;
			return other;
		} 

		//checks to see if list has elements; if it does not, then return other
		if (this.size>0) {

			//sets the head of the other list to null
			other.head=new Node<E>(this.head.getElement(), null);

			//creates a new Node instance called walk to hold the reference of the next node (after head)
			Node<E> walk=this.head.getNext();

			//creates a new Node called otherTail to hold the reference of the head of other list
			Node<E> otherTail=other.head;

			//checks to see that walk isn't null and iterates over each node in the list
			//creates a new Node newest that sets the current element in walk to null
			//sets the node next to head of other OrderedLinkedList to newest then returns walk
			while(walk!=null) {
				Node<E> newest=new Node<E>(walk.getElement(), null);
				otherTail.setNext(newest);
				otherTail=newest;
				walk=walk.getNext();
			}
		}

		//returns other list
		return other;
	}

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null 
     *
	 * Implementation based on lecture02 notes by Joanna Klukowska
     */
	@Override
	public boolean contains(Object o) throws ClassCastException, NullPointerException {

		//checks to see if o is null and throws exception if it is
		if (o==null) {
			throw new NullPointerException();
		}

		//checks to see if the list is empty
		if (this.head==null) {
			return false;
		} 

    	//create a current node that references head
		Node<E> current=this.head;

		//iterates through the list and checks to see if the data matches o
		while (current!=null) {
			if (current.getElement().equals(o)) {
				return true;
			} 

			//advances to next node in the list
			current=current.getNext();
		}

		//o is not in the list
		return false;
	}

    /**
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code e1.equals(e2)}.)  
     * In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.<p>
     *
     * @param o the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     * @throws NullPointerException if object is null
     *
	 * Implementation based on Data Structures and Algorithms textbook by Michael Goodrich, Roberto Tamassia, and Michael Goldwasser in Chapter 3
     */
	@Override
	public boolean equals(Object o) throws NullPointerException {

		//checks to see if o is null and returns false if it is
		if (o==null) {
			return false;
		}

		//cast o into OrderedLinkedList and check to see if size of lists are the same
		OrderedLinkedList<E> other=(OrderedLinkedList<E>) o;

		//checks to see if sizes of the list are the same
		if (this.size()!=other.size()) {
			return false;
		}

		//set node walkA to head of current OrderLinkedList and set walkB to head of other OrderedLinkedList
		Node<E> walkA=this.head;
		Node<E> walkB=other.head;

		//if both heads are null, they are both empty lists and still equal to each other
		if (walkA==null) {
			if (walkB==null) {
				return true;
			} else {
				return false;
			}
		} 

		//checks to see that walkA isn't null and compares each element to see if the nodes' elements are not equal
		//if they are equal, loop sets each node reference to the next node in the list and rechecks equality until end of the list
		while (walkA!=null) {
			if (!walkA.getElement().equals(walkB.getElement())) {
				return false;
			} 

			walkA=walkA.getNext();
			walkB=walkB.getNext();
		}
		return true;
	}

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range 
     * <tt>(index < 0 || index >= size())</tt>
     *
	 * Implementation based on lecture02 notes by Joanna Klukowska
     */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {

		//checks to see if this is an empty list
		if (this.head==null) {
			return null;
		}

		//creates a node current with head reference
		Node<E> current=this.head;

		//creates a counter variable
		int counter=0;

		//iterates through the list for as long as the current node is not null and the index is not equal to index
		//adds 1 to counter to get the number of the index
		while (current!=null && counter!=index) {
			counter++;
			current=current.getNext();
		}

		//returns the data at the index and returns null if we have iterated through to the end of the list
		if (counter==index) {
			return current.getElement();
		} else {
			return null;
		}
	}

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     * @throws NullPointerException if object is null
     *
	 * Implementation based on lecture02 by Joanna Klukowska
     */
	@Override
    public int indexOf (Object o) throws NullPointerException {

    	if (this.head==null || o==null) {
			return -1;
		}

    	//create a current node that references head
    	Node<E> current = this.head;

    	//create an index variable
    	int index=0;

    	//iterate through the list (until it hits null) to find o; if found, return index number
    	while (current!=null) {

    		if (current.getElement()==o) {
    			return index;
    		} 
	    	current=current.getNext();
	    	index++;
    	}

    	//there is no index with indicated object o so return -1
    	return -1;
    }

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices if such exist).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException  if the index is out of range 
     * <tt>(index < 0 || index >= size())</tt>
     *
	 * Implementation based on lecture02 by Joanna Klukowska
     */
    @Override
    public E remove (int index) throws IndexOutOfBoundsException {

    	//validates index number
    	if (index<0 || index>=this.size()) {
    		throw new IndexOutOfBoundsException();
    	}

    	//checks if list is empty
    	if (this.head==null) {
    		return null;
    	}

		//creates variable data to store head element 
		E data = this.head.getElement();

    	//if the method wants to remove the first index, set head equal to the next node (removing head)
		if (index==0) {
    		head=this.head.getNext();
    		size--;
    		return data;
    	}

		//create a current node
		Node<E> current=this.head;

		//iterate through each node in the list
		for (int i=0; i<this.size(); i++) {

			//removing the last node
			if (i==(this.size()-2) && i==index-1) { 

				//set data to the element that is about to be removed
				data=current.getNext().getElement();

				//set the next node to null, reduce size, and return data
				current.setNext(null);
				size--;
				return data;
			} 

			//removing a node in the middle of the list
			if (i==index-1) {

				//set data to the element that is about to be removed
				data=current.getNext().getElement();

				//set the next node to two nodes ahead, reduce size, and return data
				current.setNext(current.getNext().getNext());
    			size--;
    			return data;
    		}

    		//advance current node to the next
	    	current=current.getNext();
		}

		//if all fails, return null
		return null;
	}

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * <tt>(o.equals(get(i))</tt>
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     *
	 * Implementation based on lecture02 by Joanna Klukowska
     */
    @Override
    public boolean remove(Object o) throws NullPointerException, ClassCastException {

    	//checks to see if object to remove is null
    	if (o==null){
    		throw new NullPointerException();
    	}

    	//checks to see if this is an empty list
    	if (this.head==null) {
    		return false;
    	}

    	//checks to see if the list contains object o and removes it if it's there
    	if (this.contains(o)) {

    		//sets current node to head
    		Node<E> current=this.head; 

    		//iterates through the nodes in the list and if o is an element within that list, remove it
	    	for (int i=0; i<this.size(); i++) {
	    		if (o.equals(this.get(i))) {
	    			remove(i);
	    			return true;
	    		}

	    		//advance node
	    		current=current.getNext();
	    	}
	    } return false;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
	 * Implementation based on lecture02 by Joanna Klukowska
     */
    @Override
    public int size() {
		return this.size;
    }

    /**
     * Returns a string representation of this list.  The string
     * representation consists of a list of the list's elements in the
     * order they are stored in this list, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings 
     * by the {@code toString()} method of those elements.
     *
     * @return a string representation of this list
     */
    @Override
    public String toString() {

    	//sets current node to head
    	Node<E> current=this.head;

    	//if the list is empty, return empty brackets
    	if (this.head==null) {
    		return "[]";
    	}
    	
    	//accumulator String variable 
    	String summaryList="";

    	//iterates through each node in the list, gets its element, then calls the .toString() method
    	//summaryList is formatted correctly by having the ", " added after each element
    	while (current!=null) {
    		if (current.getNext()!=null) {
    			summaryList+=current.getElement().toString()+", ";
    		} else {
    			summaryList+=current.getElement().toString();
    		}

    		//advances node
    		current=current.getNext();
    	}

    	//returns the summaryList String with brackets around it
    	return ("["+summaryList+"]");
    }
}