package com.example.product_catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;
    private String customerName;
    private LocalDate orderDate;
    private Double totalAmount;
    private List<OrderItemResponse > items;
}
