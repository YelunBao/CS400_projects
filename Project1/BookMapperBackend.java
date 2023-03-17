import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class
BookMapperBackend implements IBookMapperBackend
{
    protected IterableMap<String, IBook> bookMap = new IterableMap<>();
    private String authorFilter = null;

    /**
     * Adds a new book to the backend's database and is stored in
     * a hash table internally.
     *
     * @param book the book to add
     */
    @Override
    public void addBook(IBook book)
    {
        if (book == null) {
            System.out.println("The book is invalid.");
        } else {
            bookMap.put(book.getISBN13(), book);
        }
    }

    /**
     * Returns the number of books stored in the backend's database.
     *
     * @return the number of books
     */
    @Override
    public int getNumberOfBooks()
    {
        return bookMap.size();
    }

    /**
     * This method can be used to set a filter for the author names
     * contained in the search results. A book is only returned as
     * a result for a search by title, it is also contains the string
     * filterBy in the names of its authors.
     *
     * @param filterBy the string that the book's author names must contain
     */
    @Override
    public void setAuthorFilter(String filterBy)
    {
        this.authorFilter = filterBy;
    }

    /**
     * Returns the string used as the author filter, null if no author
     * filter is currently set.
     *
     * @return the string used as the author filter, or null if none is set
     */
    @Override
    public String getAuthorFilter()
    {
        return this.authorFilter;
    }

    /**
     * Resets the author filter to null (no filter).
     */
    @Override
    public void resetAuthorFilter()
    {
        this.setAuthorFilter(null);
    }

    /**
     * Search through all the books in the title base and return books whose
     * title contains the string word (and that satisfies the author filter,
     * if an author filter is set).
     *
     * @param word word that must be contained in a book's title in result set
     * @return list of books found
     */
    @Override
    public List<IBook> searchByTitleWord(String word)
    {
        IBook book;
        List<IBook> searchResult = new ArrayList<>();
        for (IBook iBook : bookMap) {
            book = iBook;
            if (getAuthorFilter() == null) {
                if (book.getTitle().contains(word)) {
                    searchResult.add(book);
                }
            } else {
                if (book.getTitle().toLowerCase().contains(word.toLowerCase()) && book.getAuthors().contains(getAuthorFilter())) {
                    searchResult.add(book);
                }
            }
        }
        return searchResult;
    }

    /**
     * Return the book uniquely identified by the ISBN, or null if ISBN is not
     * present in the dataset.
     *
     * @param ISBN the book's ISBN number
     * @return the book identified by the ISBN, or null if ISBN not in database
     */
    @Override
    public IBook getByISBN(String ISBN)
    {
        try {
            return bookMap.get(ISBN);
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
    }
}
