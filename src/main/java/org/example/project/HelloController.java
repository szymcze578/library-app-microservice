package org.example.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String HelloWorld(){
        return "Hello world from HelloWorldController";
    }
}
