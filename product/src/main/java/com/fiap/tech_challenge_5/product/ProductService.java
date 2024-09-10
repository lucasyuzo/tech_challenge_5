package com.fiap.tech_challenge_5.product;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public List<Product> readAll() {
        return repository.findAll();
    }

    public Product readById(Integer id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Product update(Product product) {
        Product currentProduct = repository.findById(product.getId()).orElseThrow(EntityNotFoundException::new);
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setQuantity(product.getQuantity());
        return repository.save(currentProduct);
    }

    public void deleteById(Integer id) {
        Product product = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.deleteById(product.getId());
    }

}
