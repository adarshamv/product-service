package com.example.product_catalogue.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id){
        super("Order not found with Id " + id);
    }
}
