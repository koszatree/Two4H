package com.two4h.two4h.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class TestService {
    @Autowired
    private TestController testController;

    public int Addition(@RequestParam int a, @RequestParam int b){
        return testController.addition(a, b);
    }
}
