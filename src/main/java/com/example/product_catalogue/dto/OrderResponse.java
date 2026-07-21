package com.example.product_catalogue.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderResponse {

    private Long orderId;
    private String customerName;
    private LocalDate orderDate;
    private Double totalAmount;
    private List<OrderItemResponse > items;
}
