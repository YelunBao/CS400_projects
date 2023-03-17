import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class runBackendDeveloperTests
{
    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("BD Individual Test 1: " + test1());
        System.out.println("BD Individual Test 2: " + test2());
        System.out.println("BD Individual Test 3: " + test3());
        System.out.println("BD Individual Test 4: " + test4());
        System.out.println("BD Individual Test 5: " + test5());
        System.out.println("BD Integration Test 1: " + BDIntegrationTest1());
        System.out.println("BD Integration Test 2: " + BDIntegrationTest2());
        System.out.println("BD Partner (AE) Test 1: " + BDPartnerAETest1());
        System.out.println("BD Partner (AE) Test 2: " + BDPartnerAETest2());
    }

    public static boolean test1() // check the validity of addBook() and getNumberOfBooks()
    {
        BookMapperBackend bookMapperBackend = new BookMapperBackend();
        bookMapperBackend.addBook(new BDBook("Magic Stone", "J.K. Rowling", "9401858475034"));
        bookMapperBackend.addBook(new BDBook("Phoenix", "J.K. Rowling", "419583198584932"));
        return bookMapperBackend.getNumberOfBooks() == 2;
    }

    public static boolean test2() // check if the validity of searchByTitleWords
    {
        List<IBook> searchResult;
        BookMapperBackend bookMapperBackend = new BookMapperBackend();
        for (int i = 0; i < 3; i++) { // generate 3 books titled HP0, HP1, HP2
            bookMapperBackend.addBook(new BDBook("HP" + i, "J.K. Rowling", "" + i));
        }
        for (int i = 0; i < 2; i++) { // generate 2 books titled Test0, Test1 with the same author
            bookMapperBackend.addBook(new BDBook("Test" + i, "J.K. Rowling", "1" + i));
        }
        searchResult = bookMapperBackend.searchByTitleWord("HP");

        if (searchResult.size() != 3) {
            return false;
        } else {
            boolean flag = true;
            for (int i = 0; i < searchResult.size(); i++) {
                if (!searchResult.get(i).getTitle().equals("HP" + i)) {
                    flag = false;
                }
            }
            return flag;
        }
    }

    public static boolean test3() // check if author filter is applied properly
    {
        List<IBook> searchResult;
        BookMapperBackend bookMapperBackend = new BookMapperBackend();
        for (int i = 0; i < 2; i++) {
            bookMapperBackend.addBook(new BDBook("HP", "J.K. Rowling", "" + i));
        }
        for (int i = 2; i < 4; i++) {
            bookMapperBackend.addBook(new BDBook("HP", "George Orwell", "" + i));
        }
        bookMapperBackend.addBook(new BDBook("HP", "MIT", "4"));
        bookMapperBackend.setAuthorFilter("MIT");
        searchResult = bookMapperBackend.searchByTitleWord("HP");
        return searchResult.get(0).getAuthors().equals("MIT");
    }

    public static boolean test4() // check if the validity of resetAuthorFilter
    {
        List<IBook> searchResult;
        BookMapperBackend bookMapperBackend = new BookMapperBackend();
        for (int i = 0; i < 2; i++) {
            bookMapperBackend.addBook(new BDBook("HP", "J.K. Rowling", "" + i));
        }
        for (int i = 2; i < 4; i++) {
            bookMapperBackend.addBook(new BDBook("HP", "George Orwell", "" + i));
        }
        bookMapperBackend.addBook(new BDBook("HP", "MIT", "4"));
        bookMapperBackend.setAuthorFilter("MIT");
        bookMapperBackend.resetAuthorFilter();
        searchResult = bookMapperBackend.searchByTitleWord("HP");
        return searchResult.size() == 5;
    }

    public static boolean test5() // check the validity of getByISBN
    {
        List<IBook> searchResult;
        BookMapperBackend bookMapperBackend = new BookMapperBackend();
        for (int i = 0; i < 3; i++) { // generate 3 books titled HP0, HP1, HP2
            bookMapperBackend.addBook(new BDBook("HP" + i, "J.K. Rowling", "" + i));
        }
        for (int i = 0; i < 2; i++) { // generate 2 books titled Test0, Test1 with the same author
            bookMapperBackend.addBook(new BDBook("Test" + i, "J.K. Rowling", "1" + i));
        }
        return bookMapperBackend.getByISBN("2").getTitle().equals("HP2");
    }

    /*
     * If AE's IterableMap's key-value storage works, getByISBn should return a valid ISBN,
     * thus BDIntegrationTest1 should return true; false otherwise.
     */
    public static boolean BDIntegrationTest1()
    {
        BookMapperBackend bookMapperBackend = new BookMapperBackend();
        // using BDBook instead of Book because we are testing AE's codes here
        bookMapperBackend.addBook(new Book("HP", "JK", "12345"));
        return bookMapperBackend.getByISBN("12345").getTitle().equals("HP");
    }

    /*
     * If AE's IterableMap can be iterated, searchByTitle should return valid title,
     * thus BDIntegrationTest2 should return ture; false otherwise.
     */
    public static boolean BDIntegrationTest2()
    {
        List<IBook> searchResult;
        BookMapperBackend bookMapperBackend = new BookMapperBackend();
        for (int i = 0; i < 3; i++) { // generate 3 books titled HP0, HP1, HP2
            bookMapperBackend.addBook(new Book("HP" + i, "J.K. Rowling", "" + i));
        }
        for (int i = 0; i < 2; i++) { // generate 2 books titled Test0, Test1 with the same author
            bookMapperBackend.addBook(new Book("Test" + i, "J.K. Rowling", "1" + i));
        }
        searchResult = bookMapperBackend.searchByTitleWord("HP");

        if (searchResult.size() != 3) {
            return false;
        } else {
            boolean flag = true;
            for (int i = 0; i < searchResult.size(); i++) {
                if (!searchResult.get(i).getTitle().equals("HP" + i)) {
                    flag = false;
                }
            }
            return flag;
        }
    }

    public static boolean BDPartnerAETest1() throws FileNotFoundException
    {
        BookLoader BookLoader = new BookLoader();
        ArrayList<IBook> bookList;
        bookList = (ArrayList<IBook>) BookLoader.loadBooks("./books.csv");
        return bookList.get(0).getISBN13().equals("9780439785969");
    }

    public static boolean BDPartnerAETest2() throws FileNotFoundException
    {
        BookLoader BookLoader = new BookLoader();
        ArrayList<IBook> bookList;
        bookList = (ArrayList<IBook>) BookLoader.loadBooks("./books.csv");
        return bookList.get(0).getTitle().equals("Harry Potter and the Half-Blood Prince (Harry Potter  #6)");
    }
}
