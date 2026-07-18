package com.example.product_catalogue.service;

import com.example.product_catalogue.dto.ProductRequest;
import com.example.product_catalogue.dto.ProductResponseV1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponseV1 createProduct(ProductRequest request);

    Page<ProductResponseV1> getAllProducts(Pageable pageable);

//    List<ProductResponseV1> getAllProducts();

    ProductResponseV1 getProductById(Long id);

    ProductResponseV1 updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
