package com.artantech.unipdsspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/open")
    public String sayHelloOpen() {
        return "Hello World from the open endpoint";
    }

    @GetMapping("/restricted")
    public String sayHelloRestricted() {
        return "Hello World from the restricted endpoint";
    }
}
