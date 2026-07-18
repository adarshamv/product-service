package com.example.product_catalogue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @Positive(message = "Price must be greater than zero")
    private Double price;

    private Integer discount;

    @Positive(message = "Stock must be greater than zero")
    private Integer stock;

    @NotBlank(message = "Category is required")
    private String category;
}
