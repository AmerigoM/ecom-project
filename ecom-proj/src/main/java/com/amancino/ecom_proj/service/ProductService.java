package com.amancino.ecom_proj.service;

import com.amancino.ecom_proj.exception.ProductNotFoundException;
import com.amancino.ecom_proj.model.Product;
import com.amancino.ecom_proj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
