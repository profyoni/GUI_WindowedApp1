import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Regex {

    public static void main(String[] args){
        String regex = args[0];
        String searchText = args[1];

            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(searchText);

            boolean found = false;
            while (matcher.find()) {
                System.out.println(String.format("I found the text" +
                                " \"%s\" starting at " +
                                "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end()));
                found = true;
                for (int i=1;i<=matcher.groupCount();i++)
                    System.out.println(i + ":" + matcher.group(i));
            }
            if(!found){
                System.out.println("No match found.%n");
            }
        }
    }

