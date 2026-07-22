package com.example.product_catalogue.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id){
        super("User not found with Id " + id);
    }
}
