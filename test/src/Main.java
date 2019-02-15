import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public int i=10;


    public static void main(String[] args)
    {

        f4();

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
