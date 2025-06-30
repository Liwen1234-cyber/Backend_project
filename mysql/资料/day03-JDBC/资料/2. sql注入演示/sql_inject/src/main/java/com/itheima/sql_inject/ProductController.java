package com.itheima.sql_inject;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> list(String name) {
        return productService.list(name);
    }

    @Data
    public static class Product {
        private int id;
        private String name;
        private BigDecimal price;
    }
}
