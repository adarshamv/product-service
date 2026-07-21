package com.example.product_catalogue.repository;

import com.example.product_catalogue.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
