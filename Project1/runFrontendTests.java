import java.util.Scanner;

/**
 * Tests that the BookMapperFrontend works correctly
 */
public class runFrontendTests {

  /**
   * Tests that an invalid main menu input returns a statement asking the user to reenter an
   * integer between 1 and 4
   *
   * @return true if the user is prompted again and false if the input is allowed
   */
  public static boolean invalidTest() {
    TextUITester tester = new TextUITester("23\n4\n");

    IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new FDBookMapperBackend(), new FDISBNValidator());

    frontend.runCommandLoop();

    String output = tester.checkOutput();

    return output.contains("Invalid Input. Please select a menu option (1-4)");
  }

  /**
   * Tests that the correct book is printed out when given its ISBN
   *
   * @return true is the correct book is printed out and false if it isn't
   */
  public static boolean isbnTest() {
    TextUITester tester = new TextUITester("1\n9780330491198\n4\n");

    IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new FDBookMapperBackend(), new FDISBNValidator());

    frontend.runCommandLoop();

    String output = tester.checkOutput();

    return output.contains("1. \"The Hitchhiker's Guide to the Galaxy "
      + "(Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198");
  }

  /**
   * Tests that the correct books are printed out when a book title is inputted to titleSearch()
   *
   * @return true is the correct books are printed out and false if they aren't
   */
  public static boolean filterTitleSearchTest() {
    TextUITester tester = new TextUITester("3\nJames Joyce\n2\n\n4\n");


    IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new FDBookMapperBackend(), new FDISBNValidator());
    frontend.runCommandLoop();

    String output = tester.checkOutput();

    return (output.contains("\"A Portrait of the Artist as a Young Man\" by James Joyce/Jim Norton,"
      + " ISBN: 9789626343661"));
  }

  /**
   * Tests that the correct author filter is outputted when one hasn't been inputted
   *
   * @return true is the correct author filter is outputted and false if its incorrect
   */
  public static boolean noFilterTitleSearchTest() {
    TextUITester tester = new TextUITester("2\nsteam\n4\n");

    IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new FDBookMapperBackend(), new FDISBNValidator());
    frontend.runCommandLoop();

    String output = tester.checkOutput();

    return (output.contains("\"The End of Nana Sahib: The Steam House (Extraordinary Voyages "
      + "#20)\" by Jules Verne/Agnes D. Kingston, ISBN: 9781410103277"));
  }

  /**
   * Tests that the command loop ends and the user is told goodbye after inputting 4 in the menu
   *
   * @return true if the command loop ends and false if it doesnt
   */
  public static boolean exitTest() {
    TextUITester tester = new TextUITester("1\n9780330491198\n4\n");

    IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new FDBookMapperBackend(), new FDISBNValidator());
    frontend.runCommandLoop();

    String output = tester.checkOutput();

    return output.contains("Goodbye!");
  }

  /**
   * Tests that the command loop can still terminate even when loading in the BookMapperFrontend
   *
   * @return true if the command loop ends and false if it doesnt
   */
  public static boolean integrationTest1() {
    TextUITester tester = new TextUITester("4\n");
    IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new BookMapperBackend(), new ISBNValidator());
    frontend.runCommandLoop();

    String output = tester.checkOutput();

    return output.contains("Goodbye!");
  }

  /**
   * Tests even after the backend is implemented that the user input is still verified to be true
   * when choosing a main menu selection
   *
   * @return true if the backend doesn't interfere with the main menu input and false if it
   * changes the allowed strings that could be given by the user
   */
  public static boolean integrationTest2() {
    TextUITester tester = new TextUITester("1\n9780330491198\n4\n");

    IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
      new BookMapperBackend(), new ISBNValidator());

    frontend.runCommandLoop();

    String output = tester.checkOutput();

    return output.contains("1. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198");

  }

  /**
   * Tests the algorithm engineers put and get method
   *
   * @return true if the put method correctly places the book and false if it doesnt
   */
  public static boolean AETest1() {
    AEBookMapperBackend placeholder = new AEBookMapperBackend();
    IterableMap<String,IBook> map = placeholder.map;
    try{
      IBook book1 = new AEBookMapperBackend.BookTest("2s","s2","9781557344496");
      IBook book2 = new AEBookMapperBackend.BookTest("3d","d3","9781595580276");
      //test the put method
      map.put("9781557344496",book1);
      map.put("9781595580276",book2);

      //test the get method
      if(!map.get("9781595580276").equals(book2))
        return false;
    }
    catch(Exception e){
      return false;
    }
    return true;
  }

  /**
   * Tests the algorithm engineers remove method of iterableMap
   *
   * @return true if the book is removed and false if it isnt
   */
  public static boolean AETest2() {
    AEBookMapperBackend placeholder = new AEBookMapperBackend();
    IterableMap<String,IBook> map = placeholder.map;
    try{
      int prevSize = map.size();
      map.remove("9780439554893");
      if(map.size() != prevSize - 1)
        return false;
    }
    catch(Exception e){
      return false;
    }
    return true;
  }

  /**
   * Runs all tests
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("FD Individual Test 1: " + invalidTest());
    System.out.println("FD Individual Test 2: " + isbnTest());
    System.out.println("FD Individual Test 3: " + noFilterTitleSearchTest());
    System.out.println("FD Individual Test 4: " + filterTitleSearchTest());
    System.out.println("FD Individual Test 5: " + exitTest());
    System.out.println("FD Integration Test 1: " + integrationTest1());
    System.out.println("FD Integration Test 2: " + integrationTest2());
    System.out.println("FD Partner AE Test 1: " + AETest1());
    System.out.println("FD Partner AE Test 2: " + AETest2());
  }
}

