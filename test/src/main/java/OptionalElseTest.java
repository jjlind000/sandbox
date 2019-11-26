import java.io.IOException;
import java.util.Optional;

public class OptionalElseTest
{
    //returns a String
    static String doubleToString(Double d){
        System.out.println("doubleToString(Double d)");
        return "doubleToString ===> " + d.toString();
    }

    //returns a String
    static String doubleToString(){
        System.out.println("doubleToString()");
        return "doubleToString ===> nothing here ";
    }


    public static void main(String[] args) throws IOException
    {
        Optional<Double> opt = Optional.of(2.0);

        System.out.println("s1 get() test...");
        String s1 = opt.map(OptionalElseTest::doubleToString).get();
        System.out.println("s1 get(): " + s1);

        System.out.println("s1 orElse() test...");
        s1 = opt.map(OptionalElseTest::doubleToString).orElse(OptionalElseTest.doubleToString());
        System.out.println("s1 orElse: " + s1);

        System.out.println("s1 orElseGet() test...");
        s1 = opt.map(OptionalElseTest::doubleToString).orElseGet(OptionalElseTest::doubleToString);
        System.out.println("s1 orElseGet: " + s1);

/*
        Optional<Double> optempty = Optional.empty();

        final String s1 = opt.map(OptionalElseTest::doubleToString).get();
        System.out.println("s1: " + s1);
*/

    }
}
