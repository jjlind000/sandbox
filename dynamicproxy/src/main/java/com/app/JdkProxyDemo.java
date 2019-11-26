package com.app;

import java.lang.reflect.Proxy;

public class JdkProxyDemo {


    public static void main(String[] args){

        Foo fooImpl = new FooImpl();

        Handler handler = new Handler(fooImpl);

        Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),

                new Class[] { Foo.class },

                handler);

        f.doSomething("Hallo");

    }

}