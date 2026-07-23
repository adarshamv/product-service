package com.example.product_catalogue.jdbc;

import com.example.product_catalogue.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcProductService {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Product> getAllProducts(){

        String sql="SELECT * FROM products";

        return jdbcTemplate.query(sql,new ProductRowMapper());
    }

    public Product getProduct(Long id){
        String sql="SELECT * FROM products WHERE id=?";

        return jdbcTemplate.queryForObject(sql,new ProductRowMapper(),id);
    }

    public int save(Product product){
        String sql="INSERT INTO products(name,description,price,stock,category) VALUES(?,?,?,?,?)";

        return jdbcTemplate.update(sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
        );
    }

    public int update(Product product){
        String sql="UPDATE products SET name=?,description=?,price=?,stock=?,category=? WHERE id=?";

        return jdbcTemplate.update(sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getId()
        );
    }

    public int delete(Long id){
        String sql="DELETE FROM products WHERE id=?";

        return jdbcTemplate.update(sql,id);
    }

    public Integer countProducts(){
        String sql="SELECT COUNT(*) FROM products";

        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    public List<Product> findByCategory(String category){
        String sql="SELECT * FROM products WHERE category=?";

        return jdbcTemplate.query(sql, new ProductRowMapper(),category);
    }
}
