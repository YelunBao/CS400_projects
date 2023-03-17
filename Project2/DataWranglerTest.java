import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataWranglerTest {
    private static final String FILENAME = "phone_book.xml";
    private PhoneBookLoader phoneBook = new PhoneBookLoader();
    private final List<IPhoneBook> list = phoneBook.loadNames(FILENAME);
    IRedBlackTree<IPhoneBook> rbt = new BD_RedBlackTree();
    IBackend test = new Backend(rbt);

    IBackend back = new Backend();
    File file = new File("phone_book.xml");
    Scanner scnr = new Scanner(file);
    PhoneBookFrontend phone = new PhoneBookFrontend(scnr, back);

    public DataWranglerTest() throws IOException {
    }

    @Test
    public void Individual_DataWrangler_1() {
        assertEquals("Bernard Bullock", list.get(0).getName());
        assertEquals("2625066474", list.get(0).getPhoneNumber());
    }

    @Test
    public void Individual_DataWrangler_2(){
        assertEquals("Hannah Hardin", list.get(49).getName());
        assertEquals("7345860129", list.get(49).getPhoneNumber());
    }

    @Test
    public void Individual_DataWrangler_3(){
        assertEquals("Nicholas Key", list.get(5).getName());
        assertEquals("9854978326", list.get(5).getPhoneNumber());
    }

    @Test
    public void Individual_DataWrangler_4(){
        assertEquals("Justin Johnston", list.get(7).getName());
        assertEquals("2095245036", list.get(7).getPhoneNumber());
    }

    @Test
    public void Individual_DataWrangler_5(){
        assertEquals("Dee Schultz", list.get(47).getName());
        assertEquals("6416821602", list.get(47).getPhoneNumber());
//        for (int i = 0; i != 50; i++) {
//            System.out.println(list.get(i).getName());
//        }
    }

    @Test
    public void Integration_DataWrangler_1(){
        AE_RedBlackTree<IPhoneBook> rbt = new AE_RedBlackTree<IPhoneBook>();

        Backend back = new Backend(rbt);

        for (IPhoneBook contacts: list) {
            back.addContact(contacts.getName(), contacts.getPhoneNumber());
        }

        Scanner scnr = new Scanner(System.in);

        //Initiats the frontend
        IPhoneBookFrontend frontend = new PhoneBookFrontend(scnr, back);

        //frontend.displayContacts();

        System.out.println(back.search("Madison").get(0).getName());

        assertEquals(back.search("Madison").get(0).getName(),"Madison Boyer");
    }

    @Test
    public void Integration_DataWrangler_2(){
        AE_RedBlackTree<IPhoneBook> rbt = new AE_RedBlackTree<IPhoneBook>();

        Backend back = new Backend(rbt);

        for (IPhoneBook contacts: list) {
            back.addContact(contacts.getName(), contacts.getPhoneNumber());
        }

        Scanner scnr = new Scanner(System.in);

        //Initiats the frontend
        IPhoneBookFrontend frontend = new PhoneBookFrontend(scnr, back);

        //frontend.displayContacts();

        back.addContact("Julian Noeske", "2222222222");
        //System.out.println(back.search("Julian").get(0).getName());

        assertEquals(back.search("Julian").get(0).getName(),"Julian Noeske");
    }

    @Test
    public void DataWrangler_CodeReviewOf_FrontendDeveloper_1() throws FileNotFoundException {
        AE_RedBlackTree<IPhoneBook> rbt = new AE_RedBlackTree<IPhoneBook>();

        Backend back = new Backend(rbt);

        for (IPhoneBook contacts: list) {
            back.addContact(contacts.getName(), contacts.getPhoneNumber());
        }

        Scanner scnr = new Scanner(System.in);

        //Initiats the frontend
        IPhoneBookFrontend frontend = new PhoneBookFrontend(scnr, back);

        frontend.displayContacts();
        assertTrue(list.get(0).getName().equals("Bernard Bullock"));
    }

    @Test
    public void DataWrangler_CodeReviewOf_CodeReviewOf_FrontendDeveloper_2(){
        AE_RedBlackTree<IPhoneBook> rbt = new AE_RedBlackTree<IPhoneBook>();

        Backend back = new Backend(rbt);

        for (IPhoneBook contacts: list) {
            back.addContact(contacts.getName(), contacts.getPhoneNumber());
        }

        Scanner scnr = new Scanner(System.in);

        //Initiats the frontend
        IPhoneBookFrontend frontend = new PhoneBookFrontend(scnr, back);

        frontend.displayMainMenu();
        assertTrue(list.get(0).getName().equals("Bernard Bullock"));
    }
}
