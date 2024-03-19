package com.two4h.two4h;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello World from Spring Boot!";
    }

    @RequestMapping("/goodbye")
    public String goodbye() {
        return "Goodbye from Spring Boot!";
    }

}
