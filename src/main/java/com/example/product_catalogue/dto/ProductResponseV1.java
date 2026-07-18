package com.example.product_catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductResponseV1 {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer discount;
    private Integer stock;
    private String category;
}
