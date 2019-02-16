package com.example;

/**
 * Created by jasonl on 08/02/19
 */
public class HelloService{
    String message;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public HelloService(){
        message = "Hello to Spring Framework (default message)";
    }

    public HelloService(String message)
    {
        this.message = message;
    }
}
