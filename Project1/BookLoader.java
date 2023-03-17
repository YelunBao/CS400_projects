import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookLoader implements IBookLoader
{
    /**
     * This method loads the list of books from a CSV file.
     *
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException
    {
        // col 1 for title; col 2 for author; col 5 for isbn
        File file = new File(filepathToCSV);
        Scanner scanner = new Scanner(file);
        ArrayList<IBook> bookList = new ArrayList<>();
        String line = null;
        // get rid of the first line which we don't want
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] bookInfo = scanner.nextLine().split(",");
            bookList.add(new Book(bookInfo[1], bookInfo[2], bookInfo[5]));
        }
        return bookList;
    }
}
