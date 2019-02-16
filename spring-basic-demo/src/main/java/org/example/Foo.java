package org.example;

import org.springframework.stereotype.Component;

/**
 * Created by jasonl on 08/02/19
 */
//unannotated POJO class
public class Foo
{
    String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Foo(){
        message = "(Foo) Hello to Spring Framework (default message)";
    }

    public Foo(String message)
    {
        this.message = message;
    }
}
