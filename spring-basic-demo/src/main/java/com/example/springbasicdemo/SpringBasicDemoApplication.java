package com.example.springbasicdemo;

import com.example.HelloService;
import org.example.Foo;
import org.example.MyBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

//@SpringBootApplication <== original
public class SpringBasicDemoApplication
{

    public static void main(String[] args)
    {
        //SpringApplication.run(SpringBasicDemoApplication.class, args); <== original
        ApplicationContext context = getContext(ContextType.ClassPathXmlApplicationContext_asContext);
        doHelloService(context);

        context = getContext(ContextType.AnnotationConfigApplicationContext);
        doMyBean(context);
        doFoo(context);

    }

    static ApplicationContext getContext(ContextType contextType)
    {

        ApplicationContext context = null;
        switch (contextType)
        {
            case ClassPathXmlApplicationContext_asContext:
                context = new ClassPathXmlApplicationContext("beans.xml");
                break;
            case ClassPathXmlApplicationContext_asXmlContext:
                context = new ClassPathXmlApplicationContext("beans.xml");
                ((ClassPathXmlApplicationContext) context).refresh(); //needed bc context is of type ClassPathXmlApplicationContext
                break;
            case AnnotationConfigApplicationContext:
                context = new AnnotationConfigApplicationContext("org.example");
                System.out.println("Returning ANAC...");
                break;
        }

        return context;
    }

    enum ContextType
    {
        ClassPathXmlApplicationContext_asContext,
        ClassPathXmlApplicationContext_asXmlContext,
        AnnotationConfigApplicationContext
    }

    static void doHelloService(ApplicationContext context){
        Map<String, HelloService> beans = context.getBeansOfType(HelloService.class);
        HelloService helloService1 = (HelloService) beans.get("helloService1");
        System.out.println(helloService1.getMessage());
    }

    static void doMyBean(ApplicationContext context){
        MyBean myBean = context.getBean(MyBean.class);
        System.out.println(myBean.getMessage());
    }

    static void doFoo(ApplicationContext context){
        Foo foo = context.getBean(Foo.class);
        System.out.println(foo.getMessage());
    }
}

