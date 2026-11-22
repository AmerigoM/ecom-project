package com.amancino.ecom_proj.controller;

import com.amancino.ecom_proj.exception.ProductNotFoundException;
import com.amancino.ecom_proj.model.Product;
import com.amancino.ecom_proj.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    public record RequestInfo(String method, String uri, String userAgent, String remoteAddress) {}

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public RequestInfo greet(HttpServletRequest request) {
        return new RequestInfo(
                request.getMethod(),
                request.getRequestURI(),
                request.getHeader("User-Agent"),
                request.getRemoteAddr()
        );
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

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile
    ) {
        Product returnedProduct = service.addProduct(product, imageFile);
        return new ResponseEntity<>(returnedProduct, HttpStatus.CREATED);
    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}/")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestPart Product product,
            @RequestPart MultipartFile imageFile
    ) {
        Product returnedProduct = service.updateProduct(id, product, imageFile);

        return ResponseEntity.ok()
                .body(returnedProduct);
    }

    @DeleteMapping("/product/{id}/")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword) {
        List<Product> products = service.searchProducts(keyword);

        return ResponseEntity.ok(products);
    }
}
