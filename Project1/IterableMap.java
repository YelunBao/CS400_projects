import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Iterator;
import java.util.function.Consumer;
@SuppressWarnings("unchecked")
public class IterableMap<KeyType, ValueType> implements IterableMapADT<KeyType,ValueType> {
    /**
     * total amount of the LinkedList in the table
     */
    private int capacity;
    /**
     * total amount of entries contained in this map
     */
    private int size;
    private LinkedList<Entry>[] table;

    class EntryIterator {
        int count;//the amount of entries that has been traversed
        Iterator<Entry> listIterator;//the iterator on the LinkedList
        int curIndex;//the index of the list in table

        /** Init the EntryIterator.
         * <p>if no entries exist in the map, set the listIterator to null<p/>
         */
        EntryIterator() {
            count = 0;
            curIndex = 0;
            if(size == 0)
                listIterator=null;
            findNextList();
        }

        public boolean hasNext() {
            return count != size;
        }

        public Entry nextEntry() {
            if (hasNext()) {//if the hashtablemap have next value
                count++;
                if (listIterator.hasNext()){//if the current list have next value
                    return listIterator.next();
                }
                else {
                    findNextList();
                    return listIterator.next();
                }
            } else
                throw new NoSuchElementException();
        }

        public void remove () {
            count--;
            size--;
            listIterator.remove();
        }

        //find the  curIndex of the next available list, set the iterator
        public void findNextList () {
            ++curIndex;
            while (table[curIndex]==null || table[curIndex].size() == 0) {
                ++curIndex;
                curIndex %= capacity;
            }
            listIterator = table[curIndex].iterator();
        }

    }

    class ValueIterator extends EntryIterator implements Iterator<ValueType>{
        public ValueType next(){
            return (ValueType) nextEntry().v;
        }

    }
    @Override
    public Iterator<ValueType> iterator() {
        return new ValueIterator();
    }

    @Override
    public void forEach(Consumer action) {
        IterableMapADT.super.forEach(action);
    }

    @Override
    public Spliterator<ValueType> spliterator() {
        return (Spliterator<ValueType>) IterableMapADT.super.spliterator();
    }
    /**
     *return the loadFactor of the table
     * @return the load factor
     */
    public double getLoadfactor(){
        return (double)size/(double)capacity;
    }
    /**
     * init the HashtableMap with an assigned capacity
     * @param initialCapacity initial capacity
     */
    public IterableMap(int initialCapacity){
        size=0;
        capacity=initialCapacity;
        table=new LinkedList[initialCapacity];
    }

    /**
     * default constructor, set the initialCapacity to 15
     */
    public IterableMap(){
        this(15);
    }

    /**
     * add the key-value pair to the HashtableMap, if the key is previously included or key equals to null, return false.
     * @param key the key
     * @param value the value
     * @return true after successfully storing a new key-value pair,
     *         return false if the key is previously included or key equals to null.
     */
    @Override

    public boolean put(KeyType key, ValueType value) {
        if(key==null) {
            return false;
        }
        else {
            if(!containsKey(key)) {
                int index=getHashIndex(key);
                if(table[index] == null)//initiate the LinkedList if it was not previously initiated
                    table[index] = new LinkedList<>();

                table[index].add(new Entry(key, value));
                size++;

                if(getLoadfactor()>=0.7)//rehash the HashMapTable if the size is too large
                    rehash();
                return true;
            }
            else {//if the key is previously included,return false
                return false;
            }
        }
    }

    /**
     * get the index of the HashtableMap array where the entries should be stored in
     * @param key the key
     * @return the index of the HashtableMap array where the entries should be stored in
     */
    public int getHashIndex(KeyType key){
        int index=Math.abs(key.hashCode());//hashcode maybe negative, thus Math.abs is necessary
        return index%capacity;
    }

    /**
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping
     *                                for the key
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException{
        int index=getHashIndex(key);//get the index of the list which probably have the key
        if(table[index]!=null){
            for(Entry<KeyType,ValueType> e:table[index]){//traverse all the value in a specific list
                if(e.k.equals(key)){
                    return e.v;
                }
            }
        }
        throw new NoSuchElementException();//the key is not found
    }

    /**
     *remove the key from the hashtable map
     * @param key the key
     * @return null if the key can't be found,
     *        return the corresponding value If the key can be found
     */
    @Override

    public ValueType remove(KeyType key) {
        int index=getHashIndex(key);//get the index of the list which probably have the key
        if(table[index]!=null) {
            for (Entry<KeyType, ValueType> e : table[index]) {//traverse all the value in a specific list
                if (e.k.equals(key)) {
                    table[index].remove(e);
                    size--;//the amount of total entries should minus 1 since an entry was removed
                    return e.v;
                }
            }
        }
        return null;
    }

    /**
     * Returns true if this map contains such a key.
     * @param key the key which is to be tested
     * @return true if map contains such a key
     */
    @Override
    public boolean containsKey(KeyType key) {
        try {
            get(key);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * Returns how many entries is contained in this map
     * @return the amount of entries contained in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove all the entries in table
     */
    @Override
    public void clear() {
        table=new LinkedList[capacity];
        size=0;
    }

    /**
     * enlarge the size of the HashMapTable, then rehash all the entries which are previously stored.
     */

    public void rehash(){
        this.capacity*=2;//enlarge the size of the HashMapTable

        LinkedList<Entry>[] oldTable=table;
        table=new LinkedList[capacity];
        size=0;//the size is added in put() method, thus it should be set to 0 before putting all the entries into new table
        for(LinkedList<Entry> cur_list:oldTable){//add all the entries into the new table
            if(cur_list!=null)
                for(Entry e:cur_list)
                    put((KeyType) e.k, (ValueType) e.v);
        }
    }
}
/**
 * a key-value pair which is stored in table
 */
class Entry<KeyType,ValueType> {
    KeyType k;
    ValueType v;

    public Entry(KeyType key, ValueType value) {
        this.k= key;
        this.v=value;
    }

}
