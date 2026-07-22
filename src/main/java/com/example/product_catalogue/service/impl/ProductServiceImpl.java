package com.example.product_catalogue.service.impl;

import com.example.product_catalogue.dto.ProductRequest;
import com.example.product_catalogue.dto.ProductResponseV1;
import com.example.product_catalogue.entity.Product;
import com.example.product_catalogue.exception.ProductNotFoundException;
import com.example.product_catalogue.repository.ProductRepository;
import com.example.product_catalogue.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseV1 createProduct(ProductRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .discount(request.getDiscount())
                .stock(request.getStock())
                .category(request.getCategory())
                .build();

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);
    }

    @Override
    public Page<ProductResponseV1> getAllProducts(Pageable pageable){
        Page<Product> productPage=productRepository.findAll(pageable);
        return productPage.map(this::mapToResponse);
    }

//    @Override
//    public List<ProductResponseV1> getAllProducts() {
//
//        List<Product> products = productRepository.findAll();
//
//        return products.stream()
//                .map(this::mapToResponse)
//                .toList();
//    }

    @Override
    public ProductResponseV1 getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id));

        return mapToResponse(product);
    }

    @Override
    public ProductResponseV1 updateProduct(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setDiscount(request.getDiscount());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());

        Product updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id));

        productRepository.delete(product);
    }

    private ProductResponseV1 mapToResponse(Product product) {

        return ProductResponseV1.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();
    }
}