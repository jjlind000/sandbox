package org.example.producer;


import org.example.Foo;
import org.example.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jasonl on 08/02/19
 */

@Configuration
public class FooMaker
{
    {
        System.out.println("FooMaker initializer...");
    }

    @Bean
    public Foo producesFoo(){
        Foo foo = new Foo("Hello from Foo");
        return foo;
    }

}

