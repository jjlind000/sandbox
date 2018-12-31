package com.company;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation1{} //marker annotation

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface MyAnnotation2{
    String s() default "abc";

    enum Priority {
        LOW, MEDIUM, HIGH;
    }
    Priority priority() default Priority.MEDIUM;

    String[] tags() default "";

    boolean enabled() default false;
 
}

@MyAnnotation1
class X {}

@MyAnnotation2(enabled = true)
public class AnnotationsExample
{

    static Class<AnnotationsExample> main = AnnotationsExample.class;


    public static void main(String[] args) {
        Annotation c0 = AnnotationsExample.class.getAnnotation(MyAnnotation2.class);      //MyAnnotation2 extends annotation.Annotation
        MyAnnotation2 c1 = AnnotationsExample.class.getAnnotation(MyAnnotation2.class);   //call AnnotationsExample.class.getAnnotation
        MyAnnotation2 c2 = main.getAnnotation(MyAnnotation2.class);         //call getAnnotation on the object main
        System.out.printf("s: %s, priority: %s%n", c1.s(), c1.priority());
        System.out.printf("s: %s, priority: %s%n", c2.s(), c2.priority());
        System.out.printf("%s: isAnnotationPresent(%s)? %b %n", main.getSimpleName(), MyAnnotation1.class.getSimpleName(), main.isAnnotationPresent(MyAnnotation1.class));
        System.out.printf("%s: isAnnotationPresent(%s)? %b %n", main.getSimpleName(), MyAnnotation2.class.getSimpleName(), main.isAnnotationPresent(MyAnnotation2.class));


        for(Method m : main.getDeclaredMethods()){
            System.out.printf("%s: method name:%s, isAnnotationPresent(%s)? %b%n", main.getSimpleName(), m.getName(), MyAnnotation1.class.getSimpleName(), m.isAnnotationPresent(MyAnnotation1.class));
            System.out.printf("%s: method name:%s, isAnnotationPresent(%s)? %b%n", main.getSimpleName(), m.getName(), MyAnnotation2.class.getSimpleName(), m.isAnnotationPresent(MyAnnotation2.class));
            //@SuppressWarnings returns false when queried with isAnnotationPresent at runtime because it's defined with RetentionPolicy.SOURCE
            System.out.printf("%s: method name:%s, isAnnotationPresent(%s)? %b%n", main.getSimpleName(), m.getName(), SuppressWarnings.class.getSimpleName(), m.isAnnotationPresent(SuppressWarnings.class));
            if(m.isAnnotationPresent(MyAnnotation2.class) && m.getAnnotation(MyAnnotation2.class).enabled())
                try
                {
                    m.invoke(null,m.getAnnotation(MyAnnotation2.class).s()); //for static methods, the instance argument is ignored; it can be null
                    m.invoke(main, m.getAnnotation(MyAnnotation2.class).s());
                } catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                } catch (InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            else {
                System.out.println("Not enabled!");
            }

        }
    }

    @SuppressWarnings("unchecked") //returns false when queried with isAnnotationPresent at runtime because it's defined with RetentionPolicy.SOURCE
    @MyAnnotation1
    void foo(){}

    @MyAnnotation2(s="Hello world", enabled=true)
    static void bar(String message){
        System.out.println(message + "!");
    }

}
