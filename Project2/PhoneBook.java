public class PhoneBook implements IPhoneBook{
    String name;
    String phone_number;

    public PhoneBook(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPhoneNumber() {
        return this.phone_number;
    }

    @Override
    public int compareTo(IPhoneBook that) {
        //return this.compareTo(that);
	int compareName = that.getName().compareTo(this.getName());
    	int compareNum = that.getPhoneNumber().compareTo(this.getPhoneNumber());

    	if (compareName < 1) {
    		return -1;
    	}
    	
    	else if (compareName > 1 ) {
    		return 1;
    	}
    	
    	else {
    		if (compareNum < 1) {
    			return -1;
    		}
    		
    		else if (compareNum > 1) {
    			return 1;
    		}
    		
    		return 0;
    	}
    }
}
