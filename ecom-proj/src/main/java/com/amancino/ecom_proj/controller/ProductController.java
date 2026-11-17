package com.amancino.ecom_proj.controller;

import com.amancino.ecom_proj.exception.ProductNotFoundException;
import com.amancino.ecom_proj.model.Product;
import com.amancino.ecom_proj.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet() {
        return "Hello world";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
            Product product = service.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
