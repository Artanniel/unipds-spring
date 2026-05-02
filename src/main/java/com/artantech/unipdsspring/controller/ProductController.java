package com.artantech.unipdsspring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artantech.unipdsspring.model.Product;
import com.artantech.unipdsspring.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/product")
    public Product getProduct() {
        Product p = new Product("1", "Product 1", "Description 1", 10.0, 100);
        return p;
    }

    @GetMapping("/products")
    public Map<String, Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/sort")
    public Map<String, Product> getOrderedProducts(@RequestParam(name = "order", required = false) String order) {
        return productService.getAllOrderedProducts(order);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @PostMapping("/product")
    public String addNewProduct(@RequestBody Product p) {
        System.out.println("New Product received.");
        System.out.println(
                p.getId() + "/" + p.getName() + "/");
        return "New Product add.";
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product p) {
        return productService.addProduct(p);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product p) {
        return productService.updateProduct(id, p);
    }

    @DeleteMapping("/products/{id}")
    public String removeProduct(@PathVariable String id) {
        System.out.println("Product remove request received.");
        productService.removeProduct(id);
        return "Product removed.";
    }

}
