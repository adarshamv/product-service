package com.example.product_catalogue.repository;

import com.example.product_catalogue.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
