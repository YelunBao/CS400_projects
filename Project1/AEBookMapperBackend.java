import java.util.List;
@SuppressWarnings("unchecked")
public class AEBookMapperBackend implements IBookMapperBackend{
    public static class BookTest implements IBook {
        String title;
        String authors;
        String ISBN13;

        public BookTest(String title, String authors, String ISBN13) {
            this.title = title;
            this.authors = authors;
            this.ISBN13 = ISBN13;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getAuthors() {
            return authors;
        }

        @Override
        public String getISBN13() {
            return ISBN13;
        }
    }
    IterableMap<String,IBook> map=new IterableMap<>();

    //init with the data extracted from csv file
    AEBookMapperBackend(){
        map.put("9780439554893",new AEBookMapperBackend.BookTest("q1","1q","9780439554893"));
        map.put("9781400052929",new AEBookMapperBackend.BookTest("w2","2w","9781400052929"));
        map.put("9780517149256",new AEBookMapperBackend.BookTest("3e","e3","9780517149256"));
        map.put("9780767915069",new AEBookMapperBackend.BookTest("4r","r4","9780767915069"));
        map.put("9780767903868",new AEBookMapperBackend.BookTest("5t","t5","9780767903868"));
        map.put("9780060920081",new AEBookMapperBackend.BookTest("6y","y6","9780060920081"));
        map.put("9780380727506",new AEBookMapperBackend.BookTest("7u","u7","9780380727506"));
        map.put("9780345538376",new AEBookMapperBackend.BookTest("8i","i8","9780345538376"));
        map.put("9780618346257",new AEBookMapperBackend.BookTest("9o","o9","9780618346257"));
        map.put("9780618391004",new AEBookMapperBackend.BookTest("0p","p0","9780618391004"));
        map.put("9780976694007",new AEBookMapperBackend.BookTest("1a","a1","9780976694007"));
    }
    @Override
    public void addBook(IBook book) {

    }

    @Override
    public int getNumberOfBooks() {
        return 0;
    }

    @Override
    public void setAuthorFilter(String filterBy) {

    }

    @Override
    public String getAuthorFilter() {
        return null;
    }

    @Override
    public void resetAuthorFilter() {

    }

    @Override
    public List<IBook> searchByTitleWord(String word) {
        return null;
    }

    @Override
    public IBook getByISBN(String ISBN) {
        return null;
    }
}
