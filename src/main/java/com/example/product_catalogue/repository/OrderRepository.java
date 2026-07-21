package com.example.product_catalogue.repository;

import com.example.product_catalogue.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
