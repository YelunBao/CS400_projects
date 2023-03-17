import java.util.Iterator;
import java.util.LinkedList;

public class AE_RedBlackTree<T extends Comparable<T>> implements IRedBlackTree<T> {

	 protected Node<T> root; // reference to root node of tree, null when empty
	 protected int size = 0; // the number of values in the tree
	
	/**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public int blackHeight; // The black height only for current node 
        						// 0 = red, 1 = back, 2 = double-black
        
        public Node(T data) { 
        	this.blackHeight = 0;
        	this.data = data; 
        }
        
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return parent != null && parent.rightChild == this;
        }
    }
    
	/**
	 * An iterator to Iterate over the Red Black Tree
	 * 
	 * @author Nathaniel Owusu
	 */
	private class RBTIterator implements Iterator<T> {

		Node<T> current = root;
		Node<T> rightMost = null;
		
		/**
		 * Determines if the current node has a child
		 * @returns true if the current node has a child, false if otherwise
		 */
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		/**
		 * Obtains the next node in the Red Black Tree
		 * @return the node's data
		 */
		@Override
		public T next() {

			if (current.leftChild == null) {
				Node<T> temp = current;
				current = current.rightChild;
				return temp.data;
			}

			rightMost = current.leftChild;

			while (rightMost.rightChild != null && rightMost.rightChild != current) {
				rightMost = rightMost.rightChild;
			}
			
			if (rightMost.rightChild == null) {
				rightMost.rightChild = current;
				current = current.leftChild;
			} else {
				rightMost.rightChild = null;
				Node<T> temp = current;
				current = current.rightChild;
				return temp.data;
			}
			
			return next();
		}
	}
	
	/**
	 * Performs a naive insertion into a binary search tree: adding the input data
	 * value to a new node in a leaf position within the tree. After this insertion,
	 * no attempt is made to restructure or balance the tree. This tree will not
	 * hold null references, nor duplicate data values.
	 * 
	 * @param data to be added into this binary search tree
	 * @return true if the value was inserted, false if not
	 * @throws NullPointerException     when the provided data argument is null
	 * @throws IllegalArgumentException when the newNode and subtree contain equal
	 *                                  data references
	 */
	@Override
	public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
		// null references cannot be stored within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		Node<T> newNode = new Node<>(data);
		if (root == null) {
			root = newNode;
			size++;
			return true;
		} // add first node to an empty tree
		else {
			boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
			if (returnValue)
				size++;
			else
				throw new IllegalArgumentException("This RedBlackTree already contains that value.");
			root.blackHeight = 1;
			return returnValue;
		}
	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newNode should be inserted, and then extend this tree by
	 * the newNode in that position.
	 * 
	 * @param newNode is the new node that is being added to this tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descenedent beneath
	 * @return true is the value was inserted in subtree, false if not
	 */
	private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
		int compare = newNode.data.compareTo(subtree.data);
		// do not allow duplicate values to be stored within this tree
		if (compare == 0)
			return false;

