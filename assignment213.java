import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class assignment213
{

    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a java source file");
        String stringFilePath = input.nextLine();
        Path filePath = Paths.get(stringFilePath);
        System.out.println("File path is " + stringFilePath);
        System.out.println("File contains: " + countKeywords(filePath) + " keywords.");
        
    }
    public static int countKeywords(Path filePath) 
    {
        String[] keyWordString = { "abstract", "assert", "boolean", "break", "byte",
        "case", "catch", "char", "class", "const", "continue", "default", "do", "double", 
        "else", "enum", "extends", "for", "final", "finally", "float", "goto", "interface",
        "long", "native", "new", "package", "private", "protected", "public", "return", "short", 
        "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient",
        "try", "void", "volatile", "while", "true", "false", "null"
        };

        Set<String> keyWordSet  = new HashSet<>(Arrays.asList(keyWordString));
        String input = "";
        int count = 0;
        try{
            input = Files.readString(filePath);
        }
        catch (IOException e)
        {
            System.out.println("Couldn't find file");
            System.exit(-1);
        }
        //If string isn't empty, begin regex operations
        if (!input.isEmpty())
        {
            //Replace quotes with empty space
            input = input.replaceAll("\"(.*?)\"","");
            //Replace comment lines with empty space
            input = input.replaceAll("/([^/]*)/.","");
            //Replace comment blocks with empty space
            input = input.replaceAll("/\\*[\\s\\S]*?\\*/","");
        }
        //Now that comments, comment blocks and strings are replaced, count keywords
        for (String key: keyWordSet)
        {
            if (input.contains(key))
            {
                count++;
            }
        }
        return count;
    }   
}
