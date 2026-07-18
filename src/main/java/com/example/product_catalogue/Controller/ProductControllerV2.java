package com.example.product_catalogue.Controller;

import com.example.product_catalogue.dto.ProductResponseV1;
import com.example.product_catalogue.dto.ProductResponseV2;
import com.example.product_catalogue.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/products")
@RequiredArgsConstructor
public class ProductControllerV2 {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseV2> getProductById(@PathVariable Long id){
        ProductResponseV1 response=productService.getProductById(id);

        ProductResponseV2 responseV2 = ProductResponseV2.builder()
                .id(response.getId())
                .name(response.getName())
                .description(response.getDescription())
                .originalPrice(response.getPrice())
                .discount(10)
                .finalPrice(
                        response.getPrice()
                                - (response.getPrice() * response.getDiscount() / 100.0)
                )
                .stock(response.getStock())
                .available(response.getStock() > 0)
                .category(response.getCategory())
                .build();

        return ResponseEntity.ok(responseV2);


    }
}
