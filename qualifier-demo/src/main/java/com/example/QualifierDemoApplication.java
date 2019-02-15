package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//from https://www.tutorialspoint.com/spring/spring_bean_life_cycle.htm etc

@SpringBootApplication
public class QualifierDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(QualifierDemoApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		Profile profile = (Profile) context.getBean("profile");
		profile.printAge();
		profile.printName();

		HelloWorld objA = (HelloWorld) context.getBean("helloWorld");

		objA.setMessage("I'm object A");
		objA.getMessage();

		HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
		objB.getMessage();

		((ClassPathXmlApplicationContext) context).registerShutdownHook();

	}

}

