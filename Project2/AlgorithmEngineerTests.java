import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.zip.DataFormatException;
import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {	
	/**
	 * Testing for a valid RBT after removing the black root node
	 */
	@Test
    public void testDeletingBlackRootNode() {
		AE_RedBlackTree <Integer> tree = new AE_RedBlackTree<Integer>();
		tree.insert(20);
		tree.insert(25); //Right subtree
		tree.insert(15); 
		tree.insert(23); 
		tree.insert(30); //Left subtree
		tree.insert(10); 
		tree.insert(17);
		
		assertAll("This test should ensure the successfull removal of the black root node (20) "
				+ "while maintaining valid RBT properties", 
				() -> assertEquals(true, tree.remove(20)),
				() -> assertEquals(false, tree.contains(20)), //Tests if RBT contains deleted node (20)
				() -> assertEquals(1, tree.root.blackHeight), //Tests root is black
				() -> assertEquals(23, tree.root.data), // Tests root is successor 23
				() -> assertEquals(25, tree.root.rightChild.data), // Tests root's right child is 25
				() -> assertEquals(1, tree.root.rightChild.blackHeight), // Tests 25 is black node
				() -> assertEquals(15, tree.root.leftChild.data), // Tests root's left child is 15
				() -> assertEquals(1, tree.root.leftChild.blackHeight)); //Tests 15 is black node
	}
	
	/**
	 * Testing for valid RBT after removing black node with red sibling (case 2)
	 */
	@Test
	public void redSiblingCase() {
		
		//Here we will set up a case for removing a black node with red sibling 
		AE_RedBlackTree<Integer> tree = new AE_RedBlackTree<Integer>();
		tree.insert(20);
		tree.insert(15); 
		tree.insert(25); 
		tree.root.rightChild.blackHeight = 1; //Recolor 25 -> black
		tree.root.leftChild.blackHeight = 1; //Recolor 15 -> black
		tree.insert(10); 
		tree.insert(17);
		tree.root.leftChild.rightChild.blackHeight = 1; //Recolor 17 -> black
		tree.root.leftChild.leftChild.blackHeight = 1; //Recolor 10 -> black 
		tree.root.leftChild.blackHeight = 0; //Recolor 15 - > red
		
		assertAll("This test should ensure the successfull removal of the black node (25) "
				+ "with a red sibling (15) while maintaining valid RBT properties", 
				() -> assertEquals(true, tree.remove(25)),
				() -> assertEquals(false, tree.contains(25)),
				() -> assertEquals(15, tree.root.data),
				() -> assertEquals(10, tree.root.leftChild.data),
				() -> assertEquals(20, tree.root.rightChild.data),
				() -> assertEquals("[ 10, 15, 17, 20 ]", tree.toInOrderString()));
	} 
	
	/**
	 * Testing for a valid RBT after removing black node who's black sibling has two children 
	 */
	@Test
	public void blackSiblingWithTwoBlackChildren() {
		AE_RedBlackTree<Integer> tree = new AE_RedBlackTree<Integer>();
		tree.insert(17);
		tree.insert(9);
		tree.insert(19); 
		tree.insert(18);
		tree.insert(25);
				
		assertAll("This test should ensure the successfull removal of the black node (25) "
				+ "who's black sibling (18) has 2 black (null) children while maintaining valid RBT properties", 
				() -> assertEquals(true, tree.remove(25)), //Tests if remove() function returns true
				() -> assertEquals(false, tree.contains(25)), //Tests if RBT contains removed node
				//The following Tests the validity of the RBT in terms of black height 
				() -> assertEquals(1, tree.root.blackHeight),
				() -> assertEquals(1, tree.root.leftChild.blackHeight),
				() -> assertEquals(1, tree.root.rightChild.blackHeight),
				() -> assertEquals(0, tree.root.rightChild.leftChild.blackHeight),
				() -> assertEquals("[ 9, 17, 18, 19 ]", tree.toInOrderString()));
	} 

	/**
	 * Testing for valid RBT after removing black node who's sibling has at least 1 red child
	 * and it's other child is black and on opposite sides of the deleted node
	 */
	@Test
	public void blackSiblingWithOneRedChildAndOneOuterNephew() {
		AE_RedBlackTree<Integer> tree = new AE_RedBlackTree<Integer>();
		tree.insert(17);
		tree.insert(9);
		tree.insert(19);
		tree.insert(18);
		tree.insert(75);
		tree.insert(80);

		assertAll("This test should ensure the successfull removal of the black node (18) "
				+ "who's black sibling (75) has 1 black child opposite the deleted black node, as well as"
				+ "a red child. This test ensures the maintaining of valid RBT properties", 
				() -> assertEquals(true, tree.remove(18)), //Tests if remove() returns true
				() -> assertEquals(false, tree.contains(18)), //Tests if RBT contains removed node
				//Ensures the validity of the RBT tree in its black height
				() -> assertEquals(1, tree.root.blackHeight),
				() -> assertEquals(1, tree.root.leftChild.blackHeight),
				() -> assertEquals(1, tree.root.rightChild.leftChild.blackHeight),
				() -> assertEquals("[ 9, 17, 19, 75, 80 ]", tree.toInOrderString())); 
	
	}

	/**
	 * Tests whether a valid phone number returns true
	 */
	@Test
	public void testValidatorOnValidNumber() {
		AE_Validator valid = new AE_Validator();
		String validNumber = "2629553942";
		try {
			assertEquals(true, valid.isValid(validNumber));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests whether an invalid phone number returns false
	 */
	@Test
	public void testValidatorOnInvalidNumber() {
		AE_Validator valid = new AE_Validator();
		String invalidNumber = "2621553942"; //N < 2 in NXXNXXXXXX
		try {
			assertEquals(false, valid.isValid(invalidNumber));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests that an invalid format throws a DataFormatException
	 */
	@Test
	public void testInvalidNumberThrowsException() {
		AE_Validator valid = new AE_Validator();
		String illFormatted = "262955394i";
		assertThrows(DataFormatException.class, () -> valid.isValid(illFormatted));
	}

	/**
	 * Tests the backend implementation of addContact()  
	 */
	@Test
	public void integrationTest1_TestAddContact() {
		AE_RedBlackTree<IPhoneBook> tree = new AE_RedBlackTree<IPhoneBook>(); //RBT for testing purposes 
		Backend back = new Backend(tree); //Create instance of Backend
		back.addContact("James", "2629081324"); //Add contact
		Iterator<IPhoneBook> iter = tree.iterator(); //Create iterator
		IPhoneBook contact1 = iter.next(); //Get first contact 
		
		assertAll ("This test ensures that a contact has been correctly added to the tree", 
				() -> assertEquals(1, tree.size()),
				() -> assertEquals("James", contact1.getName()),
				() -> assertEquals("2629081324" , contact1.getPhoneNumber()));
	}

	/**
	 * Tests the backend implementation of removeContact()
	 */
	@Test
	public void integrationTest2_TestRemoveContact() {
		AE_RedBlackTree<IPhoneBook> tree = new AE_RedBlackTree<IPhoneBook>(); //RBT for testing purposes 
		Backend back = new Backend(tree); //Create instance of Backend
		//Add contacts
		back.addContact("James", "2629081324");
		back.addContact("Riley", "4085903456");
		Iterator<IPhoneBook> iter = tree.iterator(); //Create iterator
		IPhoneBook contact1 = iter.next(); //Create contact to place into tree 
		assertAll ("This test ensures that a contact has been correctly removed from the tree", 
				() -> assertEquals(2, tree.size()), //Make sure there are 2 contacts before removing
				() -> assertEquals(true, tree.contains(contact1)),//Remove contact
				() -> assertEquals(true, back.removeContact("James", "2629081324"))); //Remove Contact)); 
	}

	/**
	 * Tests the backend functionality of validator for invalid phone number
	 */
	@Test
	public void invalidValidator_CodeReviewOfBackendDeveloper() {
		Backend back = new Backend();
		String invalidNumber = "o120567498";
		assertThrows(DataFormatException.class, () -> back.validator(invalidNumber));
		
		String invalid = "1200267498";
		try {
			assertEquals(false, back.validator(invalid));
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests the backend functionality of validator for invalid phone number
	 */
	@Test
	public void validValidator_CodeReviewOfBackendDeveloper() {
		Backend back = new Backend();
		String valid = "5124567498";
		try {
			assertEquals(true, back.validator(valid));
		} catch (DataFormatException e) {
			e.printStackTrace();
			//fail();
		}
	}

}
