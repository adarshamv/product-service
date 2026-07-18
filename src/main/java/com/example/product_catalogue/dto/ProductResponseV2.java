package com.example.product_catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseV2 {
    private Long id;
    private String name;
    private String description;
    private Double originalPrice;
    private Integer discount;
    private Double finalPrice;
    private Integer stock;
    private Boolean available;
    private String category;
}
