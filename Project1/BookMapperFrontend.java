import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class interacts with the user getting inputs for actions they want to perform in finding
 * a book in our Book Mapper and displaying those results back out to the user
 */
public class BookMapperFrontend implements IBookMapperFrontend {

  // Variables we will initiate in the constructor and use elsewhere
  protected Scanner userInputScanner;
  IBookMapperBackend backend;
  IISBNValidator validator;

  /**
   * executes the interface informing the user about the possible functions of the programs and
   * displaying the results after being interacted with and giving inputs on specific books needed
   *
   * @param args
   */
  public static void main(String[] args) {
    BookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new BookMapperBackend(), new ISBNValidator());
    frontend.runCommandLoop();
  }

  /**
   * Constructs a frontend class with an instance of the backend, validator and scanner
   *
   * @param userInputScanner allows us to get input from the user
   * @param backend gives us a backbone to interact with the book data and retrienve information
   * @param validator used to validate the correctness of isbn inputs before giving it to the
   *                  backend
   */
  public BookMapperFrontend(Scanner userInputScanner, IBookMapperBackend backend,
    IISBNValidator validator) {
    this.userInputScanner = userInputScanner;
    this.backend = backend;
    this.validator = validator;


    try {
      BookLoader bookLoader = new BookLoader();
      ArrayList<IBook> books = (ArrayList<IBook>) bookLoader.loadBooks("./books.csv");
      for (IBook book : books) {
        backend.addBook(book);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Runs the front end loop which welcomes the user and repeatedly asks the user what menu choice
   * they want executing the methods of that menu choice until they input 4 which exits the loop
   * and thanking them for using the application
   */
  @Override
  public void runCommandLoop() {
    System.out.println("Welcome to the Book Mapper Application!\n"
      + "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");

    String menuChoice;
    do {
      displayMainMenu();
      menuChoice = userInputScanner.nextLine();
      if (menuChoice.equals("1")) {
        isbnLookup();
      } else if (menuChoice.equals("2")) {
        titleSearch();
      } else if (menuChoice.equals("3")) {
        setAuthorFilter();
      } else if (menuChoice.equals("4")) {
        System.out.println("Goodbye!");
        userInputScanner.close();
      } else {
        System.out.println("Invalid Input. Please select a menu option (1-4)");
      }
    } while (!menuChoice.equals("4"));
  }

  /**
   * Displays the main menu options of actions the user can take when interacting with the
   * application
   */
  @Override
  public void displayMainMenu() {
    System.out.println("\nYou are in the Main Menu: "
      + "\n\t1) Lookup ISBN "
      + "\n\t2) Search by Title Word "
      + "\n\t3) Set Author Name Filter "
      + "\n\t4) Exit Application");
  }

  /**
   * Neatly formats the neccesary information of the book into the a presentable String we can
   * present to the user giving them the title, author(s) and isbn
   *
   * @param book the book we want to format for the user
   * @return string containing the books neccesary information
   */
  public static String printBook(IBook book) {
    return ("\"" + book.getTitle() + "\" by " + book.getAuthors() + ", ISBN: " + book.getISBN13());
  }

  /**
   * Displays a list of books by iterating through a linked list, getting the correlating book
   * and then calling on the printBook() method to display the information of multiple books
   *
   * @param books a linked list of books we want to display to the user
   */
  @Override
  public void displayBooks(List<IBook> books) {
    for (int i = 0; i < books.size(); i++) {
      System.out.println((i + 1) + ". " + printBook(books.get(i)) + "\n");
    }
  }

  /**
   * Takes input from the user in what isbn they want to lookup, validates that the isbn is one
   * in the list and then displays the book correlated to it
   */
  @Override
  public void isbnLookup() {
    System.out.print("You are in the Lookup ISBN Menu:\n" + "\tEnter ISBN to look up: ");

    String isbn = userInputScanner.nextLine().trim();
    System.out.println(isbn);

    if (backend.getByISBN(isbn) != null) {
      System.out.println("1. " + printBook(backend.getByISBN(isbn)));
    } else {
      System.out.println("Invalid ISBN: Please re-enter the ISBN Lookup from the main menu");
    }
  }

  /**
   * Takes the input of the user with what title they want to lookup, gets the list of books with
   * that title and then printing out that list through the displayBooks(); method
   */
  @Override
  public void titleSearch() {
    System.out.print("You are in the Search for Title Word Menu:\n"
      + "\tEnter a word to search for in book titles (empty for all books): ");

    String title = userInputScanner.nextLine().trim();
    List<IBook> titleBooks = backend.searchByTitleWord(title);
    int totalBooks = backend.getNumberOfBooks();
    int foundBooks = titleBooks.size();

    System.out.println("Matches (author filter: " + backend.getAuthorFilter() + ") " +
      foundBooks + " of " + totalBooks);
    displayBooks(titleBooks);
    System.out.println("Matches (author filter: " + backend.getAuthorFilter() + ") " +
      foundBooks + " of " + totalBooks);
  }

  /**
   * Sets the author filter in the backend to be later used in the titleSearch
   */
  public void setAuthorFilter() {
    System.out.println("You are in the Set Author Filter Menu:");
    if (backend.getAuthorFilter() != null) {
      System.out.println("\tAuthor name must currently contain: " + backend.getAuthorFilter());
    } else {
      System.out.println("Author name must currently contain: none");
    }
    System.out.println("\tEnter a new string for author names to contain (empty for any): ");
    String filter = userInputScanner.nextLine().trim();
    backend.setAuthorFilter(filter);
  }
}
