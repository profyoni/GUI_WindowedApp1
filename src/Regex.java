import java.io.Console;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Regex {

    public static void main(String[] args){
        String balls = args[0];
        int ballCount = Integer.parseInt(balls);

            Pattern pattern = Pattern.compile("\\d\\d");

            Matcher matcher = pattern.matcher("Stock price is 37 and 43");

            boolean found = false;
            while (matcher.find()) {
                System.out.println(String.format("I found the text" +
                                " \"%s\" starting at " +
                                "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end()));
                found = true;
            }
            if(!found){
                System.out.println("No match found.%n");
            }
        }
    }

