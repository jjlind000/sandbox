import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main3
{
    public static void main(String ... args){
        testCompareTo();
    }

    static void testCompareTo(){
        BigDecimal config = BigDecimal.valueOf(3.3);
        BigDecimal value = BigDecimal.valueOf(2.3);
        compareTo(config, value);
        config = BigDecimal.valueOf(2.3);
        value = BigDecimal.valueOf(3.3);
        compareTo(config, value);
    }

    static void compareTo(BigDecimal config, BigDecimal value){
        System.out.printf("config:%s, value:%s, %s.compareTo(%s): %s, %s.compareTo(%s) != -1 == %b,%s.compareTo(%s)>=0 == %b %n"
                , config.toPlainString(), value.toPlainString()
                , config.toPlainString(), value.toPlainString()
                , config.compareTo(value)
                , config.toPlainString(), value.toPlainString()
                , config.compareTo(value) != -1
                , config.toPlainString(), value.toPlainString()
                , config.compareTo(value) >=0
        );

        //new BigDecimal(config).compareTo(value) != -1


    }

}
