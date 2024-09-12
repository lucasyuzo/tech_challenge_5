package com.fiap.tech_challenge_5.product;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final AuthRestClientGateway authRestClientGateway;

    public ProductController(
            ProductService productService,
            AuthRestClientGateway authRestClientGateway
    ) {
        this.productService = productService;
        this.authRestClientGateway = new AuthRestClientGateway();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @RequestBody Product product
    ) {
        if (authRestClientGateway.validateTokenForAdmin(token, userId)) {
            Product createdProduct = productService.create(product);
            return ResponseEntity.ok(createdProduct);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<Product>> readAll(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId
    ) {
        if (authRestClientGateway.validateTokenForAdmin(token, userId)) {
            List<Product> products = productService.readAll();
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Product> readById(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authRestClientGateway.validateTokenForAdmin(token, userId)) {
            Product product = productService.readById(id);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @RequestBody Product product
    ) {
        if (authRestClientGateway.validateTokenForAdmin(token, userId)) {
            Product updatedProduct = productService.update(product);
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authRestClientGateway.validateTokenForAdmin(token, userId)) {
            productService.deleteById(id);
            return ResponseEntity.ok("Deleted product with id " + id);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
