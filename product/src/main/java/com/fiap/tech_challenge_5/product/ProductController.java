package com.fiap.tech_challenge_5.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/read-by-id")
    public ResponseEntity<Product> readById(@RequestParam Integer id) {
        Product product = productService.readById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok("Deleted product with id " + id);
    }

}
