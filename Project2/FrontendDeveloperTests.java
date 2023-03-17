import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FrontendDeveloperTests {

    @Test
    void test1() throws FileNotFoundException {
	//tests add method
	BackendHelperFD back = new BackendHelperFD();
	File file = new File("test1");
	Scanner scnr = new Scanner(file);
	PhoneBookFrontend phone = new PhoneBookFrontend(scnr, back);
	phone.add();
	assertTrue(back.list.size() == 1);
    }

    @Test
    void test2() throws FileNotFoundException {
	//tests remove method, uses add method but we know it works if test1 passed
	BackendHelperFD back = new BackendHelperFD();
	File file = new File("test2");
	Scanner scnr = new Scanner(file);
	PhoneBookFrontend phone = new PhoneBookFrontend(scnr, back);
	phone.add();
	phone.remove();
	assertTrue(back.list.size() == 0);
    }

    @Test
    void test3() throws FileNotFoundException {
	//tests search method
	BackendHelperFD back = new BackendHelperFD();
	File file = new File("test3");
	Scanner scnr = new Scanner(file);
	PhoneBookFrontend phone = new PhoneBookFrontend(scnr, back);
	phone.add();
	phone.searchByName();
	List<IPhoneBook> list = back.search("billy bob");
	assertTrue(list.get(0).getName().equals("billy bob"));
    }

    @Test
    void test4() throws FileNotFoundException {
	//tests display contacts method when adding a contact, for testing purposes 2 contacts have been pre added
	BackendHelperFD back = new BackendHelperFD();
	File file = new File("test4");
	Scanner scnr = new Scanner(file);
	PhoneBookFrontend phone = new PhoneBookFrontend(scnr, back);
	phone.add();
	phone.displayContacts();
	List<IPhoneBook> list = back.search("billy bob");
	assertTrue(back.list.get(0).getName().equals("billy bob"));
    }

    @Test
    void test5() throws FileNotFoundException {
	//tests display contacts method, for testing purposes 2 contacts have been pre added
	BackendHelperFD back = new BackendHelperFD();
	File file = new File("test5");
	Scanner scnr = new Scanner(file);
	PhoneBookFrontend phone = new PhoneBookFrontend(scnr, back);
	phone.displayContacts();
	assertTrue(back.list.get(0).getName().equals("Bill"));
    }

    @Test
    void CodeReviewOfDataWrangler1() throws IOException {
	PhoneBookLoader loader = new PhoneBookLoader();
	List<IPhoneBook> contacts = loader.loadNames("phone_book.xml");
	String firstContactName = contacts.get(0).getName();
	String firstContactNumber = contacts.get(0).getPhoneNumber();

	assertTrue(firstContactName.equals("Bernard Bullock"));
	assertTrue(firstContactNumber.equals("2625066474"));
    }

    @Test
    void CodeReviewOfDataWrangler2() throws IOException {
	PhoneBookLoader loader = new PhoneBookLoader();
	List<IPhoneBook> contacts = loader.loadNames("phone_book.xml");
	String lastContactName = contacts.get(49).getName();
	String lastContactNumber = contacts.get(49).getPhoneNumber();

	assertTrue(lastContactName.equals("Hannah Hardin"));
	assertTrue(lastContactNumber.equals("7345860129"));

    }


    @Test
    void Integration1() throws FileNotFoundException, IOException{
	PhoneBookLoader loader = new PhoneBookLoader();
	List<IPhoneBook> phoneBook = loader.loadNames("phone_book.xml");

	AE_RedBlackTree<IPhoneBook> rbt = new AE_RedBlackTree<IPhoneBook>();
	Backend back = new Backend(rbt);

	for (IPhoneBook contacts: phoneBook) {
	    back.addContact(contacts.getName(), contacts.getPhoneNumber());
	}

	File file = new File("test1");

	Scanner scnr = new Scanner(file);

	IPhoneBookFrontend frontend = new PhoneBookFrontend(scnr, back);

	frontend.add();
	List<IPhoneBook> book = back.getAllContacts();

	assertTrue(book.size() == 51);

    }

    @Test
    void Integration2() throws FileNotFoundException, IOException {

	PhoneBookLoader loader = new PhoneBookLoader();
	List<IPhoneBook> phoneBook = loader.loadNames("phone_book.xml");

	AE_RedBlackTree<IPhoneBook> rbt = new AE_RedBlackTree<IPhoneBook>();
	Backend back = new Backend(rbt);

	for (IPhoneBook contacts: phoneBook) {
	    back.addContact(contacts.getName(), contacts.getPhoneNumber());
	}

	File file = new File("test2");

	Scanner scnr = new Scanner(file);

	IPhoneBookFrontend frontend = new PhoneBookFrontend(scnr, back);

	frontend.add();
	frontend.remove();

	List<IPhoneBook> book = back.getAllContacts();

	assertTrue(book.size() != 50);
    }


	

}
