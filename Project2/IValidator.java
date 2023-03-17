import java.util.zip.DataFormatException;

/**
 * Implementations of this interface can be used to validate a number
 */
public interface IValidator {
    /**
     * Checks is the given phone number is a valid phone number. Validate use can only entry 10 digits (like NXXNXXXXXX)
     * According to US Formatting, N cannot be less than 2 nor greater than 9. An example of a valid number: 2629253943, and an example
     * of an invalid number: 1230453943
     * 
     * @param number the phone number to validate
     * @return true if the string follows the format and is validated, false if format is valid but N < 2 or N > 9
     * @throws DataFormatException  if phone number is not formatted correctly (non-digital character or the string is not 10 digits) 
     */
    public boolean isValid(String number) throws DataFormatException;
 
}
