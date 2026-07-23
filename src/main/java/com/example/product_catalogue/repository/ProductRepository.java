package com.example.product_catalogue.repository;

import com.example.product_catalogue.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findByName(String name);

    List<Product> findByPriceGreaterThan(Double price);

    List<Product> findByPriceBetween(Double minPrice,Double maxPrice);

    List<Product> findByStockLessThan(Integer stock);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.stock=:stock WHERE p.id=:id")
    int updateStock(@Param ("id")Long id, @Param("stock")Integer stock);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.stock=0")
    int deleteOutofStockProducts();

}
