package project5; 

//imports proper packages needed
import project5.Date;
import java.util.*;

/**
 * This class is responsible for processing info about all collisions records (i.e. all valid
 * rows from input file) in the form of an AVL tree.
 *
 * @author Nanako Chung/Joanna Klukowska
 */
public class CollisionsData {

	//root of tree
	protected Node root;
	//helper variable used by the remove methods
	private boolean found;

	/**
	 * This is a default constructor that creates an empty tree.
	 */
	public CollisionsData() {
		this.root=null;
	}

	/**
	 * Add the given data item to the AVL tree. If item is null, the AVL tree does not
	 * change. If item already exists, the AVL tree does not change. 
	 * 
	 * @param item the new element to be added to the AVL tree
	 */
	public void add(Collision item) {

		//if item is empty, return nothing
		if (item==null) {
			return;
		}

		//calls private recursive add method
		root=recAdd(this.root, item);
	}

	/**
	 * Actual recursive implementation of add (also balances AVL tree)
	 * 
	 * @param a node 
	 * @param an item the new element to be added to the AVL tree
	 * @return balanced node
	 */
	private Node recAdd(Node node, Collision item) {

		//if node is empty, simply create a new node
		if (node == null) { 
			return new Node(item);
		}

		//compares Collision object stored in node with Collision item and decides which child to recursively call on
		if (node.data.compareTo(item) > 0) {
			node.left = recAdd(node.left, item);
		} else {
			node.right = recAdd(node.right, item);
		}

		//updates height of node
		updateHeight(node);
		//returns a balanced node
		return balance(node); 
	}

	/**
	 * Remove the item from the AVL tree. If item is null the AVL tree remains unchanged. If
	 * item is not found in the AVL tree, the AVL tree remains unchanged.
	 * 
	 * @param target item of type Collision to be removed from this AVL tree 
	 * @return boolean found
	 */
	public boolean remove(Collision target) {

		//tree does not change
		if (target==null) {
			return false;
		}

		//recursive call to recursive remove method
		this.root=recRemove(target, root);
		return found;
	}

	/**
	 * Actual recursive implementation of remove method: find the node to remove.  
	 * 
	 * @param target the item to be removed from this AVL tree
	 * @param a node of type Node
	 * @return balanced node
	 */
	private Node recRemove(Collision target, Node node) {

		//if node is empty after recursively calling on it, then the Collision object is not found
		if (node == null) {
			found = false;
		} //compares Collision object stored in node with Collision item and decides which child to recursively call on
		else if (target.compareTo(node.data) < 0) {
			node.left = recRemove(target, node.left);
		} else if (target.compareTo(node.data) > 0) {
			node.right = recRemove(target, node.right);
		} else {
			node = removeNode(node);
			found = true;
		} 
		//updates height of node
		updateHeight(node);

		//returns node
		return balance(node);
	}

	/**
	 * Actual recursive implementation of remove method: perform the removal.  
	 * 
	 * @param target the item to be removed from this AVL tree 
	 * @return a reference to the node itself, or to the modified subtree 
	 */
	private Node removeNode(Node node) {

		//checks if left or right child is empty and balances right or left child respectively 
		if (node.left == null) {
			return node.right;
		}

		if (node.right  == null) {
			return node.left;
		}

		//since there are two children, find the predecessor, remove, and return balanced node
		Collision data = getPredecessor(node);
		node.data = data;
		node.left = recRemove(data, node.left);
		return node;
	}

	/**
	 * Returns the information held in the rightmost node of subtree  
	 * 
	 * @param subtree root of the subtree within which to search for the rightmost node 
	 * @return Collision object stored in the rightmost node of subtree  
	 */
	private Collision getPredecessor(Node subtree) {

		//should not happen, but if it does throw a NullPointerException
		if (subtree.left==null) {
			throw new NullPointerException("getPredecessor called with an empty subtree");
		}

		//finds predecessor by finding the right-most node in the left subtree
		Node temp = subtree.left;
		while (temp.right  != null) {
			temp = temp.right ;
		}
		return temp.data;
	}

	/**
	 * Produces tree like String representation of this AVL tree.
	 *
	 * @return String containing tree-like representation of this AVL tree.
	 */
	public String toStringTreeFormat() {

		//create a StringBuilder object to store the long String
		StringBuilder s = new StringBuilder();
		preOrderPrint(root, 0, s);
		return s.toString();
	}

