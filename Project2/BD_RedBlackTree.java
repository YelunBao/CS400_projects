import java.util.ArrayList;
import java.util.Iterator;

public class BD_RedBlackTree implements IRedBlackTree<IPhoneBook>{
	//Initialize the size
	private int size = 0;
	private String name;
	private String phoneNum;

	@Override
	public boolean insert(IPhoneBook data) throws NullPointerException, IllegalArgumentException {
		size++;
		return true;
	}

	@Override
	public boolean contains(IPhoneBook data) {
		ArrayList<IPhoneBook> contact_list = new ArrayList<IPhoneBook>();
		contact_list.add((IPhoneBook) new BD_IPhoneBookPlaceHolder(name, phoneNum));
		return true;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		size = 0;
		return true;
	}

	@Override
	public Iterator<IPhoneBook> iterator() {
		ArrayList<IPhoneBook> contact_list = new ArrayList<IPhoneBook>();
		
		contact_list.add((IPhoneBook) new BD_IPhoneBookPlaceHolder(name, phoneNum));
		Iterator<IPhoneBook> iter = contact_list.iterator();
		return iter;
	}

	@Override
	public boolean remove(IPhoneBook data) {
		size--;
		return true;
	}

	


}

