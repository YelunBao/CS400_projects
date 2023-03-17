/*
 * This interface implements the remove function
 */
public interface IRedBlackTree<T extends Comparable<T>> extends ISortedCollection<T>, Iterable<T>{

	/**
	 * This method removes an element from the Red Black Tree, and balances the tree if necessary
	 * @param data the element to be removed
	 * @return true if the data is in the tree and was removed, false if otherwise
	 */
	public boolean remove(T data);
	
}
