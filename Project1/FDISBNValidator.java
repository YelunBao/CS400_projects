/**
 * Hard coded class to "validate" isbn value after retrieving it from the front end
 */
public class FDISBNValidator implements IISBNValidator {

  /**
   * Returns true after getting the isbn
   *
   * @param isbn13 the ISBN to validate
   * @return true
   */
  @Override public boolean validate(String isbn13) {
    return true;
  }
}

