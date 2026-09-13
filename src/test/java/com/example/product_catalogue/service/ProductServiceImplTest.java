package com.example.product_catalogue.service;

import com.example.product_catalogue.dto.ProductRequest;
import com.example.product_catalogue.dto.ProductResponseV1;
import com.example.product_catalogue.entity.Product;
import com.example.product_catalogue.repository.ProductRepository;
import com.example.product_catalogue.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProduct() {

        // Arrange
        ProductRequest request = new ProductRequest();
        request.setName("Laptop");
        request.setDescription("Test laptop");
        request.setPrice(50000.0);
        request.setDiscount(10);
        request.setStock(20);
        request.setCategory("Electronics");

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Laptop")
                .description("Test laptop")
                .price(50000.0)
                .discount(10)
                .stock(20)
                .category("Electronics")
                .build();

        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        // Act
        ProductResponseV1 response = productService.createProduct(request);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals("Laptop", response.getName());
        assertEquals("Test laptop", response.getDescription());
        assertEquals(50000.0, response.getPrice());
        assertEquals(10, response.getDiscount());
        assertEquals(20, response.getStock());
        assertEquals("Electronics", response.getCategory());

        verify(productRepository).save(any(Product.class));
    }
}