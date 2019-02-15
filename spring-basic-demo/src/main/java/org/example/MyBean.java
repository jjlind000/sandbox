package org.example;

import org.springframework.stereotype.Component;

/**
 * Created by jasonl on 08/02/19
 */

@Component
public class MyBean
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

    public MyBean(){
        message = "(MyBean) Hello to Spring Framework (default message)";
    }

    public MyBean(String message)
    {
        this.message = message;
    }
}
