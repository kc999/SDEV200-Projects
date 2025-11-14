// Look for WRITE YOUR CODE to write your code
import java.math.*;
import java.util.Scanner;

public class assignment13_15 {
  public static void main(String[] args) {
    // Prompt the user to enter two Rational numbers
    Scanner input = new Scanner(System.in);
    System.out.print("Enter rational r1 with numerator and denominator seperated by a space: ");
    String n1 = input.next();
    String d1 = input.next();

    System.out.print("Enter rational r2 with numerator and denominator seperated by a space: ");
    String n2 = input.next();
    String d2 = input.next();

    RationalUsingBigInteger r1 = new RationalUsingBigInteger(
      new BigInteger(n1), new BigInteger(d1));
    RationalUsingBigInteger r2 = new RationalUsingBigInteger(
      new BigInteger(n2), new BigInteger(d2));

    // Display results
    System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
    System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
    System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
    System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
    System.out.println(r2 + " is " + r2.doubleValue());
    input.close();
  }
}

// Name the revised Rational class RationalUsingBigInteger 
class RationalUsingBigInteger extends Number 
    implements Comparable<RationalUsingBigInteger> {
  // Data fields for numerator and denominator
  private BigInteger numerator = BigInteger.ZERO;
  private BigInteger denominator = BigInteger.ONE;

  
  public RationalUsingBigInteger()
  {
     this(BigInteger.ZERO,BigInteger.ONE);
  }
  public RationalUsingBigInteger(BigInteger n1, BigInteger n2)
  {
    BigInteger gcd = n1.gcd(n2);
    System.out.println("GCD Compare" +gcd.compareTo(BigInteger.ONE));
    if (gcd.compareTo(BigInteger.ZERO) != 0) 
    {
        this.numerator = n1.divide(gcd);
        this.denominator = n2.divide(gcd);
        System.out.println("Greatest common denominator detected as " + gcd);
    }
    
  }
  public BigInteger getNumerator(){
    return numerator;
  }
  public BigInteger getDenominator(){
    return denominator;
  }
  public RationalUsingBigInteger add(RationalUsingBigInteger r2)
  {
    BigInteger n1 = numerator.multiply(r2.getDenominator());
    BigInteger n2 = denominator.multiply(r2.getNumerator());
    BigInteger n1Total = n1.add(n2);
    BigInteger d = denominator.multiply(r2.getDenominator());
    

    return new RationalUsingBigInteger(n1Total,d);
  }
  public RationalUsingBigInteger subtract(RationalUsingBigInteger r2)
  {
     BigInteger n1 = numerator.multiply(r2.getDenominator());
    BigInteger n2 = denominator.multiply(r2.getNumerator());
    BigInteger n1Total = n1.subtract(n2);
    BigInteger d = denominator.multiply(r2.getDenominator());
    

    return new RationalUsingBigInteger(n1Total,d);
  }
  public RationalUsingBigInteger multiply(RationalUsingBigInteger r2)
  {
    BigInteger n1 = numerator.multiply(r2.getNumerator());
    BigInteger d1 = denominator.multiply(r2.getDenominator());
    return new RationalUsingBigInteger(n1,d1);
  }
  public RationalUsingBigInteger divide(RationalUsingBigInteger r2)
  {
    BigInteger n1 = numerator.multiply(r2.denominator);
    BigInteger d1 = denominator.multiply(r2.numerator);

    return new RationalUsingBigInteger(n1,d1);
  }
  @Override 
  public String toString()
  {
    String str = getNumerator().toString() + "/" + getDenominator().toString();
    return str;
  }
  @Override
  public int intValue()
  {

    return numerator.intValue() / numerator.intValue();
  }
  @Override
  public double doubleValue()
  {
    return numerator.doubleValue() / numerator.doubleValue();
  }
  @Override
  public long longValue()
  {
    return numerator.longValue() / denominator.longValue();
  }
  @Override 
  public float floatValue()
  {
    return numerator.floatValue() / denominator.floatValue();
  }
  @Override
  public int compareTo(RationalUsingBigInteger r2)
  {
    int test1 = numerator.compareTo(r2.getNumerator());
    int test2 = denominator.compareTo(r2.getDenominator());
    if (test1 == 0 && test2 == 0)
    {
      return 0;
    }
    if (test1 > 0 || test2 > 0)
    {
      return 1;
    }
    else return -1;
  }
}
