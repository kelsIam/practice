package com.pluralsight.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(){
        return "Hello there!";
    }

    //requestparam binds the name query parameter from the URL to the method argument
    //when parameter is missing, use "World"
    //
    @GetMapping("/hello")
    public String helloName(@RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name;
    }
}
