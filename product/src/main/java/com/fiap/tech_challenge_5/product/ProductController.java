package com.fiap.tech_challenge_5.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<Product>> readAll() {
        List<Product> products = productService.readAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Product> readById(@PathVariable UUID id) {
        Product product = productService.readById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.ok("Deleted product with id " + id);
    }

}
