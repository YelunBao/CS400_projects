import java.util.zip.DataFormatException;

public class Validator implements IValidator {
	@Override
	public boolean isValid(String number) throws DataFormatException {
		//If the first index appear is less than 2, return false.
		if(number.indexOf("1") == 0) {
			return false;
		}else {
			for(int i = 0; i < number.length(); i++) {//
				if(!Character.isDigit(number.charAt(i))){
					return false;
				}
			}
			return true;
		}
	}

}

