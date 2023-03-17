import java.util.List;
import java.util.ArrayList;

public class BackendHelperFD implements IBackend{
	public List<IPhoneBook> list;
	
	public BackendHelperFD() {
		list = new ArrayList();
	}
	@Override
	public boolean addContact(String name, String phoneNum) {
		PhoneBookFD phone = new PhoneBookFD(name, phoneNum);
		list.add(phone);
		return true;
	}
        @Override
	public boolean removeContact(String name, String number) {
		list.remove(0);
		return true;
        }
        @Override
	public List<IPhoneBook> search(String nameKeyWord) {
		List<IPhoneBook> list2 = new ArrayList();
		for (int i = 0; i < list.size(); ++i) {
			if (nameKeyWord.equals(list.get(i).getName())) {
				list2.add(list.get(i));
			}
		}

		return list;
	}

	@Override
	public List<IPhoneBook> getAllContacts() {
		PhoneBookFD book = new PhoneBookFD("Bill", "2222222222");
		list.add(book);
		PhoneBookFD book2 = new PhoneBookFD("Dave", "3333333333");
		list.add(book2);
		
		return list;
	}

	@Override
	public boolean validator (String phoneNumber) {
		return true;
	}
}
