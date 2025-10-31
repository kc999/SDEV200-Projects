import java.util.Arrays;
import java.util.Scanner;
public class assignment8_29 {
    public static boolean compareArrays2D(String[][] array1, String[][] array2)
    {
        if (Arrays.deepEquals(array1, array2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args)
    {
        int arrayRows = 3;
        int arrayCols = 3;
        String[][] m1 = new String[arrayRows][arrayCols];
        String[][] m2 = new String[arrayRows][arrayCols];

        Scanner scan = new Scanner(System.in);
        //First Array
        //Iterate over cols
        for (int i= 0; i< arrayCols; i++)
        {
            //Iterate over rows
            for (int j = 0; j<arrayRows; j++)
            {
                System.out.println("Enter value for Array 1, position " + (i+1) + " , " + (j+1));
                m1[i][j] = scan.nextLine();
            }
        }
        System.out.println("Array 1 values: " + Arrays.deepToString(m1) );
        //Second Array
        //Iterate over cols
        for (int i= 0; i< arrayCols; i++)
        {
            //Iterate over rows
            for (int j = 0; j<arrayRows; j++)
            {
                System.out.println("Enter value for Array 2, position " + (i+1) + " , " + (j+1));
                m2[i][j] = scan.nextLine();
            }
        }
        System.out.println("Array 2 values: " + Arrays.deepToString(m2));

        if (compareArrays2D(m1, m2))
        {
            System.out.println("Arrays\n" + Arrays.deepToString(m1) + "\n" + Arrays.deepToString(m2) + "\n Are equal." );
        }
        else
        {
            System.out.println("Arrays\n" + Arrays.deepToString(m1) + "\n" + Arrays.deepToString(m2) + "\n Aren't equal.");
        }
        

    }
}
