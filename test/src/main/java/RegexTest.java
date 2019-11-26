import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jasonl on 04/09/19
 */
public class RegexTest
{
    final static Pattern pattern = Pattern.compile("Player\\d+");

    public static void main(String... args){

        final String s = "Player24";
        Matcher m = pattern.matcher(s);
        System.out.println(m.matches());


    }
}
