public class ISBNValidator implements IISBNValidator{

    @Override
    public boolean validate(String isbn13) {
        char[] cStr=isbn13.toCharArray();
        int sum=0;
        for(int i=0;i<cStr.length;i++)
            if((i+1)%2==1)
                sum+=(cStr[i]-'0');
            else sum+=(cStr[i]-'0')*3;
        return sum % 10 == 0;
    }
}
