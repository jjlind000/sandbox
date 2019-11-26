package org.jasonlind;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        testRemoveFieldAfterSerButUIDSame();

    }


    public static void testRemoveFieldAfterSerButUIDSame(){
        //shows you can deserialize an B from a serialized B when the class has changed after serialization (a field is REMOVED after serialization) but has same UID
        //steps: serialize B as i,j,x
        //       change B - REMOVE x from class, but keep UID same (1L)
        //       deserialize
        //serializeB();
        deserializeB();

    }

    public static void testAddFieldAfterSerButUIDSame(){
        //shows you can deserialize an A from a serialized A when the class has changed after serialization (a field is added after serialization) but has same UID
        //steps: serialize A as i,j
        //       change A - add an x, but keep UID same (1L)
        //       deserialize with class as the new structure
        //serializeA();
        deserializeA();

    }



/*
    public static void serializeB(){
        B b = new B(1,2 , "three", "four");
        String filename="/home/jasonl/Documents/tmp/b.ser";
        serialize(b, filename);
    }
*/


    public static void deserializeB(){
        String filename="/home/jasonl/Documents/tmp/b.ser";
        deserialize(B.class, filename);
    }

    public static void serializeA(){
        A a = new A(1,2);
        String filename="/home/jasonl/Documents/tmp/a.ser";
        serialize(a, filename);
    }
    public static void deserializeA(){
        String filename="/home/jasonl/Documents/tmp/a.ser";
        deserialize(A.class, filename);
    }


    public static void tryDeserializeZFromZ(){
        //shows callback readObject is called
        Z z = new Z(1,2);
        String filename="/home/jasonl/Documents/tmp/z.ser";
        serialize(z, filename);
        deserialize(Z.class, filename);
    }

    public static void tryDeserializeZFromX(){
        //shows you cannot deserialize a Z from an X even if Z and X have identical structures and Z has readObject callback method
        X x = new X(1,2);
        Z z = new Z(1,2);
        String xfilename="/home/jasonl/Documents/tmp/x.ser";
        //String yfilename="/home/jasonl/Documents/tmp/y.ser";
        serialize(x, xfilename);
        //serialize(y, Y.class, yfilename);
        //deserialize(X.class, xfilename);
        deserialize(Z.class, xfilename);
    }



    public static void tryDeserializeYFromX(){
        //shows you cannot deserialize a Y from an X even if X and Y have identical structures
        X x = new X(1,2);
        Y y = new Y(1,2);
        String xfilename="/home/jasonl/Documents/tmp/x.ser";
        String yfilename="/home/jasonl/Documents/tmp/y.ser";
        serialize(x, xfilename);
        serialize(y, yfilename);
        deserialize(X.class, xfilename);
        deserialize(Y.class, xfilename);
    }

    public static void serialize(Object o, String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(o);
            System.out.println(String.format("Object written to %s",filename));
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    public static void deserialize(Class clazz, String filename){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            switch(clazz.getSimpleName()){
                case "A":
                    System.out.println("deser as A");
                    final A a = (A) ois.readObject();
                    System.out.println(String.format("Object read from %s as %s",filename, a));
                    break;
                case "B":
                    System.out.println("deser as B");
                    final B b = (B) ois.readObject();
                    System.out.println(String.format("Object read from %s as %s",filename, b));
                    break;
                case "X":
                    System.out.println("deser as X");
                    final X x = (X) ois.readObject();
                    System.out.println(String.format("Object read from %s as %s",filename, x));
                    break;
                case "Y":
                    System.out.println("deser as Y");
                    final Y y = (Y) ois.readObject();
                    System.out.println(String.format("Object read from %s as %s",filename, y));
                    break;
                case "Z":
                    System.out.println("deser as Z");
                    final Z z = (Z) ois.readObject();
                    System.out.println(String.format("Object read from %s as %s",filename, z));
                    break;
                default:
                    System.out.println(String.format("deser: unexpected class %s", clazz.getName()));
                    return;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

class B implements Serializable
{
    private static final long serialVersionUID = 1L;

/*    private void readObject(ObjectInputStream ois) throws Exception
    {
        try
        {
            System.out.println("B readobject");
            ois.defaultReadObject();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }*/


    public B(){
        System.out.println("B()");
    }
    public B(int i, int j){
        System.out.println("B(i,j)");
        this.i=i;
        this.j=j;
    }


/*
    public B(int i, int j, String x, String y)
    {
        this.i = i;
        this.x = x;
        this.j = j;
        this.y = y;
    }
*/


    private int i;
    //private String x;
    private int j;
    //private String y;

    @Override
    public String toString()
    {
        return "B{" +
                "i=" + i +
//                ", x='" + x + '\'' +
                ", j=" + j +
//                ", y='" + y + '\'' +
                '}';
    }
}
class A{

    public A(){
        System.out.println("A()");
    }
    public A(int i, int j){
        System.out.println("A(i,j)");
        this.i=i;
        this.j=j;
    }

    int i;
    int x;
    int j;

    @Override
    public String toString()
    {
        return "A{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}


class X implements Serializable
{
    public X(){
        System.out.println("X()");
    }
    public X(int i, int j){
        System.out.println("X(i,j)");
        this.i=i;
        this.j=j;
    }

    int i;
    int j;

    @Override
    public String toString()
    {
        return "X{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}

class Y implements Serializable
{
    public Y()
    {
        System.out.println("Y()");
    }

    public Y(int i, int j)
    {
        System.out.println("Y(i,j)");
        this.i = i;
        this.j = j;
    }

    int i;
    int j;

    @Override
    public String toString()
    {
        return "Y{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
class Z implements Serializable {
    private void readObject(ObjectInputStream ois) throws Exception
    {
        try
        {
            System.out.println("Z readobject");
            ois.defaultReadObject();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public Z(){
        System.out.println("Z()");
    }
    public Z(int i, int j){
        System.out.println("Z(i,j)");
        this.i=i;
        this.j=j;
    }

    int i;
    int j;

    @Override
    public String toString()
    {
        return "Z{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
