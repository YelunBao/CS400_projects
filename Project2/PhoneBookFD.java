
public class PhoneBookFD implements IPhoneBook{
	public String name;
	public String number;

	public PhoneBookFD(String name, String number) {
		this.name = name;
		this.number = number;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getPhoneNumber() {
          	return number;
       }
	   @Override public int compareTo(IPhoneBook that) {
		return 1;
       }
}
