package com.artantech.unipdsspring.aula01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artantech.unipdsspring.aula01.model.Product;

@RestController
@RequestMapping("/api")
public class IsidroController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/product")
    public Product getProduct() {
        Product p = new Product("1", "Product 1", "Description 1", 10.0, 100);
        return p;
    }

    @PostMapping("/product")
    public String addNewProduct(@RequestBody Product p) {
        System.out.println("New Product received.");
        System.out.println(
                p.getId() + "/" + p.getName() + "/");
        return "New Product add.";
    }

}
