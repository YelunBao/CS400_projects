public class BDBook implements IBook
{
    private String title;
    private String author;
    private String ISBN;

    public BDBook(String title, String author, String ISBN)
    {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    /**
     * Returns the title of the book.
     *
     * @return title of the book
     */
    @Override
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Returns a string that contains the authors of the book
     * as a single string with different authors separated by /.
     *
     * @return author names as single string
     */
    @Override
    public String getAuthors()
    {
        return this.author;
    }

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     *
     * @return ISBN number of book
     */
    @Override
    public String getISBN13()
    {
        return this.ISBN;
    }
}
