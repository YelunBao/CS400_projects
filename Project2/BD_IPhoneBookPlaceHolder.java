public class BD_IPhoneBookPlaceHolder implements IPhoneBook{
	private String name;
	private String phoneNum;
	
	
	public BD_IPhoneBookPlaceHolder(String name, String phoneNum) {
		this.name = name;
		this.phoneNum = phoneNum;
		
	
	}
	
	
	

	@Override
	public String getName() {
		
		return this.name;
	}

	@Override
	public String getPhoneNumber() {
		
		return this.phoneNum;
	}

	@Override
	public int compareTo(IPhoneBook that) {
		int result = name.compareTo(that.getName());
		
		return result == 0 ? phoneNum.compareTo(that.getPhoneNumber()) : result;
		
	}

}

