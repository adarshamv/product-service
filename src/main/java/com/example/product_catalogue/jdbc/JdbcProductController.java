package com.example.product_catalogue.jdbc;

import com.example.product_catalogue.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jdbc/products")
public class JdbcProductController {

    private final JdbcProductService jdbcProductService;

    public JdbcProductController(JdbcProductService jdbcProductService){
        this.jdbcProductService=jdbcProductService;
    }

    @PostMapping
    public String save(@RequestBody Product product){
        int rows= jdbcProductService.save(product);
        return rows + " " + "Product inserted";
    }

    @GetMapping
    public List<Product> getAll(){
        return jdbcProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return jdbcProductService.getProduct(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id,@RequestBody Product product){
        product.setId(id);
        int rows= jdbcProductService.update(product);
        return rows + " " + "Product Updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        int row=jdbcProductService.delete(id);
        return row + " " + "Product Deleted";
    }
}
