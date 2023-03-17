import java.util.List;
import java.util.Scanner;

public class BDBookMapperFrontend implements IBookMapperFrontend
{
    Scanner userInputScanner = new Scanner(System.in);
    IBookMapperBackend iBookMapperBackend = new BookMapperBackend();
    IISBNValidator iisbnValidator;
    public BDBookMapperFrontend(Scanner userInputScanner, IBookMapperBackend iBookMapperBackend, IISBNValidator isbnValidator)
    {
        this.userInputScanner = userInputScanner;
        this.iBookMapperBackend = iBookMapperBackend;
        this.iisbnValidator = isbnValidator;
    }

    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
     */
    @Override
    public void runCommandLoop()
    {

    }

    @Override
    public void displayMainMenu()
    {

    }

    @Override
    public void displayBooks(List<IBook> books)
    {

    }

    @Override
    public void isbnLookup()
    {

    }

    @Override
    public void titleSearch()
    {

    }
}
