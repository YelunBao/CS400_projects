import java.util.Scanner;

//This interface is what will be used to show output to the user.
public interface IPhoneBookFrontend {

    // This is a constructor that will be used to read information from the Backend Developers code
    // PhoneBookFronted(Scanner scnr, IBackend back, IValidator validator)

    //This method will be used to keep portraying information to the screen 
    public void runCommandLoop();

    //This method will be used to display the main menu options, it will be used quite frequently with the runCommandLoop method
    public void displayMainMenu();

    //This method will display all contacts to the screen in their corect order, based off name then number
    public void displayContacts();

    //This method will add contacts to the contact list
    public void add();

    //This method will remove contacts from the contact list
    public void remove();

    //This method will be used to search by name a person in the contact list
    public void searchByName();

    //This method will be used to quit
    public void quit();

}
