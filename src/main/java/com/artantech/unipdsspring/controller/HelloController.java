package com.artantech.unipdsspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artantech.unipdsspring.service.IMessageService;

@RestController
public class HelloController {

    @Qualifier("v1")
    @Autowired
    private IMessageService messageService;

    @Qualifier("v2")
    @Autowired
    private IMessageService messageServiceV2;

    @GetMapping("/hello")
    public String sayHello() {
        return messageService.sayCustomMessage("Hello World");
    }

    @GetMapping("/hello-custom")
    public String sayHelloCustom(
            @RequestParam(name = "original", required = false) String original) {
        return messageServiceV2.sayCustomMessage(original);
    }

}
