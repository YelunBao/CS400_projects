/**
 * This interface defines getter methods for each contact data attributes
 * and is implemented by classes that represent a contact  and its associated
 * data.
 */
public interface IPhoneBook extends Comparable<IPhoneBook>{

    /**
     * Returns a string that contains the name of a person
     * @return names as single string
     */
    String getName();

    /**
     * Returns the phone number of the person.
     * @return Phone numbers
     */
    String getPhoneNumber();

    /**
     * Compare 2 item
     * @param that items to be compared to
     * @return 1 if greater, -1 if less, 0 if same
     */
    int compareTo(IPhoneBook that);
}
