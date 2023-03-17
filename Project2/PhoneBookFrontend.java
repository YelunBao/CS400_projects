import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class PhoneBookFrontend implements IPhoneBookFrontend {
	public Scanner scnr;
	public IBackend back;
	final int[] five = new int[] { 1, 2, 3, 4, 5 };
	final int[] two = new int[] { 1, 2 };

	public PhoneBookFrontend(Scanner scnr, IBackend back) {
		this.scnr = scnr;
		this.back = back;
	}

	@Override
	public void runCommandLoop() {
		// TODO Auto-generated method stub
		displayMainMenu();
		int x = trier(five);

		// now have a valid number so we can choose what to do

		switch (x) {
		case 1:
			add();
			runCommandLoop();
			break;
		case 2:
			remove();
			runCommandLoop();
			break;
		case 3:
			searchByName();
			runCommandLoop();
			break;
		case 4:
			displayContacts();
			runCommandLoop();
			break;
		case 5:
			quit();
			break;

		}

	}

	@Override
	public void displayMainMenu() {
		// TODO Auto-generated method stub
		System.out.println("Would you like to ...");
		System.out.println("  1) Add a contact");
		System.out.println("  2) Remove a contact");
		System.out.println("  3) Search for a contact by name");
		System.out.println("  4) Display all contacts");
		System.out.println("  5) Quit");
	}

	@Override
	public void displayContacts() {
		// TODO Auto-generated method stub
		List<IPhoneBook> list = back.getAllContacts();

		//check for no contacts
		if (list.size() == 0) {
			System.out.println("No contacts were found");
		}

		//has contacts
		System.out.println("You have " + list.size() + " contacts");
		System.out.println("Name, Number");
		for (int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i).getName() + ", " + list.get(i).getPhoneNumber());
		}
		System.out.println();
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		boolean validNumber = false;
		String number = null;
		String name;

		System.out.println("Are you sure you want to add a contact");
		System.out.println("  1) Yes");
		System.out.println("  2) No");
		int x = trier(two);

		if (x == 2) {
			return;
		}
		System.out.println("What is the name of the person you would like to add?");
		
		name = scnr.nextLine();

		System.out.println("Are you sure " + name + " is the name you would like to add");
		System.out.println("  1) Yes");
		System.out.println("  2) No");
		x = trier(two);

		if (x == 2) {
			return;
		}
		System.out.println("What is their phone number (enter with no spaces)");

		//check for valid phone number
		while (!validNumber) {
			number = scnr.next();
			try {
			validNumber = back.validator(number);
			} catch (Exception e) {}
			if (!validNumber) {
				System.out.println("Not a valid phone number");
			}
		}

		System.out.println("Are you sure " + number + " is the correct number");
		System.out.println("  1) Yes");
		System.out.println("  2) No");

		x = trier(two);

		if (x == 2) {
			return;
		}

		back.addContact(name, number);

		System.out.println("You have added " + name + " with phone number " + number + " to your contact list ");

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		String name;

		System.out.println("What is the name of the person you would like to remove");

		name = scnr.next();
		name += scnr.nextLine();

		System.out.println("Are you sure you want to remove " + name);
		System.out.println("  1) Yes");
		System.out.println("  2) No");

		int x = trier(two);

		if (x == 2) {
			return;
		}

		System.out.println("What is their number (enter with no spaces)");
		String number = scnr.next();
		System.out.println("Are you sure " + number + " is their number");

		System.out.println("  1) Yes");
		System.out.println("  2) No");

		x = trier(two);

		if (x == 2) {
			return;
		}

		boolean removed = back.removeContact(name, number);

		if (removed) {
			System.out.println(name + " has been removed from the contact list");
			return;
		}

		System.out.println(name + " was not found in your contact list");

	}

	@Override
	public void searchByName() {
		// TODO Auto-generated method stub
		String x;

		System.out.println("What is the name of the person you would like to search for");

		x = scnr.next();
		x += scnr.nextLine();

		List<IPhoneBook> list = back.search(x);

		//check for no contacts
		if (list.size() == 0) {
			System.out.println("No contacts by name " + x + " were found");
		} else {
			System.out.println("Name, Number");
		}

		//has contacts
		for (int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i).getName() + ", " + list.get(i).getPhoneNumber());
		}
		System.out.println();
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		// do nothing

	}
    // this method makes sure that the user input is valid
	private int trier(int[] y) {
		int x;
		while (true) {
			try {
				x = scnr.nextInt();
				scnr.nextLine();

				for (int i = 0; i < y.length; ++i) {
					if (x == y[i]) {
						return x;
					}
				}
			} catch (Exception e) {
				scnr.nextLine();
			}
			System.out.println("Invalid Number, please choose a new number");
		}
	}
}
