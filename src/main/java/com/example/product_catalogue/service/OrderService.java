package com.example.product_catalogue.service;

import com.example.product_catalogue.dto.OrderRequest;
import com.example.product_catalogue.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    void deleteOrder(Long id);
}
