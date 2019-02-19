package com.company;

public class X {

    Y y;

    public X(){
        System.out.println("X constr");
    }

    String foo(){
        return "foo";
    }

    String bar(Object o){
        return o.getClass().getSimpleName();
    }

    String baz(int i){
        if(i<0){
            throw new IllegalArgumentException("i cannot be negative");
        }
        return "good";
    }

    int f1(int i){
        return y.doubleIt(i);
    }

}
