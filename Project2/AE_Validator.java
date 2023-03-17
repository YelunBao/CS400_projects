import java.util.zip.DataFormatException;

/**
 * This class is used to validate a phone number in the phone book
 * 
 * @author Nathaniel Owusu
 */
public class AE_Validator implements IValidator {

	/**
	 * Checks is the given phone number is a valid phone number. Validate use can
	 * only entry 10 digits (like NXXNXXXXXX) According to US Formatting, N cannot
	 * be less than 2 nor greater than 9. An example of a valid number: 2629253943,
	 * and an example of an invalid number: 1230453943
	 * 
	 * @param number the phone number to validate
	 * @return true if the string follows the format and is validated, false if
	 *         format is valid but N < 2 or N > 9
	 * @throws DataFormatException if phone number is not formatted correctly
	 *                             (non-digital character or the string is not 10
	 *                             digits)
	 */
	@Override
	public boolean isValid(String number) throws DataFormatException {

		// Check if the passed in number is null
		if (number == null) {
			throw new DataFormatException("Error: Phone number cannot be null");
		}

		// Check if there are 10 digits in the phone number
		if (number.length() != 10) {
			throw new DataFormatException("Error: Phone number must be 10 digits long");
		}

		// Check if there are any non-digit characters in the number
		for (int i = 0; i < number.length(); i++) {
			if (number.charAt(i) < '0' || number.charAt(i) > '9') {
				throw new DataFormatException("Error: Phone number must contain only digits");
			}
		}

		// Checks if the first digit ('N') in the number is >= 2 or <= 9, following
		// NXXNXXXXXX format
		if (number.charAt(0) < '2' || number.charAt(0) > '9') {
			return false;
		}

		// Checks if the fourth digit ('N') in the number is > 2 or < 9, following
		// NXXNXXXXXX format
		if (number.charAt(3) < '2' || number.charAt(3) > '9') {
			return false;
		}

		// A second check to make sure the string contains all digits
		// Try parsing the number into an integer, if there is no exception thrown, then
		// the string contains all digits
		try {
			Long.parseLong(number);

		} catch (Exception e) {
			throw new DataFormatException("Error: Phone Number must contain only Integers");
		}
		return true;
	}
}
