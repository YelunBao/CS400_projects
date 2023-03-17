import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BDBookLoader implements IBookLoader
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
        List<IBook> bookList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            bookList.add(new BDBook("Book" + i, "Me", "" + i));
        }
        return bookList;
    }
}
