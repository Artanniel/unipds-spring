package com.artantech.unipdsspring.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.artantech.unipdsspring.model.Product;

@Service
public class ProductService {
    private Map<String, Product> productList = new HashMap<>();

    public ProductService() {
        productList.put("1", new Product("1", "Computador", "Description 1", 1500.0, 100));
        productList.put("2", new Product("2", "Mouse", "Description 2", 50.0, 200));
        productList.put("3", new Product("3", "Teclado", "Description 3", 100.0, 300));
        productList.put("4", new Product("3", "Monitor", "Description 3", 500.0, 300));
        productList.put("5", new Product("3", "Impressora", "Description 3", 350.0, 300));
    }

    public Map<String, Product> getAllProducts() {
        return productList;
    }

    public Product getProduct(String id) {
        return productList.get(id);
    }

    public Product addProduct(Product product) {
        String id = "";
        if (productList.containsKey(product.getId())) {
            System.out.println("Product already exists.");
            return productList.get(product.getId());
        } else {
            id = String.valueOf(productList.size() + 1);
        }
        productList.put(id, product);
        System.out.println("Product added.");
        return productList.get(id);
    }

    public void removeProduct(String id) {
        productList.remove(id);
    }

    public Product updateProduct(String id, Product product) {
        productList.put(id, product);
        return productList.get(id);
    }

    public Map<String, Product> getAllOrderedProducts(String order) {
        Comparator<Map.Entry<String, Product>> comparator = null;

        if ("asc".equals(order)) {
            comparator = Comparator.comparing(e -> e.getValue().getName());
        }

        if ("desc".equals(order)) {
            comparator = Comparator.comparing(e -> e.getValue().getName());
            comparator = comparator.reversed();
        }

        return comparator == null ? productList
                : productList.entrySet().stream()
                        .sorted(comparator)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new // ← preserva a ordem após sort
                        ));
    }
}
