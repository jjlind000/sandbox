import java.lang.reflect.Field;

public class Main {

    public int i=10;


    public static void main(String[] args) {
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
