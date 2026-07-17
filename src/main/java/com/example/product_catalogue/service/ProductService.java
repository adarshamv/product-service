package com.example.product_catalogue.service;

import com.example.product_catalogue.dto.ProductRequest;
import com.example.product_catalogue.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse > getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id,ProductRequest request);

    void deleteProduct(Long id);
}
