import java.io.IOException;
import java.util.*;

public class OptionalFlatMapTest
{
    //returns a String
    static String doubleToString(Double d){
        return d.toString();
    }

    //returns an Optional
    static Optional<String> doubleToStringOptional(Double d){
        return d==null? Optional.empty() : Optional.of(d.toString());
    }

    public static void main(String[] args) throws IOException
    {
        Optional<Double> opt = Optional.of(2.0);

        //apply a map that will take the contents of the optional and apply a Function(Double,String) to it, then wrap
        //the result in an Optional, i.e. returning an Optional<String>
        final Optional<String> s1 = opt.map(OptionalFlatMapTest::doubleToString);

        //apply a Function(Double,Optional<String>) using map, then because the result will be wrapped in an Optional,
        //it will return an Optional<Optional<String>>
        final Optional<Optional<String>> s2 = opt.map(OptionalFlatMapTest::doubleToStringOptional);

        //but if instead we apply the Function(Double,Optional<String>) using flatMap; flatMap is defined to take a fn
        //that returns an Optional<U>, and return an Optional<U>, so whatever Optional the fn returns, will be returned
        //as-is by flatmap; in this case then it will return an Optional<String>
        final Optional<String> s3 = opt.flatMap(OptionalFlatMapTest::doubleToStringOptional);
    }
}
