public interface ISortedCollection<T extends Comparable<T>> extends Iterable<T>{

	/**
	 * Inserts an element into the Red Black Tree, balancing the tree if necessary 
	 * 
	 * @param data the element to be added
	 * @return true if element has been added, false if otherwise
	 * @throws NullPointerException if data is null
	 * @throws IllegalArgumentException when the newNode and subtree contain equal data references 
	 */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    /**
     * Checks if the Red Black Tree contains the element passed in as parameter
     * @param data the element to be tested for
     * @return true if Red Black Tree contains element, false if otherwise
     */
    public boolean contains(T data);

    /**
     * Returns the number of elements in the Red Black Tree
     * 
     * @return number of elements in Red Black Tree
     */
    public int size();

    /**
     * Checks to see if the Red Black Tree is empty 
     * 
     * @return true if there are no elements in the tree, false if otherwise
     */
    public boolean isEmpty();

}
