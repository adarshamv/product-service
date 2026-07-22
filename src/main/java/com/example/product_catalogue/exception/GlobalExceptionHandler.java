package com.example.product_catalogue.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Product Not Found");
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation failed");
        Map<String,String> errors= new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        problemDetail.setProperty("errors",errors);

        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("User Not Found");
        problemDetail.setDetail(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Product Not Found");
        problemDetail.setDetail(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ProblemDetail handleOrderNotFoundException(OrderNotFoundException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Order not Found");
        problemDetail.setDetail(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ProblemDetail handleInsufficientStockException(InsufficientStockException e){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Insufficient Stock");
        problemDetail.setDetail(e.getMessage());

        return problemDetail;
    }
}
