import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class assignment2011 {
    public static void main(String[] args)
    {
       String fileContent = readInput();
       boolean result = parseGroupingStatements(fileContent);
       if (result) System.out.println("No errors detected");
       else if (!result) System.out.println("Grouping statements contain errors.");

    }
    public static String readInput()
    {
        //Get the path to the file to be scanned
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the filepath and filename of the file you want to check.");
        String fileName = scan.nextLine();
        Path filePath = Paths.get(fileName);
        try 
        {
            //If file is found, read contents as tring
            String fileContents = Files.readString(filePath);
            System.out.println(fileContents);
            return fileContents;
        }
        catch (IOException e)
        {
            //If file isn't found, exit
            System.out.print("Could not read/find file. Exiting.");
            System.exit(0);
        }
        return "File Could not be read";
    }
    public static boolean parseGroupingStatements(String file)
    {
        //Convert file contents to an array of characters
        Stack<Character> charStack = new Stack<>();
        char cArray[] = file.toCharArray();
        for (char c: cArray)
        {
            //Push opening character
            if (c == '(' || c == '{' || c =='[')
            {
                charStack.push(c);
            }
            else if (c == ')' || c== '}' || c == ']')
            {
                //If stack is empty and no opening statement is present, return false
                if (charStack.isEmpty())
                {
                    return false;
                }
                char lastChar = charStack.pop();
                if ((c == ')' && lastChar != '(')|| (c == '}' && lastChar != '{') || (c=='[' && lastChar != ']'))
                {
                    return false;
                }
            }
            
        }
        //Check if stack is empty or not
        if (charStack.isEmpty())
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}
