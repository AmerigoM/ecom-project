package com.amancino.ecom_proj.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product not found with id: " + id);
    }
}
