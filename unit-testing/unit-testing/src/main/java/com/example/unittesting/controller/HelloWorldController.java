package com.example.unittesting.controller;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.Collections;
import java.util.Map;

        import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HelloWorldController {
    //name of method doesn't matter
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World!";
    }

    //https://stackoverflow.com/questions/30895286/spring-mvc-how-to-return-simple-string-as-json-in-rest-controller
    @GetMapping(path="/json/hello-world", produces=APPLICATION_JSON_VALUE)
    public Map helloWorldJSON(){
        String s = "Hello World!";
        return Collections.singletonMap("response", s);
    }
}
