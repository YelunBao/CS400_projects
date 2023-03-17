import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

public class Backend implements IBackend {

        // Object rbt from IRedBlackTree interface.
        private IRedBlackTree<IPhoneBook> rbt;
        private String name;
        private String phoneNum;
        private String number;
        AE_Validator validator = new AE_Validator();
        IPhoneBook contact;

        // constructor that reference the implemented methods from BD_RedBlackTree to
        // the rbt
        public Backend() {
                this.rbt = new BD_RedBlackTree();
        }

        public Backend(IRedBlackTree<IPhoneBook> rbt) {
                this.rbt = rbt;
        }

        @Override
        public boolean addContact(String name, String phoneNum) {
                // create a phoneBook object that has been passed in as name and phoneNum.
                BD_IPhoneBookPlaceHolder phoneBook = new BD_IPhoneBookPlaceHolder(name, phoneNum);
                // insert the contact.
                rbt.insert(phoneBook);
                return true;

        }

        @Override
        public boolean removeContact(String name, String phoneNum) {
                // create a phoneBook object that has been passed in as name and phoneNum.
                BD_IPhoneBookPlaceHolder phoneBook = new BD_IPhoneBookPlaceHolder(name, phoneNum);
                // remove the contact.
                rbt.remove(phoneBook);
                return true;
        }

        @Override
        public List<IPhoneBook> search(String nameKeyWord) {
                ArrayList<IPhoneBook> list = new ArrayList<IPhoneBook>();
                //In the loop check if the name contains the keyword.
                for(IPhoneBook contact : rbt) {
                        if(contact.getName().contains(nameKeyWord)) {
                                //If the name contains the keyword, add to the list.
                                list.add(contact);
                        }
                }
                return list;

        }

        @Override
        public boolean validator(String phoneNum) throws DataFormatException {

                return validator.isValid(phoneNum);

        }

        @Override
        public List<IPhoneBook> getAllContacts() {
                // create an arraylist of contact_list.
                ArrayList<IPhoneBook> contact_list = new ArrayList<IPhoneBook>();
                // Add the contacts to the iterator.
                for (IPhoneBook contact : rbt) {
                        contact_list.add(contact);
                }

                return contact_list;
        }

}
