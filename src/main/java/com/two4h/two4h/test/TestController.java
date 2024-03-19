package com.two4h.two4h.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/addition")
    public int addition(int a, int b){
        return a + b;
    }


    // http://localhost:8080/addition/2/2 - this address gives as a result 4 which is 2 + 2  given as a parameters
    @GetMapping("addition/{num1}/{num2}")
    public String additionX(@PathVariable("num1") int num1, @PathVariable("num2") int num2){
        return num1 + " + " + num2 + " = " + (num1 + num2);
    }

}
