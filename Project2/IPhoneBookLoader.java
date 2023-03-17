import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Instances of this interface can be used to load contact's data from a xml file.
 */
public interface IPhoneBookLoader{
    /**
     * This method loads the list of contacts from an XML file.
     * @param filepathToXML path to the XML file relative to the executable
     * @return a tree of contacts objects
     * @throws FileNotFoundException if file isn't found
     */
    List<IPhoneBook> loadNames(String filepathToXML) throws IOException;
}
