package com.example.product_catalogue.controller;

import com.example.product_catalogue.dto.OrderRequest;
import com.example.product_catalogue.dto.OrderResponse;
import com.example.product_catalogue.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(
            @Valid @RequestBody OrderRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){

        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long id){

        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable Long id){

        orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }
}