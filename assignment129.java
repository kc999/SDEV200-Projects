import java.util.Scanner;;
public class assignment129 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the binary string you want converted.");
        String inp = scan.nextLine();
        long dec = bin2dec(inp);
        System.out.println(dec);
    }
    
    static class BinaryFormatException extends Exception
    {
        BinaryFormatException(String error)
        {
            super(error);
        }
    }
    
    public static long bin2dec(String str) 
    {
        long decValue = 0;
        try
        {
            checkBinary(str);
            decValue = Long.parseLong(str,2);
        }
        catch(BinaryFormatException e)
        {
            System.out.println("Input is not binary " + e);
        }
        return decValue;
    }
    static void checkBinary(String str) throws BinaryFormatException
    {
        if (str.isEmpty() || str == null)
        {
            throw new BinaryFormatException("String is empty or null");
        }
        for (char c: str.toCharArray())
        {
            if (c != '0' && c!= '1')
            {
                throw new BinaryFormatException("String contains characters that aren't a 0 or 1");
            }

        }
    }
    //break
    //case
    
}
