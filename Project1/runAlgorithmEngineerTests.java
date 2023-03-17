import java.util.Iterator;
import java.util.Scanner;

public class runAlgorithmEngineerTests {
    static AEBookMapperBackend placeholder;
    static IterableMap<String,IBook> map;
    public static void main(String[] args) {
        System.out.println("AlgorithmEngineer test 1: "+(test1()?"passed":"failed"));
        System.out.println("AlgorithmEngineer test 2: "+(test2()?"passed":"failed"));
        System.out.println("AlgorithmEngineer test 3: "+(test3()?"passed":"failed"));
        System.out.println("AlgorithmEngineer test 4: "+(test4()?"passed":"failed"));
        System.out.println("AlgorithmEngineer test 5: "+(test5()?"passed":"failed"));
        System.out.println("AlgorithmEngineer Integration Test 1: "+(integrationTest1()?"passed":"failed"));
        System.out.println("AlgorithmEngineer Integration Test 2: "+(integrationTest2()?"passed":"failed"));
        System.out.println("AlgorithmEngineer FrontendDeveloper Test 1: " + (PartnerTest1()?"passed":"failed"));
        System.out.println("AlgorithmEngineer FrontendDeveloper Test 2: " + (PartnerTest2()?"passed":"failed"));
    }

    //test the put and get method
    public static boolean test1() {
        placeholder=new AEBookMapperBackend();
        map=placeholder.map;
        try{
            IBook b1 = new AEBookMapperBackend.BookTest("2s","s2","9781557344496");
            IBook b2 = new AEBookMapperBackend.BookTest("3d","d3","9781595580276");
            //test the put method
            map.put("9781557344496",b1);
            map.put("9781595580276",b2);

            //test the get method
            if(!map.get("9781595580276").equals(b2))
                return false;
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    //test the remove method of iterableMap
    public static boolean test2() {
        placeholder=new AEBookMapperBackend();
        map=placeholder.map;
        try{
            int prevSize=map.size();
            map.remove("9780517149256");
            if(map.size()!=prevSize-1)
                return false;
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    //test the containsKey method
    public static boolean test3() {
        placeholder=new AEBookMapperBackend();
        map=placeholder.map;
        try{
            if(!map.containsKey("9780517149256"))
                return false;

        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    //test the ValueIterator of IterableMap and its methods
    public static boolean test4() {
        placeholder=new AEBookMapperBackend();
        map=placeholder.map;
        try{
            int count = 0;
            Iterator iter=map.iterator();

            //test iterator remove method
            int prevSize=map.size();
            iter.next();
            iter.remove();

            //use the iterator to traverse the map
            if(map.size()!=prevSize-1)
                return false;
            while(iter.hasNext()){
                iter.next();
                count++;
            }
            if(count!=map.size())
                return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //test the isbn validator
    public static boolean test5() {
        placeholder=new AEBookMapperBackend();
        map=placeholder.map;
        IISBNValidator v=new ISBNValidator();
        try{
            for(IBook e:map)
                if(!v.validate(e.getISBN13()))
                    return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

   public static boolean integrationTest1(){
        BookMapperBackend bookMapperBackend=new BookMapperBackend();
        bookMapperBackend.addBook(new Book("q1","1q","9780439554893"));

        map=bookMapperBackend.bookMap;
        IISBNValidator v=new ISBNValidator();
        try{
            for(IBook e:map)
                if(!v.validate(e.getISBN13()))
                    return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
}

    public static boolean integrationTest2(){
        BookMapperBackend bookMapperBackend=new BookMapperBackend();
        bookMapperBackend.addBook(new Book("q1","1q","9780439554893"));
        bookMapperBackend.addBook(new Book("q1","1q","9780439554893"));
        map=bookMapperBackend.bookMap;
        try{
            int count = 0;
            Iterator<IBook> iter=map.iterator();

            int prevSize=map.size();
            iter.next();
            iter.remove();

            if(map.size()!=prevSize-1)
                return false;
            while(iter.hasNext()){
                iter.next();
                count++;
            }
            if(count!=map.size())
                return false;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //exit test
    public static boolean PartnerTest1(){
        TextUITester tester = new TextUITester("2\ntest\n4\n");
        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
                new BookMapperBackend(), new ISBNValidator());
        frontend.runCommandLoop();
        String output = tester.checkOutput();
        return output.contains("Goodbye!");
    }

    //combination test: do "Lookup ISBN" and "Search by Title Word"
    public static boolean PartnerTest2(){
        TextUITester tester = new TextUITester("1\n9780330491198\n2\nPortrait\n4\n");

        IBookMapperFrontend frontend = new BookMapperFrontend(new Scanner(System.in),
                new BookMapperBackend(), new ISBNValidator());

        frontend.runCommandLoop();
        String output = tester.checkOutput();
        if(!output.contains("1. \"The Hitchhiker's Guide to the Galaxy")||!output.contains("A Portrait of the Artist as a Young Man"))
            return false;
        return true;
    }
}

