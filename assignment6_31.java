import java.util.Scanner;
public class assignment6_31{
    public static boolean isNumber(String number)
    {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isValid(String cardNumber)
    {
        int cardLength = cardNumber.length();
        //Check if card number is valid length
        if (cardLength < 13 || cardLength >16)
        {
            System.out.println("Card length is not valid.");
            return false;
        }
        //Check for non-numeric character
        if (!isNumber(cardNumber))
        {
            System.out.println("Non number found in card number entry.");
            return false;
        }
        char[] validNumbers = {'4','5','6'};
        String americanExpressNumber = "37";
        char firstChar = cardNumber.charAt(0);
        String firstTwoNumbers = cardNumber.substring(0,2);
        if (firstChar != validNumbers[0] && firstChar != validNumbers[1] && firstChar != validNumbers[2] && !firstTwoNumbers.equals(americanExpressNumber))
        {
            System.out.println("Entered Card number does not match format of known card manufacturers.");
            return false;
        }
        //System.out.println("Card is valid");
        return true;
    }
    public static int doubleNumToSingle(int number)
    {
        String stringifiedNum = String.valueOf(number);
        //System.out.println("Stringified Num is " +stringifiedNum);
        int char1 = stringifiedNum.charAt(0);
        int char2 = stringifiedNum.charAt(1);
        int num1 = Character.getNumericValue(char1);
        int num2 = Character.getNumericValue(char2);
        return num1 + num2;
    }
    public static int doubleEvenPlace(String cardNumber)
    {
        int totalSum = 0;
        //System.out.println(cardNumber.length()-2);
        for (int i = cardNumber.length()-2; i >= 0; i-=2)
        {
            char digitChar = cardNumber.charAt(i);
            int charValue = Character.getNumericValue(digitChar);

            //System.out.println("Number found at " + i +" is " + charValue);
            charValue *= 2;
            //System.out.println("Doubled number is " + charValue);
            if (charValue >= 10)
            {
                //System.out.println("Number too large, splitting and adding values.");
                charValue = doubleNumToSingle(charValue);
                //System.out.println("New number is " + charValue);
            }
            totalSum += charValue;
            //System.out.println("Total value of sums is currently... " + totalSum);
        }
        return totalSum;
    }
    public static int addOddSpaces(String cardNumber)
    {
        int totalSum = 0;
        for (int i = 1; i<=cardNumber.length()-1; i+=2)
        {
            char digitChar = cardNumber.charAt(i);
            int charValue = Character.getNumericValue(digitChar);

            //System.out.println("Number found at " + i +" is " + charValue);
            totalSum += charValue;
            //System.out.println("Total value of sums is currently... " + totalSum);
        }

        return totalSum;
    }
    public static void validSumsOfNumbers(int sums)
    {
        if (sums % 10 == 0)
        {
            System.out.println("Card Number is valid.");
        }
        else
        {
           System.out.println("Card Number is not valid.");
        }
    }
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the credit card you wish to validate, with no spaces inbetween numbers.");
        String cardEntry = scan.nextLine();
        if (isValid(cardEntry))
        {
           int sum1 = doubleEvenPlace(cardEntry);
           int sum2 = addOddSpaces(cardEntry);

           validSumsOfNumbers(sum1+ sum2);
        }
        scan.close();
    }
}