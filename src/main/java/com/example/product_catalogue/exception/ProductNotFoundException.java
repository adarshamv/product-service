package com.example.product_catalogue.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Product not found with Id " + id);
    }
}
