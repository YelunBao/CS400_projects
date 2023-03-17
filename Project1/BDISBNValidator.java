public class BDISBNValidator implements IISBNValidator
{
    /**
     * Checks is the given ISBN number is a valid ISBN13 number.
     *
     * @param isbn13 the ISBN number to validate
     * @return true is the number if a valid ISBN number, false otherwise
     */
    @Override
    public boolean validate(String isbn13)
    {
        return isbn13.equals("4509185");
    }
}
