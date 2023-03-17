import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;

public class PhoneBookMapper {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        IPhoneBookLoader load = new PhoneBookLoader();

        //Load from xml file
        List<IPhoneBook> phoneBook = load.loadNames("phone_book.xml");

	AE_RedBlackTree<IPhoneBook> rbt = new AE_RedBlackTree<IPhoneBook>();

        Backend back = new Backend(rbt);

        for (IPhoneBook contacts: phoneBook) {
            back.addContact(contacts.getName(), contacts.getPhoneNumber());
        }

        Scanner scnr = new Scanner(System.in);

        //Initiats the frontend
        IPhoneBookFrontend frontend = new PhoneBookFrontend(scnr, back);

        // Starts the program as a whole
        frontend.runCommandLoop();
    }
}
