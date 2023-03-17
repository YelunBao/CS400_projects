import java.util.Arrays;
import java.util.List;

/**
 * Hard coded backend class that replicates some methods the backend should perform to make sure
 * that the frontend is functioning correctly
 */
public class FDBookMapperBackend implements IBookMapperBackend {

  // what author we want the book titles to come from
  protected String authorFilter;

  // instances of IBook that holds one of our fake books to access in other methods
  class Book1 implements IBook {

    @Override public String getTitle() {
      return "The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)";
    }

    @Override public String getAuthors() {
      return "Douglas Adams";
    }

    @Override public String getISBN13() {
      return "9780330491198";
    }
  }

  class Book2 implements IBook {

    @Override public String getTitle() {
      return "A Portrait of the Artist as a Young Man";
    }

    @Override public String getAuthors() {
      return "James Joyce/Jim Norton";
    }

    @Override public String getISBN13() {
      return "9789626343661";
    }
  }

  class Book3 implements IBook {

    @Override public String getTitle() {
      return "The End of Nana Sahib: The Steam House (Extraordinary Voyages #20)";
    }

    @Override public String getAuthors() {
      return "Jules Verne/Agnes D. Kingston";
    }

    @Override public String getISBN13() {
      return "9781410103277";
    }
  }

  /**
   * Method not implemented due to lack of use on frontend
   *
   * @param book the book to add
   */
  @Override
  public void addBook(IBook book) {
  }

  /**
   * Returns the number of books in the total catalog of books. In this case we hard code this
   * information to be 123
   *
   * @return a fake number of total books (123)
   */
  @Override
  public int getNumberOfBooks() {
    return 123;
  }

  /**
   * Sets the author filter to a string input put by the user
   *
   * @param filterBy the string that the book's author names must contain
   */
  @Override
  public void setAuthorFilter(String filterBy) {
    this.authorFilter = filterBy;
  }

  /**
   * Returns what the current author filter being used is
   *
   * @return the current author filer being used by the application
   */
  @Override public String getAuthorFilter() {
    return this.authorFilter;
  }

  /**
   * Resets the author filter by setting it to null
   */
  @Override public void
  resetAuthorFilter() {
    this.authorFilter = null;
  }

  /**
   * Returns a list of books that have the word in the title which in our case is hardcoded to be
   * "book"
   *
   * @param word word that must be contained in a book's title in result set
   * @return the hard coded list of books we have created
   */
  @Override public List<IBook>
  searchByTitleWord(String word) {
    return Arrays.asList(new IBook[]{new Book1(), new Book2(), new Book3()});
  }

  /**
   * Returns the book associated with the isbn or in this cas we hard coded it to be book1
   *
   * @param ISBN the book's ISBN number
   * @return Book1
   */
  @Override public IBook getByISBN(String ISBN) {
    return new Book1();
  }
}
