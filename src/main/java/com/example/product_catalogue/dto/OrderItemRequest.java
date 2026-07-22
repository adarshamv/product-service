package com.example.product_catalogue.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequest {

    @NotNull(message = "Product ID is required")
    private Long ProductId;

    @Min(value = 1,message = "Qunatity must be atleast 1")
    private Integer quantity;
}
