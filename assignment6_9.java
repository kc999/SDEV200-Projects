
public class assignment6_9{
    public static double footToMeter(double foot)
    {
        double meter = 0.305 * foot;
        return meter;
    }
    public static double meterToFoot(double meter)
    {
        double foot = 3.279 * meter;
        return foot;
    }
    public static void main(String[] args) 
    {
        //Create Values to use in table
        double[] feetValues  = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] meterValues = {20, 25, 30, 35, 40, 45, 50, 55, 60, 65};

        int printSize = feetValues.length;

        //Start constructing Table
        System.out.printf("%-10s %-20s %-10s %-10s", "Feet", "Meter", "Meter", "Feet");
        System.out.println("\n------------------------------------------------");
        //Print values in arrays
        for (int i = 0; i < printSize; i++)
        {
            System.out.printf("%-10.3f %-20.3f %-10.3f %-10.3f%n", feetValues[i], footToMeter(feetValues[i]), meterValues[i], meterToFoot(meterValues[i]));
        }
    }
}
