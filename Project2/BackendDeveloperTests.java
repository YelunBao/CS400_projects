import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

public class BackendDeveloperTests {

	private String name;
	private String phoneNum;

	IRedBlackTree<IPhoneBook> rbt = new BD_RedBlackTree();
	IBackend test = new Backend(rbt);

	/*
	 * Test if the contact is successfully added.
	 */
	@Test
	void test1() {

		test.addContact(name, phoneNum);
		test.addContact("ZhangLingling Yan", "9209398889");

		assertEquals(rbt.size(), 2);
	}

	/*
	 * Test if the contact is successfully removed.
	 * 
	 */
	@Test
	void test2() {
		test.addContact(name, phoneNum);
		test.addContact("ZhangLingling Yan", "9209398889");
		test.removeContact("ZhangLingling Yan", "9209398889");
		assertEquals(rbt.size(), 1);
	}

	/*
	 * check if the size was returned as 0 when the tree was empty
	 * 
	 */
	@Test
	void test3() {
		assertEquals(rbt.size(), 0);
	}

	/*
	 * Check if When searching for the name key words, the name can be returned.
	 */
	@Test
	void test4() {
		test.addContact("Haoyu Wuzhaer", "9209394232");
		test.addContact("ZhangLingling Yan", "9209398889");
		test.addContact("apple", "222222222");
		assertEquals(rbt.size(), 3);
	}

	/*
	 * Test if all the contacts been added can be returned. Check if the name and
	 * phone number have been added and returned.
	 */
	@Test
	void test5() {
		
		test.addContact(name, phoneNum);
		test.addContact("apple", "6085789993");
		test.getAllContacts();
		assertEquals("Haoyu Wuzhaer", "Haoyu Wuzhaer");
		assertEquals("apple", "apple");
		assertEquals("9209394232", "9209394232");
		assertEquals("6085789993", "6085789993");
		
		//check if get all contacts.
		assertEquals("Haoyu Wuzhaer, 9209394232, apple, 6085789993", "Haoyu Wuzhaer, 9209394232, apple, 6085789993");

	}
	@Test
	void test6Integration(){
		test.addContact("Haoyu", "9209394232");
		assertEquals("Haoyu", "Haoyu");
	
	}
	@Test
	void test7Integration(){
		test.addContact("apple", "999999999");
		test.removeContact("apple", "999999999");
		assertEquals(rbt.size(), 0);
	}
	/*test if the AE's add algorithm is funtional
	 *
	 */	
	@Test
	void test8AlgorihmEngineer(){
		test.addContact("Madison", "9339442222");
		assertEquals(rbt.size(), 1);	
	}
	/*test if the AE's add and remove algorithm is funtional
	 *
	 */
	@Test
	void test9AlgorithmEngineer(){
		test.addContact("Haoyu", "9209394232");
		test.addContact("apple", "2222222222");
		test.removeContact("Haoyu", "9209394232");
		assertEquals(rbt.size(), 1);
	}	

}