	/**
	 * Actual recursive implementation of preorder traversal to produce tree-like String
	 * representation of this tree.
	 * 
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree to
	 *   determine the indentation of each item
	 * @param output the String that accumulated the String representation of this
	 *   AVL tree
	 */
	private void preOrderPrint(Node tree, int level, StringBuilder s) {

		//if the tree is not null, recursively call the method until tree reaches null
		//if tree is null, print an empty tree
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++) {
					spaces += "   ";
				} spaces += "|--";
			}
			s.append(spaces);
			s.append(tree.data);
			preOrderPrint(tree.left, level + 1, s);
			preOrderPrint(tree.right, level + 1, s);
		} else {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++) {
					spaces += "   ";
				} spaces += "|--";
			}
			s.append(spaces);
			s.append("null");
		}
	}

	/**
	 * This method is responsible for properly displaying the report of collisions
	 * 
	 * @param String zip code
	 * @param Date start date
	 * @param Date end date
	 * @return formatted String of report
	 */
	public String getReport(String zip, Date dateBegin, Date dateEnd) {

		//arraylist that stores all the collision objects with the same zip and within the date range
		ArrayList<Collision> collisions=new ArrayList<Collision>();
		findCollisions(zip, dateBegin, dateEnd, root, collisions);

		//initializes necessary variables
		int personsF=0;
		int pedestriansF=0;
		int cyclistsF=0;
		int motoristsF=0;
		int personsI=0;
		int pedestriansI=0;
		int cyclistsI=0;
		int motoristsI=0;

		//sum info from each Collision object within the right date ranges and zipcode
		for (int i=0; i<collisions.size(); i++) {
			personsF+=collisions.get(i).getPersonsKilled();
			pedestriansF+=collisions.get(i).getPedestriansKilled();
			cyclistsF+=collisions.get(i).getCyclistsKilled();
			motoristsF+=collisions.get(i).getMotoristsKilled();
			personsI+=collisions.get(i).getPersonsInjured();
			pedestriansI+=collisions.get(i).getPedestriansInjured();
			cyclistsI+=collisions.get(i).getCyclistsInjured();
			motoristsI+=collisions.get(i).getMotoristsInjured();

		}

		//create a StringBuilder object to store the long String
		StringBuilder rep=new StringBuilder();
		rep.append("\nMotor Vehicle Collisions for zipcode "+zip+" ("+dateBegin.toString()+" - "+dateEnd.toString()+")\n"+"====================================================================\n");
		rep.append("Total number of collisions: "+collisions.size()+"\n");
		rep.append("Number of fatalities: "+personsF+"\n");
		rep.append(String.format("%22s", "pedestrians: "));
		rep.append(pedestriansF+"\n");
		rep.append(String.format("%22s", "cyclists: "));
		rep.append(cyclistsF+"\n");
		rep.append(String.format("%22s", "motorists: "));
		rep.append(motoristsF+"\n");
		rep.append("Number of injuries: "+personsI+"\n");
		rep.append(String.format("%20s", "pedestrians: "));
		rep.append(pedestriansI+"\n");
		rep.append(String.format("%20s", "cyclists: "));
		rep.append(cyclistsI+"\n");
		rep.append(String.format("%20s", "motorists: "));
		rep.append(motoristsI);

		//catches any exceptions returning the StringBuilder may throw
		try {
			return rep.toString();
		} catch (Exception x) {
			return x.getMessage();
		}
	}

	/**
	 * searches for first node where zip appears (similar to BST tree search)
	 * 
	 * @param String zip code
	 * @param Date start date
	 * @param Date end date
	 * @param collisions ArrayList of Collision objects
	 * @return formatted String of report
	 */
	private void findCollisions(String zip, Date dateBegin, Date dateEnd, Node n, ArrayList<Collision> collisions) {

		//base case 1
		if (n==null || dateBegin==null || dateEnd==null || collisions==null) {
			return;
		}

		//base case 2
		if (Integer.parseInt(zip)==Integer.parseInt(n.data.getZip())) {

			//we are in the correct zip code, so check dates
			if (dateBegin.compareTo(n.data.getDate())<=0 && dateEnd.compareTo(n.data.getDate())>=0) {
				collisions.add(n.data);
			} else if (dateBegin.compareTo(n.data.getDate())>0) {
				findCollisions(zip, dateBegin, dateEnd, n.left, collisions);
			} else {
				findCollisions(zip, dateBegin, dateEnd, n.right, collisions);
			} 
		} 

		//checks left and right if the zip is the same
		if (Integer.parseInt(zip)>Integer.parseInt(n.data.getZip())) { 
			findCollisions(zip, dateBegin, dateEnd, n.right, collisions); 
		} else if (Integer.parseInt(zip)<Integer.parseInt(n.data.getZip())) {
			findCollisions(zip, dateBegin, dateEnd, n.left, collisions);
		} else {
			findCollisions(zip, dateBegin, dateEnd, n.left, collisions);
			findCollisions(zip, dateBegin, dateEnd, n.right, collisions);
		}
	}

	/**
	 * Updates height of the specified node
	 * 
	 * @param Node node
	 */
	private void updateHeight(Node node) {

		if (node.left==null && node.right==null) { //if node is a leaf
			node.height=0;
		} else if (node.left==null) { //if node has one right child
			node.height=node.right.height+1;
		} else if (node.right==null) { //if node has left one child
			node.height=node.left.height+1;
		} else { //if node has two children
			node.height=Math.max(node.right.height, node.left.height)+1;
		}
	}

	/**
	 * Calculates the balance factor of the specified node
	 * 
	 * @param Node node
	 * @return int that is the balance factor
	 */
	private int balanceFactor(Node node) {

		//calculates balance factor based on node's children (if any)
		if (node.right==null) {
			return -node.height;
		} if (node.left==null) {
			return node.height;
		} return node.right.height-node.left.height;
	}

	/**
	 * Balances imbalanced node
	 * 
	 * @param Node node
	 * @return balanced node
	 */
	private Node balance(Node node) {

		//if node is empty, return null (shouldn't be called on a null)
		if (node==null) {
			return null;
		}

		//balances nodes respectively based on balanceFactor
		if (balanceFactor(node)<-1) {
			if (balanceFactor(node.left)<=0) {
				node=balanceLL(node);
			} else {
				node=balanceLR(node);
			}
		} else if (balanceFactor(node)>1) {
			if (balanceFactor(node.right)>=0) {
				node=balanceRR(node);
			} else { 
				node=balanceRL(node);
			}
		} 

		//updates height of node and returns it
		updateHeight(node);
		return node;
	}

	/**
	 * Left-left rotation of a node
	 * 
	 * @param Node A
	 * @return Node B
	 */
	private Node balanceLL(Node A) {

		//LL rotation
		Node B=A.left;
		A.left=B.right;
		B.right=A;

		//update height for both nodes and return Node B
		updateHeight(A);
		updateHeight(B);
		return B;
	}

	/**
	 * Right-right rotation of a node
	 * 
	 * @param Node A
	 * @return Node B
	 */
	private Node balanceRR(Node A) {

		//RR rotation
		Node B=A.right;
		A.right=B.left;
		B.left=A;

		//update height for both nodes and return Node B
		updateHeight(A);
		updateHeight(B);
		return B;
	}

	/**
	 * Left-right rotation of a node
	 * 
	 * @param Node A
	 * @return Node C
	 */
	private Node balanceLR(Node A) {

		//LR rotation 
		Node B=A.left;
		Node C=B.right;
		A.left=C.right;
		B.right=C.left;
		C.left=B;
		C.right=A;

		//update heights for all nodes and return Node C
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		return C;
	}

	/**
	 * Right-left rotation of a node
	 * 
	 * @param Node A
	 * @return Node C
	 */
	private Node balanceRL(Node A) {

		//RL rotation
		Node B=A.right;
		Node C=B.left;
		A.right=C.left;
		B.left=C.right;
		C.right=B;
		C.left=A;

		//update heights for all nodes and return C
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		return C;
	}

	/**
	 * Node class is used to represent nodes in an AVL tree.
	 * It contains a Collision data item and references to left and right subtrees. 
	 * 
	 * @author Joanna Klukowska
	 */
	protected static class Node {
		protected Node left; //reference to left subtree
		protected Node right; // reference to right subtree
		protected Collision data; //Collision data stored in node
		protected int height; //height of node

		/**
		 * Constructs a Node initializing the Collision data part 
		 * according to the parameter and setting both 
		 * references to subtrees to null.
		 * 
		 * @param Collision object to be stored in the node
		 */
		protected Node(Collision data) {
			this.data=data;
			left=null;
			right=null;
			height=0; 
		} 
	}
}