		// store newNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newNode;
				newNode.parent = subtree;
				root.blackHeight = 1;
				enforceRBTreePropertiesAfterInsert(newNode, subtree);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.leftChild);
		}

		// store newNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newNode;
				newNode.parent = subtree;
				root.blackHeight = 1;
				enforceRBTreePropertiesAfterInsert(newNode, subtree);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.rightChild);
		}
	}

	/**
	 * This method enforces the properties that entail a Red Black Tree after an
	 * insertion
	 * 
	 * @param newNode a newly added node to the tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descendant beneath
	 */
	public void enforceRBTreePropertiesAfterInsert(Node<T> newNode, Node<T> subtree) {

		// If subtree is black, then Red Black Tree is valid //TODO check if correct
		if (subtree.blackHeight == 1) {
			return;
		}

		// Case 1: Parent sibling is black and child of red parent is on opposite sides
		// of black sibling
		if (!newNode.isLeftChild() && subtree.parent != null) { // Case 1 violation on the right subtree --> Left
																// rotation
			if (subtree.parent.leftChild == null || subtree.parent.leftChild.blackHeight == 1) { // If black sibling of
																									// the parent is on
																									// the
																									// opposite(left)
																									// side of the
																									// inserted node
				// Swap colors of the grandparent and parent nodes
				int tempColor = subtree.parent.blackHeight;
				subtree.parent.blackHeight = subtree.blackHeight;
				subtree.blackHeight = tempColor;
				rotate(subtree, subtree.parent); // Rotate the grandparent and the parent to the left
				return;
			}

		} else if (newNode.isLeftChild() && subtree.parent != null) { // Case 1 violation on the left subtree --> Right
																		// rotation
			if (subtree.parent.rightChild == null || subtree.parent.rightChild.blackHeight == 1) { // If black sibling
																									// of the parent is
																									// on the
																									// opposite(right)
																									// side of the
																									// inserted node
				// Swap colors of the grandparent and parent nodes
				int tempColor = subtree.parent.blackHeight;
				subtree.parent.blackHeight = subtree.blackHeight;
				subtree.blackHeight = tempColor;
				rotate(subtree, subtree.parent); // Rotate the grandparent and the parent to the right
				return;
			}
		}

		// Case 2: Parent sibling is black and the child of red parent is on the same
		// side of black sibling
		// Here, we rotate the child to be inserted and the parent to recreate a Case 1
		// scenario
		if (!newNode.isLeftChild()) {
			if (subtree.parent.rightChild == null || subtree.parent.rightChild.blackHeight == 1) { // If black sibling
																									// of parent is on
																									// the same side,
																									// rotate the
																									// inserted node and
																									// its parent node
				rotate(newNode, subtree);
				// Swap colors of the grandparent and parent nodes
				int tempColor = newNode.parent.blackHeight;
				newNode.parent.blackHeight = newNode.blackHeight;
				newNode.blackHeight = tempColor;
				rotate(newNode, newNode.parent); // the new node becomes the left child of the subtree root, so rotate
													// the new node and its parent
				return;
			}
		} else if (newNode.isLeftChild()) { 
			if (subtree.parent.leftChild == null || subtree.parent.leftChild.blackHeight == 1) { // If black sibling of
																									// parent is on the
																									// same side, rotate
																									// the inserted node
																									// and its parent
																									// node
				rotate(newNode, subtree);
				// Swap colors of the grandparent and parent nodes
				int tempColor = newNode.parent.blackHeight;
				subtree.parent.blackHeight = newNode.blackHeight;
				newNode.blackHeight = tempColor;
				rotate(newNode, newNode.parent); // the new node becomes the right child of the subtree root, so rotate
													// the new node and its parent
				return;
			}
		}

		// Case 3: Parent and the sibling are both red
		if (subtree.parent.leftChild != null && subtree.parent.leftChild.blackHeight == 0) { // Inserted child is on
																								// right subtree
			subtree.parent.blackHeight = 0;
			subtree.blackHeight = 1;
			subtree.parent.leftChild.blackHeight = 1;
			if (subtree.parent.parent == null) {
				subtree.parent.blackHeight = 1;
			}
			return;
		}

		else if (subtree.parent.rightChild != null && subtree.parent.rightChild.blackHeight == 0) { // Inserted child is
																									// on left subtree
			subtree.parent.blackHeight = 0;
			subtree.blackHeight = 1;
			subtree.parent.rightChild.blackHeight = 1;
			if (subtree.parent.parent == null) {
				subtree.parent.blackHeight = 1;
			}
			return;
		}
	}

	/**
	 * Performs the rotation operation on the provided nodes within this tree. When
	 * the provided child is a leftChild of the provided parent, this method will
	 * perform a right rotation. When the provided child is a rightChild of the
	 * provided parent, this method will perform a left rotation. When the provided
	 * nodes are not related in one of these ways, this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param child  is the node being rotated from child to parent position
	 *               (between these two node arguments)
	 * @param parent is the node being rotated from parent to child position
	 *               (between these two node arguments)
	 * @throws IllegalArgumentException when the provided child and parent node
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {

		boolean isLeftChild = false;
		boolean canRotate = false;

		if (child.isLeftChild()) {
			isLeftChild = true;
			canRotate = true;
		}
		if (child.isRightChild()) {
			isLeftChild = false;
			canRotate = true;
		}

		if (!canRotate) {
			throw new IllegalArgumentException();
		}

		if (isLeftChild) {
			rotateRightHelper(child, parent);
		} else {
			rotateLeftHelper(child, parent);
		}
	}

	/**
	 * A helper method to help the RBT rotate right
	 * 
	 * @param child  is the node being rotated from child to parent position
	 *               (between these two node arguments)
	 * @param parent is the node being rotated from parent to child position
	 *               (between these two node arguments)
	 */
	private void rotateRightHelper(Node<T> child, Node<T> parent) {

		parent.leftChild = child.rightChild;
		child.parent = parent.parent;
		if (child.rightChild != null) {
			child.rightChild.parent = parent;
		}

		// determine if the root needs to be changed, parent node is at the root
		if (parent.parent == null) {
			root = child;
		}

		else if (parent.parent.rightChild == parent) {
			parent.parent.rightChild = child;
		} else {
			parent.parent.leftChild = child;
		}

		parent.parent = child;
		child.rightChild = parent;
	}

	/**
	 * A helper method to help the RBT rotate left
	 * 
	 * @param child  is the node being rotated from child to parent position
	 *               (between these two node arguments)
	 * @param parent is the node being rotated from parent to child position
	 *               (between these two node arguments)
	 */
	private void rotateLeftHelper(Node<T> child, Node<T> parent) {

		parent.rightChild = child.leftChild;
		child.parent = parent.parent;
		if (child.leftChild != null) {
			child.leftChild.parent = parent;
		}

		// determine if the root needs to be changed, parent node is at the root
		if (parent.parent == null) {
			root = child;
		}

		else if (parent.parent.leftChild == parent) {
			parent.parent.leftChild = child;
		} else {
			parent.parent.rightChild = child;
		}

		parent.parent = child;
		child.leftChild = parent;

	}
	 
	/**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
	@Override
	public boolean contains(T data) {
		// null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);	
	}
	
	/**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
	@Override
	public int size() {
		return size;
	}

	/**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Returns an iterator to iterate over this RedBlackTree
	 * @return an Iterator 
	 */
	@Override
	public Iterator<T> iterator() {
		Iterator<T> iterator = (Iterator<T>) new RBTIterator();

		return iterator;
	}

	/**
	 * This method removes a node from the Red Black Tree
	 * @param data the data to be removed
	 * @return true if successfully removed, false if otherwise 
	 */
	@Override
	public boolean remove(T data) {

		Node<T> node = root;
		
		//Find node to be deleted
		while (node != null && node.data != data) {
			if (data.compareTo(node.data) < 0) {
				node = node.leftChild;
			} else {
				node = node.rightChild;
			}
		}
		
		//If node isnt found return false
		if (node == null) {
			return false;
		}
		
		Node<T> movedUpNode;
		int deletedNodeColor;
		
		//Node has 0 or 1 child
		if  (node.leftChild == null || node.rightChild == null) {
			deletedNodeColor = node.blackHeight; 
			movedUpNode = removeOneOrMoreChildren(node);
		}
		
		//Node has 2 children
		else {
			//Find successor and copy to current node
			Node<T> successor = findSuccessor(node.rightChild);
			node.data = successor.data;
			//Delete the successor (will only have 1 or 0 children) 
			movedUpNode = removeOneOrMoreChildren(successor);
			deletedNodeColor = successor.blackHeight;
		}
		//If we delete a black node, we must fix the unbalanced black height in the RBT
		if (deletedNodeColor == 1) {
			enforceRemoveProperties(movedUpNode);
			
			//Remove temporary double black node 
			if (movedUpNode.blackHeight == 2) {
				replaceParentsChild(movedUpNode.parent, movedUpNode, null);
			}
		}
		return true;
	}
	
	/**
	 * Helper method for deleting a node with either zero children or one child
	 * @param node node to be deleted
	 * @return the node that moves up to replace the deleted node
	 */
	private Node<T> removeOneOrMoreChildren(Node<T> node) {
		
		//Node has one left child --> Replace by left child
		if(node.leftChild != null) {
			replaceParentsChild(node.parent, node, node.leftChild);
			return node.leftChild;
		}
		
		//Node has one right child --> Replace by right child
		else if (node.rightChild != null) {
			replaceParentsChild(node.parent, node, node.rightChild);
			return node.rightChild;
		}
		
		//Node has no children
		else {
			Node<T> doubleBlack = node;
			//If node is black, create temporary double-black null node 
			if (node.blackHeight == 1) {
				doubleBlack.blackHeight = 2;
			//If node is red just delete node 
			} else {
				doubleBlack = null;
			}
			replaceParentsChild(node.parent, node, doubleBlack); 
			return doubleBlack;
		}
	}
	
	/**
	 * Finds the successor of the node
	 * @param node the node to find the successor of
	 * @return the successor
	 */
	private Node<T> findSuccessor(Node<T> node) {
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		return node;
	}
	
	/**
	 * This method fixes all violations that occur after a remove function
	 * @param node node that has been moved up
	 */
	private void enforceRemoveProperties(Node<T> node) {
		
		//Case 1: Node is the root (Change to black)
		if (node == root) {
			node.blackHeight = 1;
			return;
		}
		
		Node<T> sibling = getSibling(node);
		
		//Case 2: Red Sibling
		if (sibling.blackHeight == 0) {
			redSiblingCase(node, sibling);
			sibling = getSibling(node); // Get new sibling
			return;
		}
		
		//Case 5/6: Black sibling with at least one red child
		if (((sibling.rightChild != null && sibling.rightChild.blackHeight == 0) || 
				(sibling.leftChild != null && sibling.leftChild.blackHeight == 0)) &&
				(sibling.rightChild == null || sibling.rightChild.blackHeight == 1 || 
				sibling.leftChild == null || sibling.leftChild.blackHeight == 1)) {
			blackSiblingRedChild(node,sibling);
		}
		
		// Case 3/4: Black sibling with 2 black children
		if ((sibling.leftChild == null && sibling.rightChild == null) || 
				sibling.leftChild.blackHeight == 1 && sibling.rightChild.blackHeight == 1) {
			sibling.blackHeight = 0; // Recolor the sibling red

			// Case 3: Black sibling w/2 black children --> Red Parent
			if (node.parent.blackHeight == 0) {
				node.parent.blackHeight = 1; // Recolor the parent black to maintain black height
			}
			// Case 4:
			else {
				// Recursively call method on parent node to turn back into a case 3
				enforceRemoveProperties(node.parent);
			}
		} 
	}
	
	/**
	 * Gets the sibling of the node 
	 * 
	 * @param node the node to get the sibling of 
	 * @return the sibling of the node 
	 */
	private Node<T> getSibling(Node<T> node) {
		
		if (node.isLeftChild()) {
			return node.parent.rightChild;
		} else if (node.isRightChild()) {
			return node.parent.leftChild;
		} else {
			throw new IllegalStateException("Parent isnt child of grandparent");
		}
	}
	
	/**
	 * Handles Case 2: A red sibling 
	 * @param node the node to be handled
	 * @param sibling the sibling of the node
	 */
	private void redSiblingCase(Node<T> node, Node<T> sibling) {
		//Step 1: recolor sibling black and the parent red
		sibling.blackHeight = 1; 
		node.parent.blackHeight = 0;
		//Step 2: Rotate parent and sibling towards deleted node 
		if (node.isLeftChild()) {
			rotateLeftHelper(sibling, node.parent);
		} else {
			rotateRightHelper(sibling, node.parent);
		}
	}
	
	/**
	 * Handles Case 5/6: Black sibling with at least one red child
	 * @param node the node to be handled
	 * @param sibling the sibling of the node
	 */
	private void blackSiblingRedChild(Node<T> node, Node<T> sibling) {
		// Case 5: Sibling is black with at least 1 red child and the outer nephew is
		// black
		if (node.isLeftChild() && (sibling.rightChild == null || sibling.rightChild.blackHeight == 1)) {
			sibling.leftChild.blackHeight = 1; // Color the inner nephew black
			sibling.blackHeight = 0; // Color the sibling red
			rotateRightHelper(sibling.leftChild, sibling); // Rotate sibling and inner nephew right
			sibling = node.parent.rightChild; // New sibling
			return;
		} else if (node.isRightChild() && (sibling.leftChild == null || sibling.leftChild.blackHeight == 1)) {
			sibling.rightChild.blackHeight = 1; // Color the inner nephew black
			sibling.blackHeight = 0; // Color sibling red
			rotateLeftHelper(sibling.rightChild, sibling); // Rotate sibling and inner nephew left
			sibling = node.parent.leftChild; // New sibling
			return;
		}

		// Case 6: Black sibling with at least one red child and outer nephew is red
		sibling.blackHeight = sibling.parent.blackHeight; // Recolor sibling to parent's color
		sibling.parent.blackHeight = 1; // Recolor parent to black
		if (node.isLeftChild()) { // If node is left child, rotate left
			sibling.rightChild.blackHeight = 1; // Recolor outer nephew black
			rotateLeftHelper(sibling, sibling.parent);
		} else {
			sibling.leftChild.blackHeight = 1;
			rotateRightHelper(sibling, sibling.parent);
		}
	}

	/**
	 * Helper method to replace a deleted node with one child with its child
	 * 
	 * @param parent parent of deleted node
	 * @param oldChild node to be deleted
	 * @param newChild child (left or right) of old child
	 */
	private void replaceParentsChild(Node<T> parent, Node<T> oldChild, Node<T> newChild) {
		if (parent == null) {
			root = newChild;
		} else if (parent.leftChild == oldChild) {
			parent.leftChild = newChild;
		} else if (parent.rightChild == oldChild) {
			parent.rightChild = newChild;
		} else {
			throw new IllegalStateException("Node isn't a child of its parent");
		}

		if (newChild != null) {
			newChild.parent = parent;
		}
	}
	
	/**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node){
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }
}
