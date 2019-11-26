package com.app;

import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        //stringlist();;
        //regex();
        //paymentMethodRefs();
        //suppliertest();
        pass();
    }

    private static void pass()
    {
        Dog dog = new Dog("rex");
        System.out.println("B4 foo: " + dog);
        foo(dog);
        System.out.println("After foo: " + dog);
    }

    static class Dog{
        String name;
        Dog(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "Dog{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    private static void foo(Dog someDog)
    {
        someDog.setName("Max");     // AAA
        someDog = new Dog("Fifi");  // BBB
        someDog.setName("Rowlf");   // CCC
    }

    private static void suppliertest()
    {
        foo(App::getString);
    }

    private static void foo(Supplier<String> supplier){
        System.out.println(supplier.get());
    }

    private static String getString(){
        System.out.println("generating string...");
        return "test string";
    }

    private static void paymentMethodRefs()
    {

        String paymentProviderRef = "PAYPAL-APIx";
        final Optional<String> paymentMethodReference = Arrays.stream(PaymentMethodRefs.class.getDeclaredFields()).filter(f -> Modifier.isStatic(f.getModifiers()))
                .map(methodRef -> {
                    try
                    {
                        return paymentProviderRef.toUpperCase().equals(methodRef.get(null)) ? paymentProviderRef.toUpperCase() : null;
                    } catch (IllegalAccessException e)
                    {
                        return null;
                    }
                }).filter(Objects::nonNull).findAny();

        System.out.println("paymentMethodRef:" + paymentMethodReference.orElse(" **not found - " + paymentProviderRef + "**"));

    }

    private static void regex()
    {
        final String INDEX_SUFFIX = "/index.html";
        final String SITEMAP_SUFFIX = "/sitemap.xml";
        final String SLASH_SUFFIX = "/";

        Pattern pattern = Pattern.compile(".*" + INDEX_SUFFIX  + "|" + ".*" + SITEMAP_SUFFIX + "|.*" + SLASH_SUFFIX + "$");

        String[] tests = new String[] { "/foo.xml", "/foo", "/foo/", "/foo.html", "/foo/index.html", "/sitemap.xml", "sitemap", "sitemap/", "/index", "/index/", "/index.htm", "index.html", "/index.html", "/", ""} ;
        for(String servletPath : tests)
        {
            final Matcher matcher = pattern.matcher(servletPath);
            System.out.println(String.format("%s matches: %s %s", servletPath, matcher.matches(), (!matcher.matches() ? " ==> " + servletPath + INDEX_SUFFIX : " (UNCHANGED) ")));
        }

    }

    static void df(){
//        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        System.out.println( simpleDateFormat.format(new Date()));

    }

    static void stringlist(){
        List<Integer> list = new ArrayList<>();
        list.add(3); list.add(5); list.add(7);

        list = null;
        System.out.println(list.toString());
    }
}
