import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public int i=10;


    public static void main(String[] args)
    {

        f6();

    }


    static void f6(){
        Instant sevenDaysAgo = Instant.now().plus(-7, ChronoUnit.DAYS);
        System.out.println(sevenDaysAgo);
        Calendar cal = new GregorianCalendar(2019,1, 10);
        Date date = cal.getTime();
        System.out.println(date);
        System.out.println("is before 7 days ago:" + date.toInstant().isBefore(sevenDaysAgo));

    }

    static void f5(){
        Map<String, Object> map = new HashMap<>();
        String s = (String) map.get("SOMEKEY");
        System.out.println("s:"+s);
    }

    static void f4(){
        // FIXME - temporary fix BACKOFFICE-384
        final Calendar cal = Calendar.getInstance();
        Date storedDob = null;
        cal.setTime(storedDob);
        cal.set(Calendar.HOUR, 0);
        storedDob = cal.getTime();

        if(storedDob == null){
            System.out.println("isnull");
        } else {
            System.out.println("is not null");
        }


    }


    static void f3(){
        double d = 1868916962.01;
        System.out.println(String.valueOf(d));
        System.out.println(String.format("%.2f",d));
        System.out.println(new BigDecimal(d).toPlainString());
    }

    static void callf2(){
        List<Double> totals = Arrays.asList(1.03, 1.04);
        f2(totals);
    }

    static void f2(List<Double> totals){
        List<String> outcomeTypes = Arrays.asList("OVER", "UNDER");
        List<String> strings = totals.stream().map(total -> {String s = null;
                                                             for (String outcomeType : outcomeTypes){
                                                                    System.out.println("outcomeType: " + outcomeType);
                                                                    if(outcomeType.equals("OVER")){
                                                                        System.out.println("--equals OVER:" + outcomeType);
                                                                    } else {
                                                                        System.out.println("--NOT equals OVER:" + outcomeType);
                                                                    }
                                                                    s = outcomeType.equals("OVER") ? "X" : "Y";
                                                                    //return s;
                                                                 } ;
                                                            return s;}).collect(Collectors.toList());
        System.out.println(strings);
    }

    static void f1(){
        System.out.println("Hello World!!!");

        Main main = new Main();
        try
        {
            Field intField = main.getClass().getDeclaredField("i");
            System.out.printf("Value of i: %d%n", intField.getInt(main));
            main.i=50;
            System.out.printf("Value of i: %d%n", intField.getInt(main));

        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
