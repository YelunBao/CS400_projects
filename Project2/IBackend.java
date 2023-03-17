
import java.util.List;
import java.util.zip.DataFormatException;

public interface IBackend
{
    /*
     * addContact() method will enable the user to add new contacts 
     * called by the frontend to the default contact.
     * The new name and phoneNum would be added to the tree.
     *
     * @return true if the contact was added, and false if there is already an identical contact
     *              in the database
     */
    public boolean addContact(String name, String number);

    /*
     * removeContact() method will remove the contact by the name from the tree.
     *
     * @return true if the contact was removed, and false if there is no contact with that name
     */

    public boolean removeContact(String name, String number);

    /*
     * search() method will take in characters of the name 
     * return the list of names which has the key word the 
     * user has searched.
     * @param nameKeyWord the key word the user will search.
     * @return results of the search
     */
    public List<IPhoneBook> search(String nameKeyWord);

    public List<IPhoneBook> getAllContacts();

    /*
     * validator() method validates the phone numbers.
     *
     * @return true if the phone number is formatted properly and is valid, and false if the
     *              phone number is formatted properly but invalid.
     *
     * @throws DataFormatException if the phone number is not formatted properly
     */
    public boolean validator(String phoneNum) throws DataFormatException;

}

