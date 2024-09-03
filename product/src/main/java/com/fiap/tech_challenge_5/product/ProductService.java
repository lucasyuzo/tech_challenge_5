package com.fiap.tech_challenge_5.product;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public Product readById(Integer id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Product update(Product product) {
        Product currentProduct = productRepository.findById(product.getId()).orElseThrow(EntityNotFoundException::new);
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setQuantity(product.getQuantity());
        return productRepository.save(currentProduct);
    }

    public void deleteById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteById(product.getId());
    }

}
