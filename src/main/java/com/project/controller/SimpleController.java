package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/simple", "/api/simple"}) //multiple api route
public class SimpleController {

    @GetMapping("/")
//    @RequestMapping(value = "simple", method = RequestMethod.GET)
    public String getGreeting() {
        return "Hello Greeting Application...";
    }

    @GetMapping("/inside")
    public String insideGreeting() {
        return  "Hello Greeting Inside";
    }

    @GetMapping("/outside")
    public String getGreetingOutside() {
        return  "Hello Greeting OutSide";
    }
}
