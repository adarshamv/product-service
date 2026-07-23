package com.example.product_catalogue.controller;

import com.example.product_catalogue.dto.ProductRequest;
import com.example.product_catalogue.dto.ProductResponseV1;
import com.example.product_catalogue.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductControllerV1 {

    private final ProductService productService;

    @Operation(
            summary = "Create Product",
            description = "Creates a new Product in a catalogue"
    )
    @PostMapping
    public ResponseEntity<ProductResponseV1> createProduct(@Valid @RequestBody ProductRequest request){
        ProductResponseV1 response=productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get a Product",
            description = "Returns all the products"
    )

    @GetMapping
    public ResponseEntity<Page<ProductResponseV1>> getAllProducts(Pageable pageable){
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

//    @GetMapping
//    public ResponseEntity<List<ProductResponseV1>> getAllProducts(){
//        return ResponseEntity.ok(productService.getAllProducts());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseV1> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseV1> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseV1> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseV1>> findByName(@RequestParam String name){
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductResponseV1>> findPriceGreaterThan(
            @RequestParam Double price) {

        return ResponseEntity.ok(
                productService.findByPriceGreaterThan(price));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponseV1>> lowStock(
            @RequestParam Integer stock) {

        return ResponseEntity.ok(
                productService.findByStockLessThan(stock));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductResponseV1>> betweenPrice(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {

        return ResponseEntity.ok(
                productService.findByPriceBetween(minPrice, maxPrice));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable Long id,@RequestParam Integer stock){
        productService.updateStock(id,stock);
        return ResponseEntity.ok("Stock updated");
    }

    @DeleteMapping("/out-of-stock")
    public ResponseEntity<String> deleteOutOfStockProducts(){
        productService.deleteOutOfStockProducts();
        return ResponseEntity.ok("Out of Stock Products Deleted");
    }
}
