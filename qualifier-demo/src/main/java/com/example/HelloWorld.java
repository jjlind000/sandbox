package com.example;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by jasonl on 04/02/19
 */
public class HelloWorld /* implements InitializingBean, DisposableBean */
{
    private String message;

    public void setMessage(String message){
        this.message  = message;
    }
    public void getMessage(){
        System.out.println("Your Message : " + message);
    }

    public void afterPropertiesSet(){
        System.out.println("afterPropertiesSet....");
    }

    //@Override
    public void destroy() throws Exception
    {
        System.out.println("destroy......");
    }
}